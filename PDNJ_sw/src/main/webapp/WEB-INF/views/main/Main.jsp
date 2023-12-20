<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  
  <link rel="shortcut icon" type="image/x-icon" href="/ehr/favicon.ico">
  <title>Main Page</title>
  <style>
	  /* 내비게이션 메뉴 스타일 */
	  nav {
	    margin-bottom: 0px; /* 링크와 헤더 사이 여백 조절 */
	  }
	
	  nav ul {
	    display: flex;
	    list-style: none;
	    padding: 0;
	    margin: 0;
	  }
	
	  nav li {
	    margin-right: 20px; /* 링크 간격 조절 */
	  }
	
	  nav a {
	    text-decoration: none;
	    font-weight: lighter;
	    font-size: 16px;
	    color: #333;
	  }
  
    /* 기존 스타일 유지 */
    /* 여기에 현재 사용 중인 CSS 코드를 유지하세요. */

    /* Flexbox 레이아웃을 사용하여 링크를 나란히 배치 */
    .grid-container {
      display: flex;
      justify-content: space-between; /* 링크 간격을 동일하게 설정 */
      padding: 100px; /* 링크와 헤더 사이 간격 조절 */
      flex-wrap: wrap; /* 링크가 넘칠 경우 다음 줄로 넘어갈 수 있도록 설정 */
    }

    .grid-item {
      background-color: #f0f0f0;
      padding: 40px;
      text-align: center;
      width: 150px; /* 각 링크의 너비를 조정할 수 있습니다. */
      margin: 10px; /* 링크 간격 조절 */
    }
     body {
      min-height: 100vh; /* 뷰포트의 높이에 맞춰 최소 높이 설정 */
      margin: 0; /* body의 기본 마진 제거 */
      display: flex;
      flex-direction: column;
    }

    /* 헤더 스타일 */
    header {
      text-align: center;
      background-color: #fff;
      padding: 10px;
      margin-bottom: 50px; /* 헤더와 링크 간격 조절 */
    }
    
    /* 다양한 링크 스타일 예시 */
    .grid-item:nth-child(odd) {
      background-color: #c0c0c0; /* 홀수 번째 링크 배경색 변경 */
      transform: rotate(3deg); /* 회전 효과 */
    }
    
    header {
	  text-align: center;
	  background-color: #fff;
	  padding: 0px;
	  margin-bottom: 0; /* 헤더와 링크 간격 조절을 0으로 수정 */
	}
    .grid-item:nth-child(even) {
      background-color: #a0a0a0; /* 짝수 번째 링크 배경색 변경 */
      transform: rotate(-3deg); /* 반대 방향으로 회전 효과 */
    }

    /* 추가적인 스타일을 원하시는 경우에는 여기에 추가하세요. */
    
    /* 하단 텍스트 스타일 */
    .footer-text {
  text-align: center;
  padding: 20px;
  background-color: #333;
  color: #fff;
  font-size: 14px;
  width: 100%;
  /* height: 200px; 삭제 */
  margin-top: auto;
}

.carousel-inner {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh; /* 화면 전체 높이에 맞추려면 필요한 경우 추가 */
  }

  .first-slide {
    max-width: 100%; /* 이미지가 부모 컨테이너를 벗어나지 않도록 설정 */
    max-height: 100%; /* 이미지가 부모 컨테이너를 벗어나지 않도록 설정 */
  }
  
  .navbar_menu li :hover {
        color: #00BFFF;
        text-decoration: underline;
        border-radius: 4px;
    }

  </style>
</head>
<body>

<!-- 내비게이션 메뉴 -->
        <nav class="navbar">
            <ul class="navbar_menu">
                <li><a href="/ehr/map/map.do">장소</a></li>
                <li><a href="/ehr/post/doRetrieve.do">펫 스토리</a></li>
            </ul>
        </nav>
        <hr>

<div id="header-content"></div>
  <!-- Header -->
  <header>
    <input type="text" placeholder="통합 검색">
    <button>검색</button>
  </header>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide home-image" src="/ehr/back-1.jpg" alt="First slide">
            <div class="container">
                <div class="carousel-caption">
                </div>
            </div>
        </div>
        <!-- 이하 생략 -->
    </div>
</div><!-- /.carousel -->
<!-- 
  Main Content
  <main class="main">
    <div class="grid-container">
      <div class="grid-item"><a href="#">링크 1</a></div>
      <div class="grid-item"><a href="#">링크 2</a></div>
      <div class="grid-item"><a href="#">링크 3</a></div>
      <div class="grid-item"><a href="#">링크 4</a></div>
      링크를 추가하거나 순서를 변경하여 배치할 수 있습니다.
    </div>
  </main> -->

  <%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
    <script>

    </script>
</html>
