<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="http://localhost:8080/ehr/resources/css/post.css"> -->
<link rel="stylesheet" href="/ehr/resources/css/post.css">
<style>
        /* 여기에 주어진 CSS 파일의 스타일들이나 추가적인 스타일을 넣어주세요 */
        /* 예시 스타일 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h2 {
            color: #333;
        }

        /* 폼 스타일 */
        .p-label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .p-input {
            height: 26.5px;
            width: 380px;
            vertical-align: middle;
            font-size: 12px;        
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 5px;
            box-sizing: border-box;
        }
        
        .p-div {
            margin-bottom: 15px;
        }

        .btn {
            padding: 8px 16px;
            font-size: 14px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
         textarea.p-input {
        height: 200px; /* 원하는 높이로 조절하세요 */
        /* 다른 스타일 속성들 */
    }
    </style>
<title>펫도 놀자</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>
<script >

   
   function moveToList(){
       console.log("----------------------");
       console.log("-moveToList()-");
       console.log("----------------------");
       
       window.location.href = "/ehr/post/doRetrieve.do";
   }
   
   function doSave(){
       console.log("----------------------");
       console.log("-doSave()-");
       console.log("----------------------");
       
       //javascript
       console.log("javascript title:"+document.querySelector("#title").value);
       console.log("javascript ppl_input:"+document.querySelector(".ppl_input").value);
       
       //$("#postId").val() : jquery id선택자
       //$(".title")
       
       console.log("jquery title:"+$("#title").val());
       
       console.log("jquery ppl_input:"+$(".ppl_input").val());      
       
       if(eUtil.isEmpty(document.querySelector("#userId").value) == true){
           alert('아이디를 입력하세요.');
           //$("#title").focus();//사용자 id에 포커스
           document.querySelector("#userId").focus();
           return;
       }
       if(eUtil.isEmpty(document.querySelector("#title").value) == true){
           alert('게시판 제목을 입력하세요.');
           //$("#title").focus();//사용자 id에 포커스
           document.querySelector("#title").focus();
           return;
       }
       if(eUtil.isEmpty(document.querySelector("#postContent").value) == true){
           alert('게시판 내용을 입력하세요.');
           //$("#postContent").focus();//사용자 id에 포커스
           document.querySelector("#postContent").focus();
           return;
       }       
       
       
       

       
      
       //confirm
       if(confirm("등록 하시겠습니까?")==false)return;
       
       
       $.ajax({
           type: "POST",
           url:"/ehr/post/doSave.do",
           async:"true",
           dataType:"html",
           data:{
               userId:document.querySelector("#userId").value,
               title:document.querySelector("#title").value,
               postContent:document.querySelector("#postContent").value,
               
              
           },
           success:function(data){//통신 성공     
               console.log("success data:"+data);
              //data:{"msgId":"1","msgContents":"dd가 등록 되었습니다.","no":0,"totalCnt":0,"pageSize":0,"pageNo":0}
              let parsedJSON = JSON.parse(data);
              if("1" === parsedJSON.msgId){
                  alert(parsedJSON.msgContents);
                  moveToList();
              }else{
                  alert(parsedJSON.msgContents);
              }
           
           },
           error:function(data){//실패시 처리
               console.log("error:"+data);
           },
           complete:function(data){//성공/실패와 관계없이 수행!
               console.log("complete:"+data);
           }
       });
       
   }
</script>

</head>
<body>
     <div> 
         <h2>게시판 글쓰기</h2>
         <hr/>
         <!-- Button영역 -->
         <div>
           <input type="button" class="btn" value="게시판 등록" id="doSave"      onclick="window.doSave();">
           <input type="button" class="btn" value="게시판 목록" id="moveToList"  onclick="window.moveToList();">
         </div>
         <!--// Button영역 ------------------------------------------------------>
         
         <!-- 회원 등록영역 -->  
         <div>
           <form action="#" name="postRegFrm">
               <div class="p-div">
               </div>
               <div class="p-div">
                   <label for="userId" class="p-label">아이디</label>
                   <input type="text"  class="p-input ppl_input" name="userId" id="userId" placeholder="아이디를 입력하세요." size="20"  maxlength="30">
               </div>   
               <div class="p-div">
                   <label for="title" class="p-label">게시판 제목</label>
                   <input type="text"  class="p-input ppl_input" name="title" id="title" placeholder="제목을 입력하세요." size="20"  maxlength="30">
               </div>
               <div class="p-div">
                   <label for="postContent" class="p-label">내용</label>
                   <textarea class="p-input" name="postContent" id="postContent" placeholder="내용을 입력하세요. (최대 500자)" rows="10" maxlength="500"></textarea>
               </div>   
                
                                                                  
           </form>
         </div>
         <!--// 회원 등록영역 ------------------------------------------------------>
         
     </div>
</body>
</html>