function getHistories(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';
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
var urlQuery = "";
output();

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

function outputHistory(page, url) {
    var tableHistory = "<table border='2' class='table'>" +
        "<thead>" +
        "<tr><th>ID</th><th>Date</th><th>Info</th><th>Sum</th></tr>" +
        "</thead>" +
        "<tbody>";
    getHistories(url + page, function (data) {
        if (data.length === 0) {
            page = 0
            var divFor = document.createElement("div");
            //для изменения глоб переменной
            document.body.appendChild(divFor);
            divFor.id = "isNull";
            outputHistory(page, urlQuery);
        } else {
            for (let i = 0; i < data.length; i++) {
                tableHistory += "<tr><td>" + data[i].id + "</td><td>"
                    + data[i].date + "</td><td>";
                var sum = 0;
                for (let j = 0; j < data[i].history.length; j++) {
                    tableHistory += "Наименование: " + data[i].history[j].name + ".\n Тип: " + data[i].history[j].type + ".\n Стоимость товара: " +
                        data[i].history[j].price + " Кол-во: " + data[i].history[j].count;
                    sum += data[i].history[j].price * data[i].history[j].count;
                }
                tableHistory += "</td><td>Сумма покупки: " + sum + "</td></tr>";
            }
            tableHistory += "</tbody></table>";
            drawButtons(page);

            document.getElementById('history').innerHTML = tableHistory;
        }
    })
}

function checkPage() {
    //если есть тег,добавленный в документ при получении пустой информации,то стр=0 и тег удаляется
    if (document.getElementById('isNull') !== null) {
        page = 0;
        if (document.getElementById('isNull') !== null) {
            document.getElementById('isNull').remove();
        }
    }
}

function drawButtons(numberPage) {
    var inputButton = '<table align="center">' + '<tr><th>';
    if (numberPage === 0) {
        inputButton += '<button id="next" class="pagination_next">Next</button></th>';
    } else {
        inputButton += '<th><button id="prev" class="pagination_prev">Prev</button></th><th>' +
            '<button id="next" class="pagination_next">Next</button></th>';
    }
    inputButton += '</tr>' +
        '</table>';
    document.getElementById('pagination').innerHTML = inputButton;
}

function output() {
    urlQuery = "/history?page="
    outputHistory(page, urlQuery);//покажем всех
}

document.onclick = function (event) {
    if (event.target.classList.contains('pagination_next')) {
        checkPage();
        page++;
        console.log(page);
        outputHistory(page, urlQuery);
    }
    if (event.target.classList.contains('pagination_prev')) {
        page--;
        outputHistory(page, urlQuery);
    }
    if(event.target.classList.contains('headers')){
        getSelector();
    }
    if(event.target.classList.contains('input-selector-one')){
        urlQuery = "/history?date=" + document.querySelector('.input-in').value + "&page=";
        outputHistory(page, urlQuery);
    }
    if(event.target.classList.contains('logout')){
        window.location.replace('http://'+document.location.host+"/logout");
    }
    if(event.target.classList.contains('mainMenu')){
        window.location.replace("http://"+document.location.host+"/mainMenu.html");
    }
}
