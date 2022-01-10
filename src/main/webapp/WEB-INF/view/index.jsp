<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="resources/css/styles.css" rel="stylesheet" >
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <script src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=t8y7kns73m"></script>
</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark fixed-top">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="/">Start Bootstrap</a>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
            <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
        </div>
    </form>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#!">Settings</a></li>
                <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                <li><hr class="dropdown-divider" /></li>
                <c:choose>
                    <c:when test="${sessionScope.loginCheck eq true}">
                        <li><a class="dropdown-item" href="/logoutProcess" methods="get">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a class="dropdown-item" href="/Login" methods="get">Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </li>
    </ul>
</nav>
<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <div class="row">
                <div class="col-xl-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-chart-area me-1"></i>
                            서울 버스 도착 정보 조회하기
                        </div>
                        <div class="card-body">
                                <input class="form-inputcontrol" type="text"  name="busNumber" id="busNumber" placeholder="서울 버스 번호 입력" aria-label="버스번호입력" />
                                <button class="btn btn-primary" type="submit" onclick="ajaxStation()"><i class="fas fa-search"></i></button>
                                <button class="btn btn-close btn-sm float-right" onclick="ajaxClose()"></button>

<!--                            <form th:action="@{/bus/numberview}" method="get">-->
<!--                                <button class="btn btn-info" type="submit">서울 버스 번호 조회</button>-->
<!--                            </form>-->
                            <div id="busstation"></div>
                        </div>
                    </div>
                </div>
            </div>
<%--            <div class="card mb-4">--%>
<%--                <div class="card-header">--%>
<%--                    <i class="fas fa-table me-1"></i>--%>
<%--                    서울 버스 노선 목록--%>
<%--                </div>--%>
<%--                <div class="card-body">--%>
<%--                    <table id="busnumberlist" class="table table-hover">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th>노선ID</th>--%>
<%--                            <th>버스 번호</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>
<%--                        <tr th:each="busnumberlist :${busnumberlist}">--%>
<%--                            <td>[[${busnumberlist.busRouteId}]]</td>--%>
<%--                            <td>--%>
<%--                                <a href="@{/bus/stationview(busNumber=${busnumberlist.busRouteNm})}">--%>
<%--                                    [[${busnumberlist.busRouteNm}]]--%>
<%--                                </a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table me-1"></i>
                    게시글 목록
                </div>
                <div class="card-body">
                    <a href="/boardUpload"><button class="btn btn-secondary btn-sm float-right" type="button">글쓰기</button></a>

                    <table id="datatablesSimple" class="table table-hover">
                        <thead>
                        <tr>
                            <th>글번호</th>
                            <th>작성자</th>
                            <th>제목</th>
                            <th>내용</th>
                            <th>글 작성시간</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="list" varStatus="i">
                            <tr>
                                <td>${list.id}</td>
                                <td>${list.title}</td>
                                <td>${list.content}</td>
                                <td>${list.name}</td>
                                <td><javatime:format pattern="yyyy-MM-dd HH:mm:ss" value="${list.regdate}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="resources/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="resources/js/datatables-simple-demo.js"></script>
<script type="text/javascript">
    function ajaxNumber(){
        $.ajax({
            url : "/bus/numberview?",
            type : 'get' ,
            dataType : 'text',
            error : function() {
                alert('통신실패!!');
            },
            success : function(result) {
                $('#busstation').empty();
                $('#busstation').html(result);
            }
        });
    }
    function ajaxStation(){
        var Number = document.getElementById("busNumber").value;
        $.ajax({
            url : "/bus/stationview?busNumber=" + Number,
            type : 'get' ,
            dataType : 'text',
            error : function() {
                alert('통신실패!!');
            },
            success : function(result) {
                $('#busstation').html(result);
            }
        });
    }
    function ajaxClose() {
        $('#busstation').empty();
    }
</script>
</body>

</html>