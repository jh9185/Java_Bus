/*<![CDATA[*/

//document.writeln('<script src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=t8y7kns73m"><\/script>');

var areaArr = new Array();  // 지역을 담는 배열 ( 지역명/위도경도 )
var infoWindows = new Array(); // 정보 창 배열
var script = document.createElement('script');
script.src = "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=t8y7kns73m";
document.head.appendChild(script);
// areaArr.push(
//     /*지역구 이름*/			/*위도*/					/*경도*/
//     {location : '강남' , lat : '37.4959854' , lng : '127.0664091'},  // 강남구 중심좌표
//     {location : '강동' , lat : '37.5492077' , lng : '127.1464824'},  // 강동구 중심좌표
//     {location : '강북' , lat : '37.6469954' , lng : '127.0147158'},  // 강북구 중심좌표
//     {location : '강서' , lat : '37.5657617' , lng : '126.8226561'},  // 강서구 중심좌표
//     {location : '관악' , lat : '37.4603732' , lng : '126.9536086'},  // 관악구 중심좌표
//     {location : '광진' , lat : '37.5574120' , lng : '127.0796211'},  // 광진구 중심좌표
//     {location : '구로' , lat : '37.4954856' , lng : '126.858121' },  // 구로구 중심좌표
//     {location : '금천' , lat : '37.4600969' , lng : '126.9001546'},  // 금천구 중심좌표
//     {location : '노원' , lat : '37.6377533' , lng : '127.0754623'},  // 노원구 중심좌표
//     {location : '도봉' , lat : '37.6658609' , lng : '127.0317674'},  // 도봉구 중심좌표
//     {location : '동대문' , lat : '37.5838012' , lng : '127.0507003'},  // 동대문구 중심좌표
//     {location : '동작' , lat : '37.4965037' , lng : '126.9443073'},  // 동작구 중심좌표
//     {location : '마포' , lat : '37.5676507' , lng : '126.8854549'},  // 마포구 중심좌표
//     {location : '서대문' , lat : '37.5820369' , lng : '126.9356665'},  // 서대문구 중심좌표
//     {location : '서초' , lat : '37.4769528' , lng : '127.0378103'},  // 서초구 중심좌표
//     {location : '성동' , lat : '37.5506753' , lng : '127.0409622'},  // 성동구 중심좌표
//     {location : '성북' , lat : '37.606991'  , lng : '127.0232185'},  // 성북구 중심좌표
//     {location : '송파' , lat : '37.5177941' , lng : '127.1127078'},  // 송파구 중심좌표
//     {location : '양천' , lat : '37.5270616' , lng : '126.8561534'},  // 양천구 중심좌표
//     {location : '영등포' , lat : '37.520641'  , lng : '126.9139242'},  // 영등포구 중심좌표
//     {location : '용산' , lat : '37.5311008' , lng : '126.9810742'},  // 용산구 중심좌표
//     {location : '은평' , lat : '37.6176125' , lng : '126.9227004'},  // 은평구 중심좌표
//     {location : '종로' , lat : '37.5990998' , lng : '126.9861493'},  // 종로구 중심좌표
//     {location : '중구' , lat : '37.5579452' , lng : '126.9941904'},  // 중구 중심좌표
//     {location : '중랑구' , lat : '37.598031'  , lng : '127.092931' }   // 중랑구 중심좌표
// );
//
// for (var i = 0; i < areaArr.length; i++) {
//     var marker = new naver.maps.Marker({
//         map: map,
//         title: areaArr[i].place,
//         position: new naver.maps.LatLng(areaArr[i].lat, areaArr[i].lng),
//     });
// }
// $(function() {
//     initMap();
// });

console.log("1123");

function initMap() {
    var map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(37.5697641, 126.9877861),
        zoom: 14
    });
    console.log("initmap");
}

function mapMarker() {
    var list = [[${busstop}]]
    for (var j = 0; j < list.length; j++) {
        var infoWindow = new naver.maps.InfoWindow({
            content: list[j].name
        });
        infoWindows.push(infoWindow);
        areaArr.push({location: list[j].name, lat: list[j].y, lng: list[j].x});
    }

    for (var i = 0; i < areaArr.length; i++) {
        var marker = new naver.maps.Marker({
            map: map,
            title: areaArr[i].place,
            position: new naver.maps.LatLng(areaArr[i].lat, areaArr[i].lng)
        });

        var info = new naver.maps.Event.addListener(marker, "click", function (e) {
            if (infoWindow.getMap()) {
                infoWindow.close();
            } else {
                infoWindow.open(map, marker);
            }
        });
    }
}
// // 해당 마커의 인덱스를 seq라는 클로저 변수로 저장하는 이벤트 핸들러를 반환합니다.
// function getClickHandler(seq) {
//     return function(e) {
//         var marker = areaArr[seq],
//             infoWindow = infoWindows[seq];
//
//         if (infoWindow.getMap()) {
//             infoWindow.close();
//         } else {
//             infoWindow.open(map, marker);
//         }
//     }
// }
// for (var i=0; i<areaArr.length; i++) {
//     naver.maps.Event.addListener(areaArr[i],  'click', getClickHandler(i));
// }
/*]]>*/
