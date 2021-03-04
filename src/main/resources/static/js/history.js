function getHistories(url, callback) {
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

function alreadyOutput(date, page) { //почти вывод
    f(date, page);
}

function f(date, page) {
    var tableHistory = "<table border='2' class='table'>" +
        "<thead>" +
        "<tr><th>ID</th><th>Date</th><th>Info</th></tr>" +
        "</thead>"+
        "<tbody>";
    getHistories("/history?date=" + date + "&page=" + page, function (data) {
        for (let i = 0; i < data.length; i++) {

            tableHistory += "<tr><td>" + data[i].id + "</td><td>"
                + data[i].date + "</td><td>";

            for (let j = 0; j < data[i].history.length; j++) {
                tableHistory += "Наименование: " + data[i].history[j].name + ".\nТип: " + data[i].history[j].type + ".\nСтоимость товара: " +
                    data[i].history[j].price ;
                //summ += data[i].history[j].price*data[i].history[j].count;
                //tableHistory+=data[i].history[j].info+"\n"
            }
            tableHistory +="</td></tr>";

        }
        tableHistory+="</tbody></table>";
        document.getElementById('history').innerHTML = tableHistory;
    })
}

function output() {
    alreadyOutput("2021-03-04", 0);
}

output();