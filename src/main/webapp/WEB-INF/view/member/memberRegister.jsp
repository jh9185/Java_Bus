<%--
  Created by IntelliJ IDEA.
  User: KJH
  Date: 2022-01-07
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>Register - SB Admin</title>
  <link href="../../resources/static/css/styles.css" rel="stylesheet" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
</head>
<body class="bg-primary">
<div id="layoutAuthentication">
  <div id="layoutAuthentication_content">
    <main>
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-7">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
              <div class="card-header"><h3 class="text-center font-weight-light my-4">회원가입</h3></div>
              <div class="card-body">
                <form action="/memberTest/saveMember" method="get}">
                  <div class="row mb-3">
                    <div class="col-md-6">
                      <div class="form-floating mb-3 mb-md-0">
                        <input class="form-control" id="memberId" type="text" name="memberId" placeholder="Enter your first name" />
                        <button class="btn btn-primary btn-block" id="duplicate_check" type="button" onclick="check();">중복체크</button>
                        <label for="memberId">로그인 ID</label>
                      </div>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <div class="col-md-6">
                      <div class="form-floating mb-3 mb-md-0">
                        <input class="form-control" id="inputPassword" type="password" name="password" placeholder="Create a password" />
                        <label for="inputPassword">비밀번호</label>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-floating mb-3 mb-md-0">
                        <input class="form-control" id="inputPasswordConfirm" type="password" placeholder="Confirm password" />
                        <label for="inputPasswordConfirm">재확인 비밀번호</label>
                      </div>
                    </div>
                  </div>
                  <div class="mt-4 mb-0">
                    <div class="d-grid"><button type="submit"   class="btn btn-primary btn-block" >회원가입 완료하기</button></div>
                  </div>
                </form>
              </div>
              <div class="card-footer text-center py-3">
                <div class="small"><a href="/Login">로그인 화면으로 돌아갑니다.</a></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
  <div id="layoutAuthentication_footer">
    <footer class="py-4 bg-light mt-auto">
      <div class="container-fluid px-4">
        <div class="d-flex align-items-center justify-content-between small">
          <div class="text-muted">Copyright &copy; Your Website 2021</div>
          <div>
            <a href="#">Privacy Policy</a>
            &middot;
            <a href="#">Terms &amp; Conditions</a>
          </div>
        </div>
      </div>
    </footer>
  </div>
</div>
</body>
<script>
  function check(){
    var id = $('#memberId').val();
    $.ajax({
      url: 'memberTest/IdCheck',
      type: 'POST',
      dataType: 'text', //서버로부터 내가 받는 데이터의 타입
      contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
      data: id ,
      success: function(data){
        if(data == 0){
          console.log("아이디 없음");
          alert("사용하실 수 있는 아이디입니다.");
        }else{
          console.log("아이디 있음");
          alert("중복된 아이디가 존재합니다.");
        }
      },
      error: function (){
      }
    });
  }
</script>
</html>
