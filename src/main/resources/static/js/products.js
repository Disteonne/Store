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

var page = 0;
var maxPage = 0;
var countPage = 0;

var functionByUrl = function (url, type) {
    if (type === 'json') {
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
    if (type === 'text') {
        getInfoAboutProduct(url, type, function (data) {
            console.log(data)
            maxPage = Math.round(data / 5);
            console.log(maxPage)
            return maxPage;
        });
    }
}

var result =0;
var resultPage = function getMax(url, type) {
     getInfoAboutProduct(url, type, function (data) {
        result = data; //возвращает кол-во всех объектов/товаров
        console.log(result) //все ок, выводит правильное значение
    })
    console.log(result); // выводит 0 (глоб переменную)
    return result;
}


var pagination = function (count) {
    var forButton = '<table>' + '<tr><th>';
    if (count === 0) {
        forButton += '<button>Next</button></th>'
    } else {
        forButton += '<th><button>Prev</button></th><th>' +
            '<button>Next</button></th>';
    }
    forButton += '</tr>' +
        '</table>';
    document.getElementById('pagination').innerHTML = forButton;
}
//переменная для счетчика
//var count = 0;

function getProductInfo() {

    //кнопка для поиска

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

    functionByUrl('http://' + urlHost + '/products?page=0&size=6', 'json');
    var k = resultPage('http://' + urlHost + '/count', 'text');
    console.log(k)

    console.log(maxPage);
    button.onclick = function () {
        var searchName = inputIn.value;
        console.log(searchName);
        var newUrl = 'http://' + urlHost + '/products?nameLike=' + searchName + '&page=' + 0;//&size=6
        functionByUrl(newUrl, 'json');
    }

}

getProductInfo();

