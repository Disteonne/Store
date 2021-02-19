var getProductJson = function (url, callback) {
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

var listProduct = getProductJson('http://localhost:8080/products',
    function (data) {
        console.log(data)
        var table = "<table border='2'>" +
            "<tr><td>ID</td><td>Наименование</td><td>Раздел</td><td>Стоимость</td><td>Кол-во</td>" +
            "<td>ID поставщика</td><td>Описание товара</td></tr>";
        for (var iter = 0; iter < data.length; iter++) {
            table += "<tr><td>" + data[iter].id + "</td><td>"
                + data[iter].name + "</td><td>"
                + data[iter].type + "</td><td>"
                + data[iter].price + "</td><td>"
                + data[iter].count + "</td><td>"
                + data[iter].supplierId + "</td><td>"
                + data[iter].info + "</td></tr>"
        }
        table += "</table>";
        document.getElementById('all_product').innerHTML = table;
    });
