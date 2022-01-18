let locations = new Array();
let markers = new Array();
let busmarkers = new Array();
let infoWindows = new Array();
let businfoWindows = new Array();
let polyline;
let polylinePaths = new Array();
let transValue = 0;
let pathCount = 0;
let centerX = 0;
let centerY = 0;

function initCount() {
    transValue = 0;
    pathCount = 0;
    centerX = 0;
    centerY = 0;
    locations.length = 0;
    markers.length = 0;
    busmarkers.length = 0;
    infoWindows.length = 0;
    businfoWindows.length = 0;
    polylinePaths.length =0;
}
function insertLocation(list) {
    locations.push({location: list[1], lat: list[3], lng: list[2]});
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
function insertBusinfoWindows(plainNo) {
    //info
    let infoWindow = new naver.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:5px;"><b>' + plainNo + '</b><br></div>'
    }); // 클릭했을 때 띄워줄 정보 HTML 작성

    businfoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
}

function insertPolyline(posX, posY) {
    //poly
    let polyPath = new naver.maps.LatLng(posX, posY);
    polylinePaths.push(polyPath);

    centerX = centerX + posX;
    centerY = centerY + posY;
    pathCount++;
}

function insertBusMarker(posX, posY) {
    var position = new naver.maps.LatLng(posX, posY);

    var markerOptions = new naver.maps.Marker({
        position: position.destinationPoint(50, 15),
        map: map,
        icon: {
            url: '../../../resources/static/icon/bus.png',
            size: new naver.maps.Size(50, 50),
            origin: new naver.maps.Point(0, 0),
            anchor: new naver.maps.Point(25, 26)
        }
    });

    busmarkers.push(markerOptions);
}


function getClickHandler(seq) {

    return function(e) {  // 마커를 클릭하는 부분
        let marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
            infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다

        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
            infoWindow.open(map, marker); // 표출
        }
    }
}
function getClickBusHandler(seq) {

    return function(e) {  // 마커를 클릭하는 부분
        let marker = busmarkers[seq], // 클릭한 마커의 시퀀스로 찾는다.
            infoWindow = businfoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다

        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
            infoWindow.open(map, marker); // 표출
        }
    }
}