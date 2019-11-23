//function remove() {
//    //alert("asdasd");
//    var card = document.getElementById("card-to-delete");
//    card.parentElement.removeChild(card);
//}
//function add(){
//    car card = document.getElementById("card-to-delete");
//    document.body.appendChild(card);//УСтаревший код. Сейчас - библиотеки. jquery
//}
function removeByJquery() {

    var element = $("#card-to-delete"); //как в css можно .card class    # id
    element.remove(); //можно без .card

}

function add() {
    //если внутрь элемента, то как ребенка. Обычно - после, after. append закончить/склеить
    var but2 = $("#add-but"); //нашли элемент, но таких несколько, массив.
    but2.after("#card-to-delete");
}
//динамические вещи это джава

var current = "green";
let colors = ["none", "none", "none", "none", "none", "none", "none", "none", "none"];

function restart() {
    
}
    
function turn(number) {
    //alert(number);
    //console.log(number);

    //$("#s" + number).css("background-color", current); //без анимаций
    $("#s" + number).animate({
        borderColor: "white",
        marginTop: 15 ,
        backgroundColor : current
    }, 500)
    colors[number - 1] = current;
    console.log(colors);

    if (current === "green") {

        current = "red";
    } else {
        current = "green";
    }
    $("#s" + number).prop("disabled", true);
    var result = check();
    console.log(result);
    if(result!="none"){
        for(let i=1; i<10; i++){
            $("#s" + i).prop("disabled", true);
        }
    }
    
    if(result!="none"){
        $("#result").animate({
            backgroundColor:result,
        },200);
        $("#result").text("Winner is")
    }
    

    if (check() == "green") {

    }


    for (let i = 0; i < 10; i++) {

    }

}

function check() {
    for (let i = 0; i < 7; i += 3) {
        if (colors[0 + i] == colors[1 + i] == colors[2 + i] && colors[2 + i] != "none") {
            return colors[0 + i];
        }
    }

    for (let i = 0; i < 3; i += 1) {
        if (colors[3 + i] == colors[4 + i] == colors[5 + i] && colors[5 + i] != "none") {
            return colors[0 + i];
        }
    }


    if (colors[0] == colors[4] && colors[4] == colors[8] && colors[0] !=
        "none") {
        return colors[0];
    }
    if (colors[2] == colors[4] == colors[4] && colors[6] && colors[2] !=
        "none") {
        return colors[2];
    }

    let wasNone = false;
    for (let i = 0; i < 9; i++) {
        if (colors[i] == "none") {
            wasNone = true;
        }

    }
    if (wasNone == false) {
        return "draw";
    }
    return "none";


    // return "nothing";
}
