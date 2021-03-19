//метод для парсинга JSON
var getInfoAboutProduct = function (url, type, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = type;
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            callback(xhr.response)
        } else {
            callback(status, xhr.response);
        }
    };
    xhr.send();
};

function getSelector() {
    var inp = "<table>" +
        "<tr>" +
        "<td>" +
        "<input id='input' class='input-in' type=\"text\" style='background-color: #fafafa;" +
        "  border:0;" +
        "  box-shadow:0 0 4px rgba(0,0,0,0.3);" +
        "  transition: .3s box-shadow;" +
        "  width: 600px ; height: 35px '" + "></td>" +
        "<td><button class='input-selector-one'>Поиск</button></td></tr></table>";

    document.getElementById('inputText').innerHTML = inp;

}

var inputIn = document.querySelector('.input-in');
var button = document.querySelector('button');

var urlHost = document.location.host

var urlHost = document.location.host

var orderBy="asc";

var countPage = 0;

function start(inputIn) {
    if (inputIn === null) {
        getMax('http://' + urlHost + '/count', 'text', 'http://' + urlHost + '/products?page=',orderBy);
    } else if (inputIn === "") {
        getMax('http://' + urlHost + '/count', 'text', 'http://' + urlHost + '/products?page=',orderBy);
        //var inputIn = document.querySelector('.input-in');
    } else {
        //countPage = 0;
        //getMax('http://' + urlHost + '/count?name=' + inputIn.value);
        getMax('http://' + urlHost + '/count?name=' + inputIn.value,'text','http://' + urlHost + '/products?nameLike='+inputIn.value+'&page=',orderBy);
    }
}

start(inputIn);


var functionByUrl = function (url, type) {
    getInfoAboutProduct(url, type,
        function (data) {
            var table = "<br>" +
                "<table border='2' class='table'>" + "<thead>" +
                "<tr><th>ID</th><th>Наименование</th><th>Раздел</th><th>Стоимость</th><th>Кол-во</th>" +
                "<th>ID поставщика</th><th>Описание товара</th><th>Корзина</th></tr></thead>"
                + "<tbody>";

            for (var iter = 0; iter < data.length; iter++) {
                table += "<tr><td>" + data[iter].id + "</td><td>"
                    + data[iter].name + "</td><td>"
                    + data[iter].type + "</td><td>"
                    + data[iter].price + "</td><td>"
                    + data[iter].count + "</td><td>"
                    + data[iter].supplierId + "</td><td>"
                    + data[iter].info + "</td><td>"
                    + "<button  class=\"buttons_plus\" data-id=" + data[iter].id + ">+</button>" //button-primary plus
                    + "<button  class=\"buttons_minus\" data-id=" + data[iter].id + ">-</button></tr>" //button-primary minus
            }
            table += "</tbody></table>";
            document.getElementById('all_product').innerHTML = table;
        });
}

var page = 0;
var maxPage = 0;
var result = 0;

function getMax(url, type, urlForProduct) {
    getInfoAboutProduct(url, type, function (data) {
        maxPage = Math.round(data / 5);//максимальное кол-во страниц
        console.log(maxPage);
        functionByUrl(urlForProduct + countPage+"&orderBy="+orderBy, 'json')
        pagination(countPage, maxPage);
    })
}

var pagination = function nextPrev(counter, maxPage) {
    var forButton = '<table align="center">' + '<tr><th>';
    if (counter === 0) {
        forButton += '<button id="next" class="pagination_next">Next</button></th>';
    } else if (counter  === maxPage) {
        forButton += '<th><button id="prev" class="pagination_prev">Prev</button></th><th>';
    } else {
        forButton += '<th><button id="prev" class="pagination_prev">Prev</button></th><th>' +
            '<button id="next" class="pagination_next">Next</button></th>';
    }
    forButton += '</tr>' +
        '</table>';
    document.getElementById('pagination').innerHTML = forButton;
}

