function inputHtml() {
    var inHtml = "<table><tbody>" +
        "<tr><td>NameProduct</td><td><input class='NameProduct' type='text'></td></tr>" +
        "<tr><td><button class='sendToSpring'>Send</button></td><td></td></tr>" +
        "</tbody></table>";
    document.getElementById('deleteProduct').innerHTML=inHtml;
}

document.onclick = function (event) {
    if (event.target.classList.contains('sendToSpring')) {
        send();
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
    if(confirm("Товар с наименованием: "+ document.querySelector('.NameProduct').value +"будет удален из базы.Вы уверены?")) {
        console.log(JSON.stringify(obj));
        sendToSpring(JSON.stringify(obj),"http://"+document.location.host+"/warehouse/delete",'DELETE');
        alert("Товар удален.")
        window.location.replace('http://' + document.location.host + '/deleteProduct.html');
    }

}

function sendToSpring(jsonText,url,type) {

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
