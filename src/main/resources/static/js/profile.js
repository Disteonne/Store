var userInfo = function (url, callback) {
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

function profile(){
    userInfo("http://"+document.location.host+"/user/current",function (data) {
        var table="<table border='2' class='table'>" +
            "<thead>" +
            "<tr><th>Поля</th><th>Информация</th><th></th><tbody>"+
            "<tr><td>id</td><td>"+data.id+"</td><td></td></tr>"+
            "<tr><td>Name</td><td>"+data.name+"</td><td><input id='input' class='input-in' type='text'><button id='name' class='changeName'>Изменить</button></td></tr>"+
            "<tr><td>Surname</td><td>"+data.surname+"</td><td><input id='input' class='input-in' type='text'><button id='surname' class='change'>Изменить</button></td></tr>"+
            "<tr><td>Age</td><td>"+data.age+"</td><td><input id='input' class='input-in' type='text'><button id='age' class='age'>Изменить</button></td></tr>"+
            "<tr><td>Login</td><td>"+data.login+"</td><td><input id='input' class='input-in' type='text'><button id='login' class='change'>Изменить</button></td></tr>"+
            "<tr><td>Password</td><td>********</td><td><input id='input' class='input-in' type='text'><button id='password' class='change'>Изменить</button></td></tr></tbody>"+
            "</table>";
        document.getElementById('info').innerHTML=table;
    });
}
document.onclick=function (event){
    if(event.target.classList.contains('changeName')){
        patch(event.target.dataset.id);
    }
}
function patch(id) {
    var inputText=new Map();

    if(id==="name"){
        inputText.set("name",document.querySelector('input-in'));
    }
    if(id==="surname"){
        inputText.set("surname",document.querySelector('input-in'));
    }
    if(id==="age"){
        inputText.set("age",document.querySelector('input-in'));
    }
    if(id==="login"){
        inputText.set("login",document.querySelector('input-in'));
    }
    if(id==="password"){
        inputText.set("password",document.querySelector('input-in'));
    }
   sendToSpring(JSON.stringify(inputText));
}
function sendToSpring(jsonText) {

    $.ajax({
        type: "PATCH",
        contentType: 'application/json; charset=utf-8',
        url: "/user/current",
        data: jsonText, // Note it is important
        success: function (result) {
            // do what ever you want with data
        }
    });
}
profile();