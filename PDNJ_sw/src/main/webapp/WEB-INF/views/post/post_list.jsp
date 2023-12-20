<%@page import="com.pcwk.ehr.user.domain.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
 PostVO dto = (PostVO)request.getAttribute("searchVO");

%>  
<!DOCTYPE html>
<html>
<head>
<style> 
    table, th, td {
      border:1px solid #ccc;
      border-collapse: collapse;
    }
  
    th, td {padding:10px 20px;}
    
    
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
    
    
    .select {
        height: 30px;    
        vertical-align: middle;
        font-size: 12px;    
    }    
    .btn{
        height: 30px;    
        vertical-align: middle;
        font-size: 12px;
        background-color:skyblue;
        color:#ffffff;      
    }
    
    .input {
        height: 26.5px;
        vertical-align: middle;
        font-size: 12px;
    }
    #postTable {
        width: 100%;
        border: 1px solid #ccc;
        border-collapse: collapse;
        margin-top: 20px;
    }

    #postTable th, #postTable td {
        border: 1px solid #ccc;
        padding: 10px;
        text-align: center; /* 텍스트를 가운데 정렬합니다. */
    }

    #postTable th {
        background-color: #58ACFA; /* 배경색을 강조합니다. */
        color: #ffffff; /* 글자 색상을 변경합니다. */
    }
                
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>

</head>
<body>
     <%@ include file="/WEB-INF/views/main/header.jsp" %>

  <h2>Pet Story</h2>

    
   <div>
    <form action="/ehr/post/doRetrieve.do" method="get" name="postFrm">
           <input type="hidden" name="pageNo" >
        <div>
            <!-- 검색구분 -->
            <select name="searchDiv" id="searchDiv">
              <option value="">전체</option>
              <option value="10" <% if("10".equals(dto.getSearchDiv())){out.print("selected");} %>  >제목</option>
              <option value="20" <% if("20".equals(dto.getSearchDiv())){out.print("selected");} %>  >사용자ID</option>
              <option value="30" <% if("30".equals(dto.getSearchDiv())){out.print("selected");} %>  >이름</option>
              <option value="40" <% if("40".equals(dto.getSearchDiv())){out.print("selected");} %>  >이메일</option>
              
            </select>
            <!-- 검색어 -->
            <input type="text" value="${searchVO.searchWord }" name="searchWord" id="searchWord" placeholder="검색어를 입력하세요">
            <!-- pageSize: 10,20,30,50,10,200 -->
            <select name="pageSize" id="pageSize">
               <option value="10"   <% if(10L==dto.getPageSize()){out.print("selected");} %>  >10</option>                 
               <option value="20"   <% if(20L==dto.getPageSize()){out.print("selected");} %>  >20</option>
               <option value="30"   <% if(30L==dto.getPageSize()){out.print("selected");} %>  >30</option>
               <option value="50"   <% if(50L==dto.getPageSize()){out.print("selected");} %>  >50</option>
               <option value="100"  <% if(100L==dto.getPageSize()){out.print("selected");} %> >100</option>
               <option value="200"  <% if(200L==dto.getPageSize()){out.print("selected");} %> >200</option>
            </select>    
            <!-- button -->
            <input type="button"  value="검색" id="doRetrieve" onclick="window.doRetrieve(1);">
            <input type="button" class="btn" value="글쓰기" id="moveToReg"  onclick="window.moveToReg();">
        </div>
    </form>
    
    <!-- table -->
    <table id="postTable">
        <thead>
        <tr>
            <th>게시판번호</th>
            <th>제목</th>
            <th>게시판 콘텐츠</th>
            <th>작성일</th>
            <th>글쓴이</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vo" items="${list}">
            <tr>
                <td class="txt-center">${vo.postId}</td>
                <td class="txt-left">${vo.title}</td>
                <td class="txt-left">${vo.postContent }</td>
                <td class="txt-left">${vo.postDate }</td>
                <td class="txt-left">${vo.userId }</td>
                      
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!--// table -------------------------------------------------------------->
   </div>
    <script type="text/javascript">
       
    //jquery:table 데이터 선택     
    $("#postTable>tbody").on("click","tr" , function(e){
        console.log('----------------------------');
        console.log('postTable>tbody');
        console.log('----------------------------');    
        
        let tdArray = $(this).children();//td
        
        let postId = tdArray.eq(0).text(); // 예상되는 postId가 첫 번째 열(tdArray.eq(0))에 있을 것으로 보입니다.
        console.log('postId:'+postId);
        window.location.href ="/ehr/post/doSelectOne.do?postId="+postId;
        
    });
        function moveToReg(){
        
        
        console.log('----------------------------');
        console.log('moveToReg');
        console.log('----------------------------');  
        
        let frm = document.postFrm;
        frm.action = "/ehr/post/moveToReg.do";
        frm.submit();
        
       //window.location.href= '/ehr/post/moveToReg.do';
      
        }
        function  doRetrieve(pageNo){
            console.log('----------------------------');
            console.log('doRetrieve');
            console.log('----------------------------');
            
            let frm = document.forms['postFrm'];//form
            let pageSize = frm.pageSize.value;
            console.log('pageSize:'+pageSize);
            
            let searchDiv = frm.searchDiv.value;
            console.log('searchDiv:'+searchDiv);
            
            let searchWord = frm.searchWord.value;
            console.log('searchWord:'+searchWord);
            
            console.log('pageNo:'+pageNo);
            frm.pageNo.value = pageNo;
            
            console.log('pageNo:'+frm.pageNo.value);
            //pageNo
            //서버 전송
            frm.submit();
        }
</script>  
<%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>