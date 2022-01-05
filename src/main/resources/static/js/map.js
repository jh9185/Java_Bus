/*<![CDATA[*/

let locations = new Array();
let markers = new Array();
let infoWindows = new Array();
let polylinePaths = new Array();
let polylineTransPaths = new Array();
let transValue = 0;

function insertLocation(list) {
    locations.push({location: list.stStationNm, lat: list.posY, lng: list.posX});
}

function insertMarker(location) {
    let marker = new naver.maps.Marker({
        map: map,
        title: location.location,
        position: new naver.maps.LatLng(location.lat, location.lng)
    });

    markers.push(marker);// 생성한 마커를 배열에 담는다.
}

function insertInfoWindow(location, index) {
    var myaddress = index+1 + " " + location.location;

    //info
    let infoWindow = new naver.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:5px;"><b>' + myaddress + '</b><br></div>'
    }); // 클릭했을 때 띄워줄 정보 HTML 작성

    infoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
}

function insertPolyline(location, index, transValue) {
    //poly
    if(index > transValue){
        let polyTransPath = new naver.maps.LatLng(location.lat, location.lng);
        polylineTransPaths.push(polyTransPath);
    }
    else if(index == transValue){
        let polyTransPath = new naver.maps.LatLng(location.lat, location.lng);
        let polyPath = new naver.maps.LatLng(location.lat, location.lng);
        polylineTransPaths.push(polyTransPath);
        polylinePaths.push(polyPath);
    }
    else{
        let polyPath = new naver.maps.LatLng(location.lat, location.lng);
        polylinePaths.push(polyPath);
    }
}
/*]]>*/
