var userProfile = function (url, callback) {
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

function profile(flag) {
    userProfile("http://" + document.location.host + "/profile", function (data) {
        if(flag===false) {
            var table = "<table align='center' border='2'  class='table'>" +
                "<thead>" +
                "<tr><th>Поля</th><th>Информация</th></tr><tbody>" +
                "<tr><td>id</td><td>" + data.userId + "</td></tr>" +
                "<tr><td>Name</td><td>" + data.name + "</td></tr>" +
                "<tr><td>Surname</td><td>" + data.surname + "</td></tr>" +
                "<tr><td>Age</td><td>" + data.age + "</td></tr>" +
                "<tr><td>Login</td><td>" + data.login + "</td><td></tr>" +
                "<tr><td>Country</td><td>" + data.country + "</td></tr>" +
                "<tr><td>City</td><td>" + data.city + "</td></tr>" +
                "<tr><td>Street</td><td>" + data.street + "</td></tr>";
            if (data.building != null) {
                table += "<tr><td>Country</td><td>" + data.building + "</td></tr>";
            }
            table += "</tbody></table>";


            var actions = "<table align='center'><tr><td><button class='main'>Редактировать профиль</button></td>" +
                "<td><button class='password'>Изменить пароль</button></td>" +
                "<td><button class='delete'>Удалить профиль</button></td>" +
                "<td><button class='logout' type='button'>Logout</button></td>" +
                "<td><button class='mainMenu' type='button'>Главное меню</button></td></tr></table>";
            document.getElementById('info').innerHTML = table;
            document.getElementById('actions').innerHTML = actions;
        }
        else {
            sendMain(data.userId);
        }
    });
}

document.onclick = function (event) {
    if (event.target.classList.contains('main')) {
        main();
        //patch(event.target.dataset.id);
    }
    if (event.target.classList.contains('sendMain')) {
        profile(true);
    }
    if(event.target.classList.contains('password')){
        password();
    }
    if(event.target.classList.contains('sendPassword')){
        sendPassword();
    }
    if(event.target.classList.contains('delete')){
        deleteUser();
    }
    if(event.target.classList.contains("logout")){
        sendLogout();
    }
    if(event.target.classList.contains("mainMenu")){
        window.location.replace("http://"+document.location.host+"/mainMenu.html");
    }
}

    /*
    Адрес изменяется
    1 юзер-1 адрес
    но 1 адрес-не 1 юзер
    тут стоит  создавать новый адрес и проверять есть ли такой в бд
    если да.то брать айди и вставлять
    если нет-создавать новый в бд и вставлять
     */
function password() {
    var inputs = "<table><tbody>" +
        "<tr><td>Password</td><td><input class='newPassword' type='text'></td></tr>"+
        "<tr><td><button class='sendPassword'>Send</button></td><td></td></tr>"+
        "</tbody></table>";
    //inputs+="<button class='sendPassword'>Send</button>";
    document.getElementById('actionsPassword').innerHTML=inputs;
    //document.getElementById('actionsPassword').innerHTML=send;
}
function deleteUser() {
    userProfile("http://" + document.location.host + "/profile",function (data){
        sendToSpring(JSON.stringify(""),"http://" + document.location.host + "/user/delete/"+parseInt(data.userId),"delete");
        if(confirm("Вы уверены?")) {
            window.location.replace('http://' + document.location.host + '/login');
        }
    })
}
function sendMain(userId) {
    var nameValue = document.querySelector('.name');
    var surnameValue = document.querySelector('.surname');
    var ageValue = document.querySelector('.age');
    var loginValue = document.querySelector('.login');
    var countryValue = document.querySelector('.country');
    var cityValue = document.querySelector('.city');
    var streetValue = document.querySelector('.street');
    var buildingValue = document.querySelector('.building');
    sendToSpring(JSON.stringify(new UserProfilePatchDto(nameValue.value,surnameValue.value,ageValue.value,loginValue.value,countryValue.value,cityValue.value,
        streetValue.value,buildingValue.value)),"/profile/"+userId,"PATCH");
    alert("Изменено!");
    window.location.replace('http://'+document.location.host+'/profile.html');//редирект
}
function sendPassword() {
    var passwordValue = document.querySelector('.newPassword');
    if(passwordValue.value===""){
        alert("Пароль не был изменен!")
    }else {
        alert("Пароль изменен!")
        sendToSpring(JSON.stringify(new PasswordDto(passwordValue.value)), "/profile/password","PATCH");
        window.location.replace('http://' + document.location.host + '/login');
    }
}
class UserProfilePatchDto {
    constructor(name, surname, age, login, country, city, street, building) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
    }
}

function main() {
    var inputs = "<table><tbody>" +
        "<tr><td>Name</td><td><input class='name' type='text'></td></tr>" +
        "<tr><td>Surname</td><td><input class='surname' type='text'></td></tr>" +
        "<tr><td>Age</td><td><input class='age' type='text'></td></tr>" +
        "<tr><td>login</td><td><input class='login' type='text'></td></tr>" +
        "<tr><td>Country</td><td><input class='country' type='text'></td></tr>" +
        "<tr><td>City</td><td><input class='city' type='text'></td></tr>" +
        "<tr><td>Street</td><td><input class='street' type='text'></td></tr>" +
        "<tr><td>Building</td><td><input class='building' type='text'></td></tr>" +
        "<tr><td><button class='sendMain'>Send</button></td></tr>"
    "</tbody>";
    document.getElementById('actionsMain').innerHTML = inputs;
}
class PasswordDto{
    constructor(password) {
        this.password=password;
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

function sendLogout() {
    window.location.replace('http://'+document.location.host+"/logout");
}
profile(false);