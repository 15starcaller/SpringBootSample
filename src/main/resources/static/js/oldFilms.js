

function test () {




}

$(document).ready(function () {
    $.ajax({
        url: '/api/film/all',
        type: 'get',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (films) {//array
            console.log(films);
            console.log(films.title);
            console.log(films.rating);
            for (var i = 0; i < films.length; i++) {
                // console.log(films[i].title)
                $("#forAdd").before(
                    "<tr>" +
                    "<td>" +films[i].id +"</td>" +
                    "<td>" +films[i].title +"</td>" +
                    "<td>" +films[i].rating +"</td>" +
                    "<td>" +films[i].age +"</td>" +
                    "<td>" +
                    "<a class=\"btn btn-primary\" href=\"/films/editFilm?id="+films[i].id+"\">Изменить</a>" +
                    "<a class=\"btn btn-primary\" href=\"/films/deleteFilm?id="+films[i].id+"\">Удалить</a>" +
                    "</td>"+
                "</td>")
            }

        },
        error: function (response) {
            alert('error');
        }
    });

})

function addFilm(){ /*ajax наоборот*/

    var title =$("#title").val();
    var rating =$("#rating").val();
    var age =$("#age").val();

    var newProduct ={
        'title':title,
        'rating':rating,
        'age':age
    }
    $.ajax({
        method:"post",
        url: "/api/products/add",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(newProduct),
        success: function(){
            window.location.replace("/productsPage")
        },
        error: function (error) {

        }
    })

}