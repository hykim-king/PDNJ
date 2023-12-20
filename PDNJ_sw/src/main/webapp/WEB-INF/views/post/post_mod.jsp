<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="http://localhost:8080/ehr/resources/css/user.css"> -->
<link rel="stylesheet" href="/ehr/resources/css/user.css">
<style>
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

.p-label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}
.p-input {
    height: 26.5px;
    width: 300px;
    vertical-align: middle;
    font-size: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
}
.p-div {
    margin-bottom: 5px;
}
textarea.p-input {
    height: 200px; /* 원하는 높이로 조절하세요 */
    /* 다른 스타일 속성들 */
}
</style>
<title>게시판</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>
<script>
    function moveToList() {
        console.log("-----------------");
        console.log("-moveToList()-");
        console.log("-----------------");
        window.location.href = "/ehr/post/doRetrieve.do";
    }
    function doDelete() {
        console.log("-----------------");
        console.log("-doDelete()-");
        console.log("-----------------");
        let postId = document.querySelector("#postId").value;
        console.log("postId:" + postId);
        if (eUtil.isEmpty(postId) == true) {
            alert('아이디를 입력하세요.');
            document.querySelector("#postId").focus();
            return;
        }
        if (window.confirm('삭제하시겠습니까?') == false) {
            return;
        }
        console.log("confirm:")
        $.ajax({
            type : "GET",
            url : "/ehr/post/doDelete.do",
            asyn : "true",
            dataType : "json",
            data : {
                postId : postId
            },
            success : function(data) {//통신 성공
                console.log("success data:" + data);
                //let parsedJSON = JSON.parse(data);
                if ("1" === data.msgId) {
                    alert(data.msgContents);
                    moveToList();
                } else {
                    alert(data.msgContents);
                }
            },
            error : function(data) {//실패시 처리
                console.log("error:" + data);
            },
            complete : function(data) {//성공/실패와 관계없이 수행!
                console.log("complete:" + data);
            }
        });
    }
    function doSave() {
        console.log("-----------------");
        console.log("-doSave()-");
        console.log("-----------------");
        // javascript
        console.log("javascript postId:"
                + document.querySelector("#postId").value);
        console.log("javascript ppl_input:"
                + document.querySelector(".ppl_input").value);
        console.log("jquery postId:" + $("#postId").val());
        console.log("jquery ppl_input:" + $(".ppl_input").val());
        if (eUtil.isEmpty(document.querySelector("#postId").value) == true) {
            alert('아이디를 입력하세요.');
            document.querySelector("#postId").focus();
            return;
        }
        if (eUtil.isEmpty(document.querySelector("#title").value) == true) {
            alert('제목을 입력하세요.');
            document.querySelector("#title").focus();
            return;
        }
        if (eUtil.isEmpty(document.querySelector("#postContent").value) == true) {
            alert('내용을 입력하세요.');
            document.querySelector("#postContent").focus();
            return;
        }
        if (eUtil.isEmpty(document.querySelector("#userId").value) == true) {
            alert('이름을 입력하세요.');
            document.querySelector("#userId").focus();
            return;
        }
        // confirm
        if (confirm("등록 하시겠습니까?") == false)
            return;
        $.ajax({
            type : "POST",
            url : "/ehr/post/doSave.do",
            asyn : "true",
            dataType : "html",
            data : {
                postId : document.querySelector("#postId").value,
                title : document.querySelector("#title").value,
                postContent : document.querySelector("#postContent").value,
                userId : document.querySelector("#userId").value
            },
            success : function(data) {//통신 성공
                console.log("success data:" + data);
                let parsedJSON = JSON.parse(data);
                if ("1" === parsedJSON.msgId) {
                    alert(parsedJSON.msgContents);
                    moveToList();
                } else {
                    alert(parsedJSON.msgContents);
                }
            },
            error : function(data) {//실패시 처리
                console.log("error:" + data);
            },
            complete : function(data) {//성공/실패와 관계없이 수행!
                console.log("complete:" + data);
            }
        });
    }
    function doUpdate(){
           console.log("----------------------");
           console.log("-doUpdate()-");
           console.log("----------------------");
           //javascript
           console.log("javascript postId:"+document.querySelector("#userId").value);
           //console.log("javascript ppl_input:"+document.querySelector(".ppl_input").value);
           //$("#userId").val() : jquery id선택자
           //$(".userId")
           console.log("jquery userId:"+$("#userId").val());
          // console.log("jquery ppl_input:"+$(".ppl_input").val());
           if(eUtil.isEmpty(document.querySelector("#userId").value) == true){
               alert('이름를 입력 하세요.');
               //$("#userId").focus();//사용자 id에 포커스
               document.querySelector("#userId").focus();
               return;
           }
           if(eUtil.isEmpty(document.querySelector("#title").value) == true){
               alert('제목을 입력 하세요.');
               //$("#userId").focus();//사용자 id에 포커스
               document.querySelector("#title").focus();
               return;
           }
           if(eUtil.isEmpty(document.querySelector("#postContent").value) == true){
               alert(' 내용를 입력 하세요.');
               //$("#userId").focus();//사용자 id에 포커스
               document.querySelector("#postContent").focus();
               return;
           }
           //confirm
           if(confirm("수정 하시겠습니까?")==false)return;
           $.ajax({
               type: "POST",
               url:"/ehr/post/doUpdate.do",
               asyn:"true",
               dataType:"html",
               data:{
                   postId: document.querySelector("#postId").value,
                   userId: document.querySelector("#userId").value,
                   title: document.querySelector("#title").value,
                   postContent: document.querySelector("#postContent").value
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
        <h2>상세 게시판</h2>
        <hr />
        <!-- Button 영역 -->
        <div></div>
        <!-- // Button 영역 ---------------------------------------------------->
        <!-- 회원 등록 영역 -->
        <div>
            <form action="#" name="userRegFrm">
                <div class="p-div">
                    <label for="postId" class="p-label">게시글번호</label> <input type="text"
                        class="p-input" readonly="" name="postId" id="postId" placeholder="이름을 입력하세요."
                        value="${outVO.postId}" size="20" maxlength="20">
                </div>
                <div class="p-div">
                    <label for="userId" class="p-label">이름</label> <input type="text"
                        class="p-input" readonly="" name="userId" id="userId" placeholder="이름을 입력하세요."
                        value="${outVO.userId}" size="20" maxlength="20">
                </div>
                <div class="p-div">
                    <label for="title" class="p-label">제목</label> <input type="text"
                        class="p-input" name="title" id="title" placeholder="제목을 입력하세요."
                        value="${outVO.title}" size="20" maxlength="30">
                </div>
                <div class="p-div">
                    <label for="postContent" class="p-label">내용</label>
                    <textarea class="p-input" name="postContent" id="postContent"
                        placeholder="내용을 입력하세요. (최대 500자)" rows="10" maxlength="500">${outVO.postContent}</textarea>
                </div>
            </form>
        </div>
        <!-- // 회원 등록 영역 ----------------------------------------------------->
            <input type="button" class="btn" value="수정" id="doUpdate" onclick="window.doUpdate();">
            <input type="button" class="btn" value="삭제" id="doDelete" onclick="window.doDelete();">
            <input type="button" class="btn" value="목록" id="moveToList" onclick="window.moveToList();">
    </div>
</body>
</html>