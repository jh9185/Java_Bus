function Loaddata() {
    var xhr = new XMLHttpRequest();
    var url = 'http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute'; /*URL*/
    var queryParams = '?' + encodeURIComponent('serviceKey') + '=' + 'crWr3d38ilIuwdcULZmazg8UNnUS%2B9MEXSS1KKyPvE%2BuYkGBfR6HKTKpMSTEw3i03ISVwG59bJai7JDasd4%2BIw%3D%3D'; /*Service Key*/
    queryParams += '&' + encodeURIComponent('busRouteId') + '=' + encodeURIComponent('104000007'); /**/
    xhr.open('GET', url + queryParams);
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            //alert('Status: ' + this.status + 'nHeaders: ' + JSON.stringify(this.getAllResponseHeaders()) + 'nBody: ' + this.responseText);
            console.log(xhr.responseText);
        }
    };

    xhr.send('');
}