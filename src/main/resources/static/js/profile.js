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
        if (flag === false) {
            var table = "<table align='center' border='2'  class='table'>" +
                "<thead>" +
                "<tr><th>Поля</th><th>Информация</th></tr><tbody>" +
                "<tr><td>id</td><td>" + data.userId + "</td></tr>" +
                "<tr><td>Name</td><td>" + data.name + "</td></tr>" +
                "<tr><td>Surname</td><td>" + data.surname + "</td></tr>" +
                "<tr><td>Age</td><td>" + data.age + "</td></tr>" +
                "<tr><td>Login</td><td>" + data.login + "</td><td></tr>" +
                "<tr><td>Login</td><td>" + data.mail + "</td><td></tr>" +
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
        } else {
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
    if (event.target.classList.contains('password')) {
        password();
    }
    if (event.target.classList.contains('sendPassword')) {
        sendPassword();
    }
    if (event.target.classList.contains('delete')) {
        deleteUser();
    }
    if (event.target.classList.contains("logout")) {
        sendLogout();
    }
    if (event.target.classList.contains("mainMenu")) {
        window.location.replace("http://" + document.location.host + "/mainMenu.html");
    }
}

function password() {
    var inputs = "<table align='center'><tbody>" +
        "<tr><td>Пароль</td><td><input class='newPassword' type='text'></td></tr>" +
        "<tr><td><button class='sendPassword'>Send</button></td><td></td></tr>" +
        "</tbody></table>";
    //inputs+="<button class='sendPassword'>Send</button>";
    document.getElementById('actionsPassword').innerHTML = inputs;
    //document.getElementById('actionsPassword').innerHTML=send;
}

function deleteUser() {
    userProfile("http://" + document.location.host + "/profile", function (data) {
        sendToSpring(JSON.stringify(""), "http://" + document.location.host + "/profile/delete/" + parseInt(data.userId), "delete");
        if (confirm("Вы уверены?")) {
            window.location.replace('http://' + document.location.host + '/logout');
        }
    })
}

function sendMain(userId) {
    var nameValue = document.querySelector('.name');
    var surnameValue = document.querySelector('.surname');
    var ageValue = document.querySelector('.age');
    var loginValue = document.querySelector('.login');
    var mailValue = document.querySelector('.mail');
    var countryValue = document.querySelector('.country');
    var cityValue = document.querySelector('.city');
    var streetValue = document.querySelector('.street');
    var buildingValue = document.querySelector('.building');
    console.log(JSON.stringify(new UserDto(userId, nameValue.value, surnameValue.value, ageValue.value, loginValue.value,mailValue.value,null, countryValue.value, cityValue.value,
        streetValue.value, buildingValue.value)));
    sendToSpring(JSON.stringify(new UserDto(userId, nameValue.value, surnameValue.value, ageValue.value, loginValue.value,mailValue.value,null, countryValue.value, cityValue.value,
        streetValue.value, buildingValue.value)), "http://" + document.location.host + "/profile", "PUT");
    alert("Изменено!");
    window.location.replace('http://' + document.location.host + '/profile.html');//редирект
}

function sendPassword() {
    var passwordValue = document.querySelector('.newPassword');
    if (passwordValue.value === "") {
        alert("Пароль не был изменен!")
    } else {
        alert("Пароль изменен!")
        sendToSpring(JSON.stringify(new PasswordDto(passwordValue.value)), "/profile/password", "PATCH");
        window.location.replace('http://' + document.location.host + "/logout");
    }
}

class UserDto {
    constructor(id, name, surname, age, login,mail,addressId, country, city, street, building) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.mail=mail;
        this.password="";
        this.addressId=addressId;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
    }
}

function main() {
    userProfile("http://" + document.location.host + "/profile", function (data) {
        var inputs = "<table align='center'><tbody>" +
            "<tr><td>Имя</td><td><input class='name' type='text' value='" + data.name + "'></td></tr>" +
            "<tr><td>Фамилия</td><td><input class='surname' type='text' value='" + data.surname + "'></td></tr>" +
            "<tr><td>Возраст</td><td><input class='age' type='text' value='" + data.age + "'></td></tr>" +
            "<tr><td>Логин</td><td><input class='login' type='text' value='" + data.login + "'></td></tr>" +
            "<tr><td>Доп.почта</td><td><input class='mail' type='text' value='" + data.mail + "'></td></tr>" +
            "<tr><td>Страна</td><td><input class='country' type='text' value='" + data.country + "'></td></tr>" +
            "<tr><td>Город</td><td><input class='city' type='text' value='" + data.city + "'></td></tr>" +
            "<tr><td>Улица</td><td><input class='street' type='text' value='" + data.street + "'></td></tr>" +
            "<tr><td>Здание/офис/кв</td><td><input class='building' type='text' value='" + data.building + "'></td></tr>" +
            "<tr><td><button class='sendMain'>Send</button></td></tr>"
        "</tbody>";
        document.getElementById('actionsMain').innerHTML = inputs;
    })

}

class PasswordDto {
    constructor(password) {
        this.password = password;
    }
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

profile(false);