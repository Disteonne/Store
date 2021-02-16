var getJSON = function (url, callback) {
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


var list = getJSON('http://localhost:8080/product/getAll',
    function (data) {
        console.log(data)


        for (var iter=0;iter<data.length;iter++){
            var id=data.id;
            var name=data.name;

            var elTR = $('all_product').insertRow( -1 );
            var elTD1 = elTR.insertCell( -1 );
            elTD1.innerHTML = id;
            var elTD2 = elTR.insertCell( -1 );
            elTD2.innerHTML = name;
        }

    });


console.log('kek')