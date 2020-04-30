window.init = function () {
    var map = new AMap.Map('map', {
        resizeEnable: true,
        zoom: 7, //级别
        center: [108, 34],
        //viewMode: '2D' //使用2D视图
    });
}
//未待完续
function maphttpGet(url) {
    let xhr = new XMLHttpRequest();
    //let url = "/travel";
    let revcObj;
    xhr.open("get", url, true);
    xhr.setRequestHeader("Contenet-type", "application/json");
    xhr.onload = function () {
        console.log(xhr.response);
        revcObj = JSON.parse(xhr.response);
    }
    xhr.send(null);
    console.log(revcObj);
    return revcObj;
}
