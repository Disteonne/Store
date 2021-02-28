function httpGet(url) {

    return new Promise(function(resolve, reject) {

        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);

        xhr.onload = function() {
            if (this.status == 200) {
                resolve(this.response);
            } else {
                var error = new Error(this.statusText);
                error.code = this.status;
                reject(error);
            }
        };

        xhr.onerror = function() {
            reject(new Error("Network Error"));
        };

        xhr.send();
    });

}

var str;
var kekch;
async function kek() {

    str = await httpGet("http://localhost:8081/product/2").then(response => {
            console.log(response);
            let info = JSON.parse(response);
            str = info;
            return info
        }
        , error => console.log(error));

    console.log("opopop");

    console.log(str);
    return str;
}
/*
async function f() {
    kekch = await kek();
    console.log(kekch);
}
f();

 */

var inf= kek().then(r => "kek");
var wtd=Promise.resolve();
console.log(wtd);