//КОРЗИНА
var countBasket = 0;
document.onclick = function (event) {
    if (event.target.classList.contains('buttons_plus')) {
        plusFunction(event.target.dataset.id);
    }
    if (event.target.classList.contains('buttons_minus')) {
        minusFunction(event.target.dataset.id);
    }
    if (event.target.classList.contains('delete')) {
        deleteProduct(event.target.dataset.id);
    }
    if (event.target.classList.contains("headers")) {
        getSelector();
    }
    if (event.target.classList.contains("input-selector-one")) {
        countPage = 0;
        var searchName = document.querySelector('.input-in').value;
        console.log(searchName);
        var newUrl = 'http://' + urlHost + '/products?nameLike=' + searchName + '&page=';//&size=6
        getMax('http://' + urlHost + '/count?name=' + searchName, 'json', newUrl);
    }
    if (event.target.classList.contains("logout")) {
        window.location.replace('http://' + document.location.host + "/logout");
    }
    if (event.target.classList.contains("mainMenu")) {
        window.location.replace("http://" + document.location.host + "/mainMenu.html");
    }
    if (event.target.classList.contains("ASC")) {
        orderBy = "asc";
        if (document.querySelector('.input-in') == null) {
            start(inputIn);
        } else {
            var searchName = document.querySelector('.input-in').value;
            console.log(searchName);
            var newUrl = 'http://' + urlHost + '/products?nameLike=' + searchName + '&page=';//&size=6
            getMax('http://' + urlHost + '/count?name=' + searchName, 'json', newUrl);
        }
    }
    if (event.target.classList.contains("DESC")) {
        orderBy = "desc";
        if (document.querySelector('.input-in') == null) {
            start(inputIn);
        } else {
            var searchName = document.querySelector('.input-in').value;
            console.log(searchName);
            var newUrl = 'http://' + urlHost + '/products?nameLike=' + searchName + '&page=';//&size=6
            getMax('http://' + urlHost + '/count?name=' + searchName, 'json', newUrl);
        }
    }
    if(event.target.classList.contains("pagination_next")){
        countPage++;
        start(document.querySelector('.input-in'));
    }
    if(event.target.classList.contains("pagination_prev")){
        countPage--;
        start(document.querySelector('.input-in'));
    }
}

function deleteProduct(id) {
    var productId = id.substr(1, 2);
    mapBasket.delete(productId);
    basket(mapBasket);
}

var mapBasket = new Map();

function plusFunction(id) {
    if (mapBasket.has(id) === false) {
        mapBasket.set(id, 1);
    } else {
        var valueById = mapBasket.get(id);
        mapBasket.set(id, ++valueById);
    }
    //вывод корзины
    basket(mapBasket);
}

function minusFunction(id) {
    if (mapBasket.size !== 0) {
        if (mapBasket.has(id)) {
            var valueById = mapBasket.get(id);
            if ((valueById - 1) !== 0) {
                mapBasket.set(id, --valueById);
            } else {
                mapBasket.delete(id);
            }
        }
    }
    basket(mapBasket);//вывод корзины

    //парсинг мапы в json: toObject->json
    //var obj = map_to_object(mapBasket);
    //console.log(JSON.stringify(obj));
}

class Basket {
    constructor(id, count) {
        this.id = id;
        this.count = count;
    }
}

var addBasket = function productById(tableBasket, map) {
    getInfoAboutProduct('http://' + urlHost + '/all/products', 'json', function (data) {
        var listProduct = new Set();
        for (let i = 0; i < data.length; i++) {
            listProduct.add(data[i]);
        }
        var sumOfPurchase = 0;
        //var basket = new Array();
        var basket = new Array();
        for (var [key, values] of map) {
            for (let item of listProduct) {
                if (item.id === parseInt(key)) {
                    tableBasket += "<tr><td>" + item.name + "</td>"
                        + "<td>" + values + "</td>"
                        + "<td>" + (item.price * values) + "</td>"
                        + "<td><button class='delete' data-id=\"d" + key + "\">Удалить</button></td>";
                    sumOfPurchase += item.price * values;
                    basket.push(new Basket(item.id, values));
                }
            }
        }
        var jsonBasket = JSON.stringify(basket);
        tableBasket += "<tr><td></td><td></td><td></td><td><h2>Итого: " + sumOfPurchase + "</h2></td>" +
            "<tr><td></td><td></td><td></td><td><button id='buy' formaction='post'>Купить</button></td>";
        tableBasket += "</tr></tbody></table>";
        document.getElementById('basket').innerHTML = tableBasket;


        var buttonBuy = document.getElementById('buy');
        buttonBuy.onclick = function () {
            sendBasketToSpring(jsonBasket);
            document.getElementById('basketTable').remove();
            alert('Куплено');
            mapBasket.clear();
            location.reload();//обновляем
        }
    });
};

function sendBasketToSpring(jsonText) {

    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: "/basket",
        data: jsonText, // Note it is important
        success: function (result) {
            // do what ever you want with data
        }
    });
}

function basket(map) {
    var tableBasket = "<h1 align=\"center\">Basket</h1>";

    if (map.size === 0) {
        tableBasket += "<h2 align='center'>Basket is empty</h2>";
        document.getElementById('basket').innerHTML = tableBasket;
    } else {
        tableBasket += "<br><table id='basketTable' border='2' class='table'><thead>" +
            "<tr><th>Name</th><th>Count</th><th>Price</th><th></th></tr></thead><tbody>";
        addBasket(tableBasket, map);
    }
}



