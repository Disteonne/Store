function inputs() {
    var data = "<table align='center'><tbody>" +
        "<tr><td>Фамилия</td><td><input type='text' class='surname'></td></tr>" +
        "<tr><td>Имя</td><td><input type='text' class='name'></td></tr>" +
        "<tr><td>Возраст</td><td><select class='age' id='age'>";
    for (let i = 18; i < 101; i++) {
        data += "<option value='" + i + "'>" + i + "</option>";
    }
    data += "</select></td></tr>" +
        "<tr><td>Логин(основная эл.почта)</td><td><input type='text' class='login'></td></tr>" +
        "<tr><td>Пароль</td><td><input type='password' class='password'></td></tr>" +
        "<tr><td>Доп.почта</td><td><input type='text' class='mail'></td></tr>" +
        "<tr><td>Адрес</td><td></td></tr>" +
        "<tr><td>Страна</td><td><input type='text' class='country'></td></tr>" +
        "<tr><td>Город</td><td><input type='text' class='city'></td></tr>" +
        "<tr><td>Улица</td><td><input type='text' class='street'></td></tr>" +
        "<tr><td>Здание/офис/кв</td><td><input type='text' class='building'></td></tr>" +
        "</tbody></table>";
    document.getElementById('registration').innerHTML = data;
}

inputs();

document.onclick = function (event) {
    if (event.target.classList.contains('green')) {
        console.log(JSON.stringify(
            new UserDto(
                document.querySelector('.surname').value,
                document.querySelector('.name').value,
                document.getElementById('age').value,
                document.querySelector('.login').value,
                document.querySelector('.password').value,
                document.querySelector('.mail').value,
                document.querySelector('.country').value,
                document.querySelector('.city').value,
                document.querySelector('.street').value,
                document.querySelector('.building').value
            )
        ));
        postUser(
            JSON.stringify(
                new UserDto(
                    document.querySelector('.surname').value,
                    document.querySelector('.name').value,
                    document.getElementById('age').value,
                    document.querySelector('.login').value,
                    document.querySelector('.password').value,
                    document.querySelector('.mail').value,
                    document.querySelector('.country').value,
                    document.querySelector('.city').value,
                    document.querySelector('.street').value,
                    document.querySelector('.building').value
                )
            )
        );
        window.location.replace("http://" + document.location.host + "/auth/login");
    }
}

//registration
class UserDto {
    constructor(surname,name, age, login,password,mail, country, city, street, building) {
        this.id = null;
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.login = login;
        this.password=password;
        this.mail=mail;
        this.addressId=null;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
    }
}

function postUser(jsonText) {
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: "/registration",
        data: jsonText, // Note it is important
        success: function (result) {
            // do what ever you want with data
        }
    });
}