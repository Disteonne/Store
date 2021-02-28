var mapBasket;

function getMapFromProduct(Map) {
    mapBasket = Map;
}

function tableBasket() {
    var tableBasket = "<table>" +
        "<tr><th>ID</th><th>Count</th></tr>";
    for (var [key, value] of mapBasket) {
        tableBasket += "<tr><td>" + key + "</td>"
            + "<td>" + value + "</td>";
    }
    tableBasket += "</tr></table>"
    document.getElementById("basket").innerHTML = tableBasket;
}

var arrProduct=new Array();
function getJsonMap() {
    var jsonMap = localStorage.getItem("map");
    arrProduct.push(jsonMap);
   // console.log(jsonMap)
    //console.log(localStorage.getItem("map"))

    document.getElementById("kek").innerHTML=arrProduct.toString();
}
getJsonMap();


