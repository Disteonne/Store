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
function send(jsonText, url, query) {
    $.ajax({
        type: query,
        contentType: 'application/json; charset=utf-8',
        url: url,
        data: jsonText,
        success: function (result) {
            // do what ever you want with data
        }
    });
}
class ProductPutDto{
    constructor(id,name,type,price,count,supplierId,info) {
        this.id=id;
        this.name=name;
        this.type=type;
        this.price=price;
        this.count=count;
        this.supplierId=supplierId;
        this.info=info;
    }
}

class ProductPostDto{
    constructor(name,type,price,count,supplierId,info) {
        this.name=name;
        this.type=type;
        this.price=price;
        this.count=count;
        this.supplierId=supplierId;
        this.info=info;
    }
}
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

function start(inputIn) {
    if (inputIn === null) {
        getMax('http://' + urlHost + '/count', 'text', 'http://' + urlHost + '/products?page=');
    } else if (inputIn === "") {
        getMax('http://' + urlHost + '/count', 'text', 'http://' + urlHost + '/products?page=');
        //var inputIn = document.querySelector('.input-in');
    } else {
        countPage = 0;
        getMax('http://' + urlHost + '/count?name=' + inputIn.value);
    }
}

start(inputIn);


var functionByUrl = function (url, type) {
    getInfoAboutProduct(url, type,
        function (data) {
            var table = "<br>" +
                "<table border='2' class='table'>" + "<thead>" +
                "<tr><th>ID</th><th>Наименование</th><th>Раздел</th><th>Стоимость</th><th>Кол-во</th>" +
                "<th>ID поставщика</th><th>Описание товара</th><th></th></tr></thead>"
                + "<tbody>";

            for (var iter = 0; iter < data.length; iter++) {
                table += "<tr><td>" + data[iter].id + "</td><td>"
                    + data[iter].name + "</td><td>"
                    + data[iter].type + "</td><td>"
                    + data[iter].price + "</td><td>"
                    + data[iter].count + "</td><td>"
                    + data[iter].supplierId + "</td><td>"
                    + data[iter].info + "</td><td>"
                    + "<button  class=\"buttons_edit\" data-id=" + data[iter].id + ">Edit</button>"; //button-primary plus
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
    var forButton = '<table align="center">' + '<tr><th>';
    if (counter === 0) {
        forButton += '<button id="next" class="pagination_next">Next</button></th>';
    } else if (counter + 1 === maxPage) {
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
document.onclick = function (event) {
    if (event.target.classList.contains('headers')) {
        getSelector();
    }
    if (event.target.classList.contains('buttons_edit')) {
        edit(event.target.dataset.id);
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
    if (event.target.classList.contains("save")){
        send(JSON.stringify(new ProductPutDto(event.target.dataset.id,document.querySelector('.editName').value,
            document.querySelector('.editType').value,
            document.querySelector('.editPrice').value,
            document.querySelector('.editCount').value,
            document.getElementById('editProd').value,
            document.querySelector('.editInfo').value)),
            "http://"+document.location.host+"/product","PUT");
        alert('Товар изменен');
        window.location.replace("http://"+document.location.host+"/edit.html");
    }
    if (event.target.classList.contains('logout')) {
        window.location.replace('http://' + document.location.host + "/logout");
    }
    if (event.target.classList.contains('mainMenu')) {
        window.location.replace("http://" + document.location.host + "/mainMenu.html");
    }
    if (event.target.classList.contains('newProduct')) {
        newProduct();
    }
    if (event.target.classList.contains('create')){
       send(JSON.stringify(new ProductPostDto(document.querySelector('.newName').value,
           document.querySelector('.newType').value,
           document.querySelector('.newPrice').value,
           document.querySelector('.newCount').value,
           document.getElementById('newProd').value,
           document.querySelector('.newInfo').value
       )),
           "http://"+document.location.host+"/product","POST");
    }
}

function edit(id) {
    var inputs = "<table align='center'><tbody>";
    getInfoAboutProduct("http://" + document.location.host + "/product/" + parseInt(id), 'json', function (currentProduct) {
        inputs += "<tr><td>Наименование</td><td><input class='editName' type='text' value='" + currentProduct.name + "'></td></tr>" +
            "<tr><td>Раздел</td><td><input class='editType' type='text' value='" + currentProduct.type + "'></td></tr>" +
            "<tr><td>Стоимость</td><td><input class='editPrice' type='text' value='" + currentProduct.price + "'></td></tr>" +
            "<tr><td>Кол-во</td><td><input class='editCount' type='text' value='" + currentProduct.count + "'></td></tr>" +
            "<tr><td>Описание товара</td><td><input class='editInfo' type='text' value='" + currentProduct.info + "'></td></tr>" +
            "<tr><td>Поставщик</td><td>" +
            "<select id='editProd'>";
        getInfoAboutProduct("http://" + document.location.host + "/supplier/" + parseInt(currentProduct.supplierId), 'json', function (currentSupplier) {
            getInfoAboutProduct("http://" + document.location.host + "/address/" + parseInt(currentSupplier.addressId), 'json', function (addressOfCurrentSupp) {
                inputs += "<option value='" + currentSupplier.id + "'>Наименование: " + currentSupplier.name + ", " +
                    "Почта: " + currentSupplier.mail + ", Адрес: страна- " + addressOfCurrentSupp.country + ", " +
                    "г." + addressOfCurrentSupp.city + ", ул." + addressOfCurrentSupp.street + ", здание/офис/кв " + addressOfCurrentSupp.building + "" +
                    "</option>";
                getInfoAboutProduct("http://" + document.location.host + "/allSuppliers", 'json',function (allSupp) {
                    for (let i = 0; i < allSupp.length; i++) {
                        if (allSupp[i].name !== currentSupplier.name && allSupp[i].mail !== currentSupplier.mail) {
                            getInfoAboutProduct("http://" + document.location.host + "/address/" + parseInt(allSupp[i].addressId),'json', function (addressAllsSupp) {
                                if(i===allSupp.length-1) {
                                    inputs += "<option value='" + allSupp[i].id + "'>Наименование: " + allSupp[i].name + ", " +
                                        "Почта: " + allSupp[i].mail + ", Адрес: страна- " + addressAllsSupp.country + ", " +
                                        "г." + addressAllsSupp.city + ", ул." + addressAllsSupp.street + ", здание/офис/кв " + addressAllsSupp.building + "" +
                                        "</option>";
                                    console.log('я в конце');
                                    inputs += "</td></tr>" +
                                        "<tr><td></td><td></td></tr>"+
                                        "<tr><td></td><td></td></tr>"+
                                        "<tr><td><button class='save' data-id='" + id + "'>Сохранить</button></td></tr>"+
                                        "</tbody></table>";
                                    console.log(inputs);
                                    document.getElementById("editProduct").innerHTML = inputs;
                                }
                                inputs += "<option value='" + allSupp[i].id + "'>Наименование: " + allSupp[i].name + ", " +
                                    "Почта: " + allSupp[i].mail + ", Адрес: страна- " + addressAllsSupp.country + ", " +
                                    "г." + addressAllsSupp.city + ", ул." + addressAllsSupp.street + ", здание/офис/кв " + addressAllsSupp.building + "" +
                                    "</option>";
                            });
                        }
                    }
                })

            });

        });
    });
}

function newProduct() {
    var newData="<table align='center'><tbody>" +
        "<tr><td>Наименование</td><td><input type='text' class='newName'></td></tr>"+
        "<tr><td>Раздел</td><td><input type='text' class='newType'></td></tr>"+
        "<tr><td>Стоимость</td><td><input type='text' class='newPrice'></td></tr>"+
        "<tr><td>Кол-во</td><td><input type='text' class='newCount'></td></tr>"+
        "<tr><td>Поставщик</td><td><select id='newProd'>";
    getInfoAboutProduct("http://"+document.location.host+"/allSuppliers",'json',function (supplier) {
        for (let i = 0; i < supplier.length; i++) {
            getInfoAboutProduct("http://" + document.location.host + "/address/" + parseInt(supplier[i].addressId),'json', function (addressAllsSupp){
                newData += "<option value='" + supplier[i].id + "'>Наименование: " + supplier[i].name + ", " +
                    "Почта: " + supplier[i].mail + ", Адрес: страна- " + addressAllsSupp.country + ", " +
                    "г." + addressAllsSupp.city + ", ул." + addressAllsSupp.street + ", здание/офис/кв " + addressAllsSupp.building + "" +
                    "</option>";
                if(i===supplier.length-1){

                    newData+="</select></td></tr>" +
                        "<tr><td>Описание</td><td><input type='text' class='newInfo'></td></tr>"+
                        "<tr><td></td><td></td></tr>"+
                        "<tr><td></td><td></td></tr>"+
                        "<tr><td><button class='create'>Создать</button></td><td></td></tr>"+
                        "</tbody></table>";
                    document.getElementById('newProduct').innerHTML=newData;
                }
            });

        }

    })

}
