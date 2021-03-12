document.onclick = function (event) {
    if(event.target.classList.contains('logout')){
        window.location.replace('http://'+document.location.host+"/logout");
    }
}