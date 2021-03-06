<%--
  Created by IntelliJ IDEA.
  User: KJH
  Date: 2022-01-07
  Time: 오후 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.java_bus.vo.BusStationVo" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=t8y7kns73m"></script>
<%--    <link href="resources/css/styles.css" rel="stylesheet" >--%>
    <script type="text/javascript" src="../../../resources/static/js/map.js" ></script>
    <title>버스 정류장 조회</title>
</head>
<body>
<div class="container">
    <div id="map" style="width:100%;height:400px;">
    </div>
    <script inline="javascript">

        var list = new Array();
        var stationInfo = new Array();

        initCount();

        //지도 표시 폴리라인
        <c:forEach items="${busStationPathList}" var="busStationPath" varStatus="i">
            insertPolyline(${busStationPath.y}, ${busStationPath.x});
        </c:forEach>

        centerX = centerX / pathCount;
        centerY = centerY / pathCount;

        <c:forEach items="${busStationVoList}" var="busStationVo" varStatus="i">
            stationInfo.push("${i.count}");
            stationInfo.push("${busStationVo.stStationNm}");
            stationInfo.push("${busStationVo.posX}");
            stationInfo.push("${busStationVo.posY}");
            stationInfo.push("${busStationVo.transYn}");

            list.push(stationInfo);
            stationInfo = [];
        </c:forEach>

        map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(centerX, centerY),
            zoom: 14
        });

        for (let j = 0; j < list.length; j++) {
            insertLocation(list[j]);

            if(list[j][4] == "Y")
                transValue = j;
        }

        for (let i = 0; i < locations.length; i++) {
            insertMarker(locations[i]);
            insertInfoWindow(locations[i], i);
        }


        //지도 표시 버스 위치
        <c:forEach items="${busArriveList}" var="busArrive" varStatus="i">
            insertBusMarker(${busArrive.posY}, ${busArrive.posX});
            insertBusinfoWindows('${busArrive.plainNo}');
        </c:forEach>


        polyline = new naver.maps.Polyline({
            map: map,
            path: polylinePaths,
            strokeColor: '#996ac5',
            strokeStyle: 'solid',
            strokeOpacity: 1,
            strokeWeight: 4
        });

        for (let i=0, ii=markers.length; i<ii; i++) {
            // console.log(markers[i] , getClickHandler(i));
            naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
            naver.maps.Event.addListener(markers[i], 'mouseover', getClickHandler(i));
        }
        for (let i=0, ii=busmarkers.length; i<ii; i++) {
            console.log(busmarkers[i] , getClickBusHandler(i));
            naver.maps.Event.addListener(busmarkers[i], 'click', getClickBusHandler(i)); // 클릭한 마커 핸들러
            naver.maps.Event.addListener(busmarkers[i], 'mouseover', getClickBusHandler(i));
        }
    </script>
    <table id="busstationboard" class="table table-hover">
        <thead>
        <tr>
            <th>노선 ID</th>
            <th>버스 번호</th>
            <th>정류장 번호</th>
            <th>정류장 이름</th>
            <th>도착 정보</th>
            <th>도착 버스번호</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${busStationVoList}" var="busStationVo" varStatus="i">
            <tr>
                <td><c:out value="${busStationVo.busRouteId}"/></td>
                <td><c:out value="${busStationVo.busRouteNm}"/></td>
                <td>${i.count}</td>
                <td><c:out value="${busStationVo.stStationNm}"/></td>
                <td><c:out value="${busStationVo.arrmsg}"/><br>
                    <c:out value="${busStationVo.arrmsg2}"/></td>
                <td><c:out value="${busStationVo.plainNo1}"/><br>
                    <c:out value="${busStationVo.plainNo2}"/>
                </td>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
