//const newButton = document.getElementById('newButtonProduct');

function getProductInfo() {
    var inp = "<table>" +
        "<tr>" +
        "<td>" +
        "<input id='input' class='input-in' type=\"text\" style='background-color: #fafafa;" +
        "  border:0;" +
        "  box-shadow:0 0 4px rgba(0,0,0,0.3);" +
        "  transition: .3s box-shadow;" +
        "  width: 600px ; height: 35px '" + "></td>" +
        "<td><button>Input</button></td></tr></table>";

    document.getElementById('inputText').innerHTML = inp;

    var inputIn = document.querySelector('.input-in');
    var button = document.querySelector('button');

    button.onclick = function () {
        var url = inputIn.value;
        console.log(url);

        // var url = document.location.href


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

        //'http://localhost:8081/products?page=0&size=6'
        var listProduct = getProductJson(url,
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
}
getProductInfo();

//newButton.addEventListener('click', getProductInfo);
