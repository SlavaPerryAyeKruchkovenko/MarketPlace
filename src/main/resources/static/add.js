
$(document).ready(()=>{
    feather.replace()
    const addBtn = $('#add');
    addBtn.click(e=>{
        console.log($('#name').val())
        $.ajax({
            method: "POST",
            url: `/api/add`,
            contentType:"application/json",
            dataType:"json",
            data: JSON.stringify({
                "name": $('#name').val()
            }),
            cache: false,
        }).done(()=>{
            console.log('done')
            $('#name').val(' ')
        })
    })

})