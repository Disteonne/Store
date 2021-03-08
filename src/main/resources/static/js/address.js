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
selector();
var inputIn = document.querySelector('.input-in');
var button = document.querySelector('button');
var queryUrl = "http://" + document.location.host + "/addresses?page=";

function selector() {
    var search = "<table><tbody>" +
        "<tr><td>Страна</td><td><input class='country' type='text'></td></tr>" +
        "<tr><td>Город</td><td><input class='city' type='text'></td></tr>" +
        "<tr><td>Улица</td><td><input class='street' type='text'></td></tr>" +
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

class AddressPutDto {
    constructor(id, country, city, street, building) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building=building;
    }
}

var changeId;
document.onclick = function (event) {
    if (event.target.classList.contains('next')) {
        checkPage();
        page++;
        start(queryUrl);
    }
    if (event.target.classList.contains('prev')) {
        page--;
        start(queryUrl);
    }
    if (event.target.classList.contains('change')) {
        changeId = event.target.dataset.id;
        console.log(changeId);
        var changes = "<table><tbody>" +
            "<tr><td>Country</td><td><input class='changeCountry' type='text'></td></tr>" +
            "<tr><td>City</td><td><input class='changeCity' type='text'></td></tr>" +
            "<tr><td>Street</td><td><input class='changeStreet' type='text'></td></tr>" +
            "<tr><td>Building</td><td><input class='changeBuilding' type='text'></td></tr>" +
            "<tr><td><button class='changeAddress'>Change</button></td><td></td></tr>" +
            "</tbody>";
        document.getElementById("changeAdd").innerHTML = changes;
    }
    if (event.target.classList.contains('changeAddress')) {
        new AddressPutDto(changeId, document.querySelector('.changeCountry').value, document.querySelector('.changeCity').value,
            document.querySelector('.changeStreet').value, document.querySelector('.changeBuilding').value,"");
        sendToSpring(JSON.stringify(new AddressPutDto(changeId, document.querySelector('.changeCountry').value, document.querySelector('.changeCity').value,
            document.querySelector('.changeStreet').value, document.querySelector('.changeBuilding').value)),"/address/"+changeId);
        alert("Адрес изменен");
        window.location.reload();
    }
}

button.onclick = function () {
    var country = document.querySelector('.country').value;
    var city = document.querySelector('.city').value;
    var street = document.querySelector('.street').value;
    console.log(country);
    console.log(city);
    console.log(street);
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

function first() {
    start("http://" + document.location.host + "/addresses?page=");//стартуем с изначальным url
}

function sendToSpring(jsonText, url) {

    $.ajax({
        type: "PATCH",
        contentType: 'application/json; charset=utf-8',
        url: url,
        data: jsonText, // Note it is important
        success: function (result) {
            // do what ever you want with data
        }
    });
}

first();
