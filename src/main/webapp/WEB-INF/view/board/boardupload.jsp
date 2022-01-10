<%--
  Created by IntelliJ IDEA.
  User: KJH
  Date: 2022-01-10
  Time: 오후 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<div class="container">
    <form action="<c:url value='/boardTest/upload' />" method="post">
        제목 :
        <div>
            <input type="text" class="form-control" name="title">
            </textarea>
        </div>
        작성자 :
        <div>
            <input type="text" class="form-control" name="name">
        </div>
        내용 :
        <div>
            <textarea class="form-control" name="content"></textarea>
            </textarea>
        </div>
        <button type="submit" class="btn btn-secondary btn-sm float-left">등록</button>
    </form>
</div>

</body>
</html>