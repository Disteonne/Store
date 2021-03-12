function inputHtml() {
    var inHtml = "<table class='table'><tbody>" +
        "<tr><td>Наименование товара</td><td><input class='NameProduct' type='text'></td></tr>" +
        "<tr><td>Тип товара</td><td><input class='TypeProduct' type='text'></td></tr>" +
        "<tr><td>Стоимость, Р</td><td><input class='PriceProduct' type='text'></td></tr>" +
        "<tr><td>Кол-во</td><td><input class='CountProduct' type='text'></td></tr>" +
        "<tr><td>Описание</td><td><input class='InfoProduct' type='text'></td></tr>" +
        "<tr><td>Наименование поставщика</td><td><input class='NameSupplier' type='text'></td></tr>" +
        "<tr><td>Почта поставщика</td><td><input class='MailSupplier' type='text'></td></tr>" +
        "<tr><td>Страна поставщика</td><td><input class='CountrySupplierAddress' type='text'></td></tr>" +
        "<tr><td>Город поставщика</td><td><input class='CitySupplierAddress' type='text'></td></tr>" +
        "<tr><td>Улица поставщика</td><td><input class='StreetSupplierAddress' type='text'></td></tr>" +
        "<tr><td>Здание/корпус/офис</td><td><input class='BuildingSupplierAddress' type='text'></td></tr>" +
        "<tr><td><button class='input-selector-one'>Send</button></td><td></td></tr>" +
        "</tbody></table>";
    document.getElementById('inputProduct').innerHTML = inHtml;
}

document.onclick = function (event) {
    if (event.target.classList.contains('input-selector-one')) {
        send();
    }
    if(event.target.classList.contains('logout')){
        window.location.replace('http://'+document.location.host+"/logout");
    }
    if(event.target.classList.contains('mainMenu')){
        window.location.replace("http://"+document.location.host+"/mainMenu.html");
    }
}

class NewProduct {
    productOldName;
    productNewName;
    type;
    price;
    count;
    info;

    supplierName;
    mail;
    country;
    city;
    street;
    building;

    constructor(nameP, nameN, typeP, priceP, countP, infoP, nameS, mailS, countrySA, citySA, streetSA, buildingSA) {
        this.productOldName = nameP;
        this.productNewName = nameN;
        this.type = typeP;
        this.price = priceP;
        this.count = countP;
        this.info = infoP;

        this.supplierName = nameS;
        this.mail = mailS;
        this.country = countrySA;
        this.city = citySA;
        this.street = streetSA;
        this.building = buildingSA;
    }
}

function send() {
    var obj = new NewProduct(document.querySelector('.NameProduct').value, "",
        document.querySelector('.TypeProduct').value,
        document.querySelector('.PriceProduct').value,
        document.querySelector('.CountProduct').value,
        document.querySelector('.InfoProduct').value,
        document.querySelector('.NameSupplier').value,
        document.querySelector('.MailSupplier').value,
        document.querySelector('.CountrySupplierAddress').value,
        document.querySelector('.CitySupplierAddress').value,
        document.querySelector('.StreetSupplierAddress').value,
        document.querySelector('.BuildingSupplierAddress').value);
    console.log(JSON.stringify(obj));
    sendToSpring(JSON.stringify(obj), "http://" + document.location.host + "/warehouse/new", 'PATCH');
    window.location.replace('http://' + document.location.host + '/newProduct.html');
}

function sendToSpring(jsonText, url, type) {

    $.ajax({
        type: type,
        contentType: 'application/json; charset=utf-8',
        url: url,
        data: jsonText, // Note it is important
        success: function (result) {
            // do what ever you want with data
        }
    });
}


inputHtml();
