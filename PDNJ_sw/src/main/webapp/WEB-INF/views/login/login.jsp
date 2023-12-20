<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
</style>
<link rel="shortcut icon" type="image/x-icon" href="/ehr/favicon.ico">
<title>PCWK</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>
<script type="text/javascript">
    $( document ).ready(function() {
        console.log( "ready!" );
        
        $("#doLogin").on("click", function(e){
            console.log( "doLogin click!" );
            
            let userId = document.querySelector("#userId").value;
            if(eUtil.isEmpty(userId)==true){
                alert('아이디를 입력 하시오.');
                document.querySelector("#userId").focus();
                return;
            }
            console.log("userId:"+userId);
            
            let password = document.querySelector("#password").value;
            if(eUtil.isEmpty(password)==true){
                alert('비밀번호를 입력 하시오.');
                document.querySelector("#password").focus();
                return;
            }
            console.log("password:"+password);
            
            if(confirm("로그인 하시겠습니까?")===false) return;
            console.log("로그인 하시겠습니까?");
            
            $.ajax({
                type: "POST",
                url:"/ehr/login/doLogin.do",
                asyn:"true",
                dataType:"json",/* return dataType : json으로 return */
                data:{
                    "userId": userId,
                    "password": password
                },
                success:function(data){//통신 성공
                    console.log("data.msgId:"+data.msgId);
                    console.log("data.msgContents:"+data.msgContents);
                    /*let parsedJSON = JSON.parse(data);
                    if("1" === data.msgId){
                        alert(data.msgContents);
                        moveToList();
                        document.querySelector("#idCheck").value = 0;
                    }else{
                        alert(data.msgContents);
                        document.querySelector("#idCheck").value = 1;
                    }*/
                    if("10" == data.msgId){
                        alert(data.msgContents);
                        document.querySelector("#userId").focus();
                    }else if("20" == data.msgId){
                        alert(data.msgContents);
                        document.querySelector("#password").focus();
                    }else if("30" == data.msgId){
                        alert(data.msgContents);
                        /* 성공 -> 이동 */
                        window.location.href ="/ehr/main/Main.do";
                    }
                    
                },
                error:function(data){//실패시 처리
                    console.log("error:"+data);
                },
                complete:function(data){//성공/실패와 관계없이 수행!
                    console.log("complete:"+data);
                }
            });
            
            
            
        });/* #doLogin */
    });/* document.ready */
</script>
</head>
<body>
     <%@ include file="/WEB-INF/views/main/header.jsp" %>

    <!-- <h2>로그인</h2>
    <hr/> -->
    <fieldset style="width:300px;">
    <legend>로그인</legend>
    <form action="#" method="post">
           <table>
               <tr>
                   <td>
                       <label for="userId">ID</label>
                   </td>
                   <td>
                       <input type="text" id="userId" name="userId" size="20" maxlength="30">
                   </td>
               </tr>
               <tr>
                   <td>
                       <label>password</label>
               </td>
                   <td>
                       <input type="password" id="password" name="password" size="20" maxlength="30">
                   </td>
               </tr>
               <!-- <tr>
                   <td colspan="2">
                   </td>
               </tr> -->
           </table>
        </form>
        <input type="button" value="login" id="doLogin">
    </fieldset>
</body>
</html>