package com.example.marketplace.service;

import com.example.marketplace.models.Item;
import com.example.marketplace.models.ItemResponse;
import com.example.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Optional;

@Service
public class ItemService {
    private Connection connectionNow;
    @Autowired
    ItemRepository productRepository;

    public Item add(ItemResponse response) {
        Item item = productRepository.save(new Item(response.getName()));
        try{
            PreparedStatement st = getConnection().prepareStatement(
                    "INSERT INTO items (itemId, name, sale) VALUES (?, ?, ?)");
            st.setInt(1, item.getItemId());
            st.setString(2, item.getName());
            st.setBoolean(3, item.getSale());
            st.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return item;
    }

    public void remove(Integer id) {
        Optional<Item> item = productRepository.findById(id);
        boolean a = item.isPresent();
        if(item.isPresent()){
            try{
                PreparedStatement st = getConnection().prepareStatement(
                        "DELETE FROM items WHERE itemId = ?;");
                st.setInt(1, item.get().getItemId());
                st.executeUpdate();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        productRepository.deleteById(id);
    }

    public Optional<Item> getById(Integer id) {
        return productRepository.findById(id);
    }

    public Iterable<Item> getAllItems() {
        try{
            PreparedStatement st = getConnection().prepareStatement(
                    "SELECT itemId, name, sale FROM items");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Item value =  new Item();
                value.setName(rs.getString("name"));
                value.setSale(rs.getBoolean("sale"));
                value.setItemId(rs.getInt("itemId"));
                productRepository.save(value);
            }
        }
        catch (Exception ex){
            return  null;
        }
        return productRepository.findAll();
    }

    public Item save(Item item) {
        try{
            PreparedStatement st = getConnection().prepareStatement(
                    "UPDATE items SET sale = ? WHERE itemId=?;");
            st.setBoolean(1, item.getSale());
            st.setInt(2, item.getItemId());
            st.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return productRepository.save(item);

    }
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connectionNow != null && !connectionNow.isClosed()) {
            return connectionNow;
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        connectionNow = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab12", "root", "12345");
        return connectionNow;
    }
}
