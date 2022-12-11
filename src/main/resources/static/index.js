$(document).ready(async ()=> {

    const items = $('#items')
    const createItem = (json) => {
        const item = $(`<li class="item-section">
            <span>${json.name}</span>
        </li>`)
        const deleteBtn = $(feather.icons['x'].toSvg({ class: 'plus' }))
        const saleCheck = $(`<input type="checkbox" ${json.sale ? 'checked' : ''} value="${json.sale}">`)
        item.append(saleCheck)
        item.append(deleteBtn)

        deleteBtn.click(()=>{
            $.ajax({
                method: "DELETE",
                url: `/api/remove/${json.id}`,
                contentType:"application/json",
                dataType:"json",
                cache: false,
            }).done(()=>{
                item.remove()
            }).fail(()=>{
                item.remove()
            })
        })
        saleCheck.click(()=>{
            $.ajax({
                method: "PATCH",
                url: `/api/update/${json.id}`,
                contentType:"application/json",
                dataType:"json",
                cache: false,
            }).done(value=>{
                console.log(value)
            }).fail(()=>{
            })
        })
        return item;
    }
    $.ajax({
        method: "GET",
        url: "/api/",
        contentType:"application/json",
        dataType:"json",
        cache: false,
    }).done(value=>{
            console.log(value)
            if(value && value.length){
                value.forEach(x=>{
                    items.append(createItem(x))
                })
            }
        })
})
