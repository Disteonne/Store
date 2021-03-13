function getAddresses(url, callback) {
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
//selector();
var inputIn = document.querySelector('.input-in');
var button = document.querySelector('button');
var queryUrl = "http://" + document.location.host + "/addresses?page=";

function selector() {
    var search = "<br><table><tbody>" +
        "<tr><td>Страна</td><td><input class='country' type='text'></td></tr>" +
        "<tr><td>Город</td><td><input class='city' type='text'></td></tr>" +
        "<tr><td>Улица</td><td><input class='street' type='text'></td></tr>" +
        "<tr><td></td><td></td></tr>" +
        "<tr><td></td><td></td></tr>" +
        "<tr><td></td><td></td></tr>" +
        "<tr><td><button class='search'>Search</button></td><td></td></tr>" +
        "</tbody></table>";

    document.getElementById('search').innerHTML = search;
}

function start(url) {
    var startTable = "<table border='2' class='table'><thead><th>Страна</th><th>Город</th><th>Улица</th><th>Здание/кв</th><th></th></thead><tbody>";
    getAddresses(url + page, function (data) {
        if (data.length === 0) {
            page = 0
            var divFor = document.createElement("div");
            //для изменения глоб переменной
            document.body.appendChild(divFor);
            divFor.id = "isNull";
            start(url);
        }
        for (let i = 0; i < data.length; i++) {
            startTable += "<tr><td>" + data[i].country + "</td><td>" + data[i].city + "</td><td>"
                + data[i].street + "</td><td>" + data[i].building + "</td><td><button class='change' data-id=\"" + data[i].id + "\">Изменить</button></td></tr>";
        }
        document.getElementById("address").innerHTML = startTable;
        drawButtons(page);
    });
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

class AddressPutDto {
    constructor(id, country, city, street, building) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
    }
}

var changeId;
document.onclick = function (event) {
    if (event.target.classList.contains('headers')) {
        selector();
    }
    if (event.target.classList.contains('pagination_next')) {
        checkPage();
        page++;
        start(queryUrl);
    }
    if (event.target.classList.contains('pagination_prev')) {
        page--;
        start(queryUrl);
    }
    if (event.target.classList.contains('change')) {
        changeId = event.target.dataset.id;
        getAddresses("http://" + document.location.host + "/address/" + changeId, function (data) {
            console.log(changeId);
            var changes = "<table align='center'><tbody>" +
                "<tr><td>Страна</td><td><input class='changeCountry' type='text' value='" + data.country + "'></td></tr>" +
                "<tr><td>Город</td><td><input class='changeCity' type='text' value='" + data.city + "'></td></tr>" +
                "<tr><td>Улица</td><td><input class='changeStreet' type='text' value='" + data.street + "'></td></tr>" +
                "<tr><td>Здание/офис/кв</td><td><input class='changeBuilding' type='text' value='" + data.building + "'></td></tr>" +
                "<tr><td><button class='changeAddress'>Change</button></td><td></td></tr>" +
                "</tbody><br>";
            document.getElementById("changeAdd").innerHTML = changes;
        });
    }
    if (event.target.classList.contains('changeAddress')) {
        sendToSpring(
            JSON.stringify(
                new AddressPutDto(changeId,
                    document.querySelector('.changeCountry').value,
                    document.querySelector('.changeCity').value,
                    document.querySelector('.changeStreet').value,
                    document.querySelector('.changeBuilding').value)),
            "http://" + document.location.host + "/address");
        alert("Адрес изменен");
        window.location.replace("http://" + document.location.host + "/address.html");
    }
    if (event.target.classList.contains('search')) {
        var country = document.querySelector('.country').value;
        var city = document.querySelector('.city').value;
        var street = document.querySelector('.street').value;
        if (country === "" && city === "" && street === "") {
            //street(queryUrl);
            queryUrl = "http://" + document.location.host + "/addresses?page=";
        } else if (country !== "" && city === "" && street === "") {
            queryUrl = "http://" + document.location.host + "/addresses?country=" + country + "&page=";
        } else if (country === "" && city !== "" && street === "") {
            queryUrl = "http://" + document.location.host + "/addresses?city=" + city + "&page=";
        } else if (country === "" && city === "" && street !== "") {
            queryUrl = "http://" + document.location.host + "/addresses?street=" + street + "&page=";
        } else if (country !== "" && city !== "" && street === "") {
            queryUrl = "http://" + document.location.host + "/addresses?country=" + country + "&city=" + city + "&page=";
        } else if (country === "" && city !== "" && street !== "") {
            queryUrl = "http://" + document.location.host + "/addresses?city=" + city + "&street=" + street + "&page=";
        } else if (country !== "" && city === "" && street !== "") {
            queryUrl = "http://" + document.location.host + "/addresses?country=" + country + "&street=" + street + "&page=";
        } else if (country !== "" && city !== "" && street !== "") {
            queryUrl = "http://" + document.location.host + "/addresses?country=" + country + "&city=" + city + "&street=" + street + "&page=";
        }
        start(queryUrl);
    }
    if (event.target.classList.contains('logout')) {
        window.location.replace('http://' + document.location.host + "/logout");
    }
    if (event.target.classList.contains('mainMenu')) {
        window.location.replace("http://" + document.location.host + "/mainMenu.html");
    }
    if (event.target.classList.contains('newAddress')) {
        var newAddress = "<table align='center'><tbody>" +
            "<tr><td>Страна</td><td><input class='newCountry' type='text'></td></tr>" +
            "<tr><td>Город</td><td><input class='newCity' type='text'></td></tr>" +
            "<tr><td>Улица</td><td><input class='newStreet' type='text'></td></tr>" +
            "<tr><td>Здание/офис/кв</td><td><input class='newBuilding' type='text'></td></tr>" +
            "<tr><td><button class='add'>Add</button></td><td></td></tr>" +
            "</tbody>";
        document.getElementById("newAddress").innerHTML = newAddress;
    }
    if (event.target.classList.contains('add')) {
        sendNewAddress(JSON.stringify(new Address(document.querySelector('.newCountry').value,
            document.querySelector('.newCity').value,
            document.querySelector('.newStreet').value,
            document.querySelector('.newBuilding').value)), "http://" + document.location.host + "/address")
        alert("Адрес добавлен");
        window.location.replace("http://" + document.location.host + "/address.html");
    }
}

function first() {
    start("http://" + document.location.host + "/addresses?page=");//стартуем с изначальным url
}

function sendToSpring(jsonText, url) {
    $.ajax({
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        url: url,
        data: jsonText,
        success: function (result) {
            // do what ever you want with data
        }
    });
}

function sendNewAddress(jsonText, url) {
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: url,
        data: jsonText, // Note it is important
        success: function (result) {
            // do what ever you want with data
        }
    });
}


class Address {
    constructor(country, city, street, building) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
    }
}

first();
