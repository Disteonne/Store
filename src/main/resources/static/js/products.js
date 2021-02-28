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
var inputIn = document.querySelector('.input-in');
var button = document.querySelector('button');

var urlHost = document.location.host

button.onclick = function () {
    countPage = 0;
    var searchName = inputIn.value;
    console.log(searchName);
    var newUrl = 'http://' + urlHost + '/products?nameLike=' + searchName + '&page=';//&size=6
    getMax('http://' + urlHost + '/count?name=' + searchName, 'json', newUrl);
    //functionByUrl(newUrl, 'json',);
}

var urlHost = document.location.host
var kek = inputIn.value
console.log(kek === "")
var s = 'sk';
if (inputIn.value === "") {
    getMax('http://' + urlHost + '/count', 'text', 'http://' + urlHost + '/products?page=');
} else {
    countPage = 0;
    getMax('http://' + urlHost + '/count?name=' + inputIn.value);
}


var functionByUrl = function (url, type) {
    getInfoAboutProduct(url, type,
        function (data) {
            console.log(data)
            var table = "<table border='2' class='table'>" + "<thead>" +
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
var countPage = 0;
var result = 0;

function getMax(url, type, urlForProduct) {
    getInfoAboutProduct(url, type, function (data) {
        maxPage = Math.round(data / 5);//максимальное кол-во страниц
        functionByUrl(urlForProduct + countPage, 'json')
        pagination(countPage, maxPage);
        var next;
        var prev;
        if ((countPage + 1) !== maxPage) {
            next = document.getElementById('next');
            next.onclick = function () {
                if (countPage <= maxPage) {
                    countPage++;
                    getMax(url, type, urlForProduct);
                    console.log(kek)
                    functionByUrl(urlForProduct + countPage, 'json');
                } else {
                    pagination(countPage, maxPage);
                }
            }
        }
        if (countPage !== 0) {
            prev = document.getElementById('prev');
            prev.onclick = function () {
                if (countPage >= 0) {
                    countPage--;
                    getMax(url, type, urlForProduct);
                    functionByUrl(urlForProduct + countPage, 'json');
                }
            }
        }
    })
}

var pagination = function nextPrev(counter, maxPage) {
    var forButton = '<table>' + '<tr><th>';
    if (counter === 0) {
        forButton += '<button id="next">Next</button></th>';
    } else if (counter + 1 === maxPage) {
        forButton += '<th><button id="prev">Prev</button></th><th>';
    } else {
        forButton += '<th><button id="prev">Prev</button></th><th>' +
            '<button id="next">Next</button></th>';
    }
    forButton += '</tr>' +
        '</table>';
    document.getElementById('pagination').innerHTML = forButton;
}
//КОРЗИНА
var countBasket = 0;
document.onclick = function (event) {
    if (countBasket <= 10) {
        if (event.target.classList.contains('buttons_plus')) {
            plusFunction(event.target.dataset.id);
        }
        if (event.target.classList.contains('buttons_minus')) {
            minusFunction(event.target.dataset.id);
        }
    } else {
        console.log('basket is full')
    }
}
var mapBasket = new Map();

function plusFunction(id) {
    var flag = false;
    if (mapBasket.size === 0) {
        mapBasket.set(id, 1);
    } else {
        for (var [key, value] of mapBasket) {
            if (key === id) {
                var kek = value;
                mapBasket.set(id, ++kek);
                flag = true;
                break;
            }
        }
        if (flag === false) {
            mapBasket.set(id, 1);
        }
    }
    //вывод корзины
    basket(mapBasket);
}

function minusFunction(id) {
    var flag = false;
    if (mapBasket.size === 0) {
        console.log("basket is empty");
    } else {
        for (var [key, value] of mapBasket) {
            if (key === id) {
                var kek = value;
                if (--kek === 0) {
                    mapBasket.delete(key)
                } else {
                    mapBasket.set(id, kek);
                }
                flag = true;
                break;
            }
        }
        if (flag === false) {
            console.log("product not found")
        }
    }
    basket(mapBasket);//вывод корзины

    //парсинг мапы в json: toObject->json
    //var obj = map_to_object(mapBasket);
    //console.log(JSON.stringify(obj));
}

var getProductById =  function productById(tableBasket,map) {
    getInfoAboutProduct('http://' + urlHost + '/all/products', 'json', function (data) {
        var listProduct=new Set();
        for (let i = 0; i < data.length; i++) {
            listProduct.add(data[i]);
        }
        var sumResult=0;
        for (var [key, values] of map) {
            for(let item of listProduct) {
                if(item.id===parseInt(key)) {
                    tableBasket += "<tr><td>" + item.name + "</td>"
                        + "<td>" + values + "</td>"
                        + "<td>"+(item.price*values)+ "</td>";
                    sumResult+=item.price*values;
                }
            }
        }
        tableBasket += "<tr><td></td>"
            + "<td></td>"
            + "<td><h2>Итого: "+sumResult+"</h2></td>";
        tableBasket += "</tr></tbody></table>";
        document.getElementById('basket').innerHTML = tableBasket;
    });

};

function basket(map) {
    var tableBasket = "<h1 align=\"center\">Basket</h1>";

    if (map.size === 0) {
        tableBasket += "<h2>Basket is empty</h2>";
        document.getElementById('basket').innerHTML = tableBasket;
    } else {
        tableBasket += "<br><table border='2' class='table'><thead>" +
            "<tr><th>Name</th><th>Count</th><th>Price</th>></tr></thead><tbody>";
        getProductById(tableBasket,map);
    }
}


function map_to_object(map) {
    const out = Object.create(null)
    map.forEach((value, key) => {
        if (value instanceof Map) {
            out[key] = map_to_object(value)
        } else {
            out[key] = value
        }
    })
    return out
}
