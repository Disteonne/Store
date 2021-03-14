function getProductByName(url, callback) {
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

function inputHtml() {
    var inHtml = "<table class='table'><tbody>" +
        "<tr><td>Наименование товара</td><td><input class='NameProduct' type='text'></td></tr>" +
        "<tr><td><button class='input-selector-one'>Send</button></td><td></td></tr>" +
        "</tbody></table>";
    document.getElementById('deleteProduct').innerHTML=inHtml;
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

class deleteProduct {
    name;
    constructor(productName) {
        this.name=productName;
    }
}

function send() {
    var obj = new deleteProduct(document.querySelector('.NameProduct').value);
    if(confirm("Товар с наименованием: "+ document.querySelector('.NameProduct').value +" будет удален из базы.Вы уверены?")) {
        console.log(JSON.stringify(obj));
        ///product/{name}
        getProductByName('http://'+document.location.host+"/product?name="+obj.name,function (data) {
            sendToSpring("http://"+document.location.host+"/warehouse/delete/"+parseInt(data.id))
        });
        //sendToSpring(JSON.stringify(obj),"http://"+document.location.host+"/warehouse/delete",'DELETE');
        alert("Товар удален.")
        window.location.replace('http://' + document.location.host + '/deleteProduct.html');
    }

}

function sendToSpring(url) {

    $.ajax({
        type: "DELETE",
        contentType: 'application/json; charset=utf-8',
        url: url,
        //data: jsonText, // Note it is important
        success: function (result) {
            // do what ever you want with data
        }
    });
}


inputHtml();
