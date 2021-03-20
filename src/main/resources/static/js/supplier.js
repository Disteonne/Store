function get(url, callback) {
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

function selector() {

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

var queryUrl = "http://" + document.location.host + "/suppliers?page="

function output(url) {
    var startTable = "<table border='2' class='table'><thead><th>Наименование</th>" +
        "<th>Почта</th><th>Страна</th><th>Город</th><th>Улица</th><th>Здание/кв</th><th></th></thead><tbody>";
    get(url + page, function (data) {
        if (data.length === 0) {
            page = 0
            var divFor = document.createElement("div");
            //для изменения глоб переменной
            document.body.appendChild(divFor);
            divFor.id = "isNull";
            output(url);
        }
        for (let i = 0; i < data.length; i++) {
            startTable += "<tr><td>" + data[i].name + "</td><td>" + data[i].mail + "</td><td>" + data[i].country + "</td><td>"
                + data[i].city + "</td><td>" + data[i].street + "</td><td>" + data[i].building + "</td>" +
                "<td><button class='change' data-id=\"" + data[i].id + "\">Изменить</button> </td></tr>";
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

document.onclick = function (event) {
    if (event.target.classList.contains('pagination_next')) {
        checkPage();
        page++;
        output(queryUrl);
    }
    if (event.target.classList.contains('pagination_prev')) {
        page--;
        output(queryUrl);
    }
    if (event.target.classList.contains('headers')) {
        selector();
    }
    if (event.target.classList.contains('input-selector-one')) {
        queryUrl = "http://" + document.location.host + "/suppliers?nameLike=" + document.querySelector('.input-in').value + "&page=";
        output(queryUrl);
    }
    if (event.target.classList.contains('logout')) {
        window.location.replace('http://' + document.location.host + "/logout");
    }
    if (event.target.classList.contains('mainMenu')) {
        window.location.replace("http://" + document.location.host + "/mainMenu.html");
    }
    if (event.target.classList.contains('change')) {
        inputsAndList(event.target.dataset.id);
    }
    if (event.target.classList.contains('save')) {
        console.log(JSON.stringify(new SupplierDto(event.target.dataset.id,
            document.querySelector('.name').value,
            document.querySelector('.mail').value,
            document.getElementById('select').value)));
        send(JSON.stringify(new SupplierDto(event.target.dataset.id,
            document.querySelector('.name').value,
            document.querySelector('.mail').value,
            document.getElementById('select').value)), "http://" + document.location.host + "/supplier", "PUT");

        alert('Измненено.')
        window.location.replace("http://" + document.location.host + "/supplier.html")
    }
    if (event.target.classList.contains('newSupplier')) {
        newSupplier();
    }
    if (event.target.classList.contains('savePost')) {
        console.log(JSON.stringify(new SupplierDto(null,document.querySelector('.nameOfNewSupp').value,
            document.querySelector('.mailOfNewSupp').value,
            document.getElementById('selectNewSupp').value)));
        send(JSON.stringify(new SupplierDto(null,document.querySelector('.nameOfNewSupp').value,
            document.querySelector('.mailOfNewSupp').value,
            document.getElementById('selectNewSupp').value)), "http://" + document.location.host + "/supplier", "POST");
        alert('Добавлено.');
        window.location.replace("http://" + document.location.host + "/supplier.html")
    }
}

class SupplierDto {
    constructor(id, name, mail, addressId) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.addressId = addressId;
    }
}

var select;

function inputsAndList(id) {
    get("http://" + document.location.host + "/supplier/" + id, function (data) {

        var inputs = "<table align='center'><body>" +
            "<tr><td>Наименование</td><td><input class='name' type='text' value='" + data.name + "'></td></tr>" +
            "<tr><td>Почта</td><td><input class='mail' type='text' value='" + data.mail + "'></td></tr>" +
            "<tr><td>Адрес</td><td>" +
            "<select id='select'>";
        get("http://" + document.location.host + "/address/" + data.addressId, function (currentAddress) {
            inputs += "<option value='" + currentAddress.id + "'>Страна: " + currentAddress.country + ", " +
                "Город: " + currentAddress.city + ", Улица: " + currentAddress.street + ", Здание/офис/кв: " + currentAddress.building + "</option>";

            get("http://" + document.location.host + "/address", function (all) {
                for (var i = 0; i < all.length; i++) {
                    if (all[i].country !== currentAddress.country && all[i].city !== currentAddress.city &&
                        all[i].street !== currentAddress.street && all[i].building !== currentAddress.building) {
                        inputs += "<option value='" + all[i].id + "'>Страна: " + all[i].country + ", " +
                            "Город: " + all[i].city + ", Улица: " + all[i].street + ", Здание/офис/кв: " + all[i].building + "</option>";
                    }
                }
                inputs += "</option></select></td></tr>" +
                    "<tr><td></td><td><button class='save' data-id='" + id + "'>Отправить</button></td></tr>" +
                    "</body>" +
                    "</table>";

                document.getElementById('edit').innerHTML = inputs;
            })

        })
        //document.getElementById('warehouse').innerHTML=inputs;
    })
}

function newSupplier() {
    var inputs = "<table align='center'><tbody>" +
        "<tr><td>Наименование</td><td><input type='text' class='nameOfNewSupp'></td></tr>" +
        "<tr><td>Почта</td><td><input type='text' class='mailOfNewSupp'></td></tr>" +
        "<tr><td>Адрес</td><td><select id='selectNewSupp'>";
    get("http://" + document.location.host + "/address", function (all) {
        for (let i = 0; i < all.length; i++) {
            inputs += "<option value='" + all[i].id + "'>Страна: " + all[i].country + ", " +
                "Город: " + all[i].city + ", Улица: " + all[i].street + ", Здание/офис/кв: " + all[i].building + "</option>";
        }
        inputs += "</option></select></td></tr>" +
            "<tr><td></td><td><button class='savePost'>Сохранить</button></td></tr>" +
            "</body>" +
            "</table>";

        document.getElementById('newSupp').innerHTML = inputs;

    });

}

function send(jsonText, url, query) {
    $.ajax({
        type: query,
        contentType: 'application/json; charset=utf-8',
        url: url,
        data: jsonText, // Note it is important
        success: function (result) {
            // do what ever you want with data
        }
    });
}

output(queryUrl);