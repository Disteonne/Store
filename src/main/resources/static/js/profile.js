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
            "<tr><td>Name</td><td>"+data.name+"</td><td><input id='input1' class='input-in' type='text'><button data-id='name' class='changeName'>Изменить</button></td></tr>"+
            "<tr><td>Surname</td><td>"+data.surname+"</td><td><input id='input2' class='input-in surname' type='text'><button data-id='surname' class='changeSurname'>Изменить</button></td></tr>"+
            "<tr><td>Age</td><td>"+data.age+"</td><td><input id='input3' class='input-in age' type='text'><button data-id='age' class='changeAge'>Изменить</button></td></tr>"+
            "<tr><td>Login</td><td>"+data.login+"</td><td><input id='input4' class='input-in login' type='text'><button data-id='login' class='changeLogin'>Изменить</button></td></tr>"+
            "<tr><td>Password</td><td>********</td><td><input id='input5' class='input-in password' type='text'><button data-id='password' class='changePassword'>Изменить</button></td></tr></tbody>"+
            "</table>";
        document.getElementById('info').innerHTML=table;
    });
}
document.onclick=function (event){
    if(event.target.classList.contains('changeName')){
        console.log(event.target.dataset.id);
        patch(event.target.dataset.id);
    }
    if(event.target.classList.contains('changeSurname')){
        patch(event.target.dataset.id);
    }
    if(event.target.classList.contains('changeAge')){
        patch(event.target.dataset.id);
    }
    if(event.target.classList.contains('changeLogin')){
        patch(event.target.dataset.id);
    }
    if(event.target.classList.contains('changePassword')){
        patch(event.target.dataset.id);
    }
}
function patch(id) {
    var inputText=new Map();
    var value;
    if(id==="name"){
        value=document.querySelector('.input-in')
        inputText.set("name",value.value);
    }
    if(id==="surname"){
        value=document.querySelector('.input-in surname')
        inputText.set("surname",value.value);
    }
    if(id==="age"){
        value=document.querySelector('.input-in age')
        inputText.set("age",value.value);
    }
    if(id==="login"){
        value=document.querySelector('.input-in login')
        inputText.set("login",value.value);
    }
    if(id==="password"){
        value=document.querySelector('.input-in password')
        inputText.set("password",value.value);
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