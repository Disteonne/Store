function getSuppliers(url, callback) {
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
var page=0;

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

button.onclick=function() {
    queryUrl="http://"+document.location.host+"/suppliers?nameLike="+inputIn.value+"&page=";
    output(queryUrl);
}

var queryUrl="http://"+document.location.host+"/suppliers?page="

function output(url) {
    var startTable = "<table border='2' class='table'><thead><th>Наименование</th><th>Почта</th><th>Страна</th><th>Город</th><th>Улица</th><th>Здание/кв</th></thead><tbody>";
    getSuppliers(url+page,function (data){
        console.log(JSON.stringify(data));
        if(data.length===0){
            page = 0
            var divFor = document.createElement("div");
            //для изменения глоб переменной
            document.body.appendChild(divFor);
            divFor.id = "isNull";
            output(url);
        }
        for (let i = 0; i < data.length; i++) {
            startTable += "<tr><td>" + data[i].name + "</td><td>" + data[i].mail + "</td><td>" + data[i].country + "</td><td>"
                +data[i].city+"</td><td>"+data[i].street+"</td><td>"+data[i].building+"</td></tr>";
            console.log(JSON.stringify(data[i]));
        }
        document.getElementById("supplier").innerHTML = startTable;
        drawButtons(page);
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
    var inputButton = '<table>' + '<tr><th>';
    if (numberPage === 0) {
        inputButton += '<button id="next" class="next">Next</button></th>';
    } else {
        inputButton += '<th><button id="prev" class="prev">Prev</button></th><th>' +
            '<button id="next" class="next">Next</button></th>';
    }
    inputButton += '</tr>' +
        '</table>';
    document.getElementById('pagination').innerHTML = inputButton;
}

document.onclick = function (event) {
    if (event.target.classList.contains('next')) {
        checkPage();
        page++;
        output(queryUrl);
    }
    if (event.target.classList.contains('prev')) {
        page--;
        output(queryUrl);
    }
}
output(queryUrl);