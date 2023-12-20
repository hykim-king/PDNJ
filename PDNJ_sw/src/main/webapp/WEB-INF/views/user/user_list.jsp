<%@page import="com.pcwk.ehr.user.domain.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    UserVO dto = (UserVO)request.getAttribute("searchVO");
%>
<!DOCTYPE html>
<html>
<head>
<style> /* style */
    table, th, td {
        border: 1px solid #ccc;
        border-collapse: collapse;
    }
    
    th, td {
        padding: 10px 20px;
    }
    
    /* 정렬 */
    .txt-left{
        text-align: left;
    }
    .txt-center{
        text-align: center;
    }
    .txt-right{
        text-align: right;
    }
    .btn{
         height: 30px;
         vertical-align: middle;
         font-size: 12px;
         background-color:skyblue;
         color:#fff;
        
   }
   .select{
        height: 30px;
        vertical-align: middle;
        font-size:  10.5px;
   }
   .input{
          height: 26px;
          vertical-align: middle;
   }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>

</head>
<body>
     <%@ include file="/WEB-INF/views/main/header.jsp" %>
    <h2>회원 관리</h2>

    <div>
         <!-- button -->
        <input type="button" value="조회" id="doRetrieve" onclick="window.doRetrieve(1);">
        <input type="button" value="등록" id="moveToReg" onclick="window.moveToReg();">
        <form action="#" method="get" name="userFrm">
            <input type="hidden" name="pageNo">
            <div>
                <!-- 검색 구분 -->
                <select name="searchDiv" id="searchDiv">
                    <option value="">전체</option>
                    <option value="10" <% if ("10".equals(dto.getSearchDiv())){out.print("selected");} %>>사용자 ID</option>
                    <option value="20" <% if ("20".equals(dto.getSearchDiv())){out.print("selected");} %>>이름</option>
                    <option value="30" <% if ("30".equals(dto.getSearchDiv())){out.print("selected");} %>>이메일</option>
                </select>
                <!-- 검색어 -->
                <input type="text" class="input" value="${searchVO.searchWord}" name="searchWord" id="searchWord" placeholder="검색어를 입력하세요.">
                <!-- pageSize : 10, 20, 30, 50, 100, 200 -->
                <select name="pageSize" id="pageSize" class="select">
                    <option value="10"  <% if (10L == dto.getPageSize()){out.print("selected");}  %>>10</option>
                    <option value="20"  <% if (20L == dto.getPageSize()){out.print("selected");}  %>>20</option>
                    <option value="30"  <% if (30L == dto.getPageSize()){out.print("selected");}  %>>30</option>
                    <option value="50"  <% if (50L == dto.getPageSize()){out.print("selected");}  %>>50</option>
                    <option value="100" <% if (100L == dto.getPageSize()){out.print("selected");} %>>100</option>
                    <option value="200" <% if (200L == dto.getPageSize()){out.print("selected");} %>>200</option>
                </select>
            </div>
        </form>
        <!-- table -->
        <table id="userTable">
            <thead>
                <tr>
                    <th class="txt-center">번호</th>
                    <th class="txt-center">사용자 ID</th>
                    <th class="txt-center">비밀번호</th>
                    <th class="txt-center">이름</th>
                    <th class="txt-center">이메일</th>
                    <th class="txt-center">전화번호</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <%-- 조회 테이더가 있는 경우 : jsp comment(html에 노출 안 됨) --%>
                    <c:when test="${not empty list}">
		                <c:forEach var="vo" items="${list}">
		                    <tr>
		                        <td class="txt-center">${vo.no}</td>
		                        <td class="txt-center">${vo.userId}</td>
		                        <td class="txt-left">${vo.password}</td>
		                        <td class="txt-left">${vo.username}</td>
		                        <td class="txt-left">${vo.email}</td>
		                        <td class="txt-center">${vo.userTel}</td>
		                    </tr>
		                </c:forEach>
	                </c:when>
                    <%-- 조회 데이터가 없는 경우 : jsp comment(html에 노출 안 됨) --%>
                    <c:otherwise>
                        <tr>
                            <td colspan="99">No Data found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <!-- // table ------------------------------------------>
    </div>
<script type="text/javascript">
//jquery event 감지
	$("#searchWord").on("keypress", function(e){
	    console.log('searchWord:keypress');
	    
	    // e.which : 13
	    console.log(e.type + ':' + e.which);
	    if (13 == e.which) {
	        e.preventDefault(); // 버블링 중단
	        doRetrieve(1);
	    }
	});
	
	//jquery:table 데이터 선택     
	$("#userTable>tbody").on("click","tr" , function(e){
	    console.log('----------------------------');
	    console.log('userTable>tbody');
	    console.log('----------------------------');    
	    
	    let tdArray = $(this).children();//td
	    
	    let userId = tdArray.eq(1).text();
	    console.log('userId:'+userId);
	    
	    window.location.href ="/ehr/user/doSelectOne.do?userId="+userId;
	    
	});

    function moveToReg(){
        console.log('----------------');
        console.log('moveToReg');
        console.log('----------------');
        
        let frm = document.userFrm;
        frm.action = "/ehr/user/moveToReg.do";
        frm.submit();
        //window.location.href= '/ehr/user/moveToReg.do'
    }

    function doRetrieve(pageNo) {
        console.log('----------------');
        console.log('doRetrieve');
        console.log('----------------');
        
        let frm = document.forms['userFrm']; // form
        let pageSize = frm.pageSize.value;
        console.log('pageSize:' + pageSize);
        
        let searchDiv = frm.searchDiv.value;
        console.log('searchDiv:' + searchDiv);
        
        let searchWord = frm.searchWord.value;
        console.log('searchWord:' + searchWord);
        
        console.log('pageNo:' + pageNo);
        frm.pageNo.value = pageNo;
        
        console.log('pageNo:' + frm.pageNo.value);
        
        // pageNo
        frm.action = "/ehr/user/doRetrieve.do";
        
        // 서버 전송
        frm.submit();
    }
</script>
<%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>