<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="/ehr/favicon.ico">
        <title>펫도 놀자</title>
        <!-- jQuery 라이브러리 로드 -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        
        <style>
            /* 헤더 스타일 */
            @import url('https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap');
            header {
                font-family: 'Sunflower', sans-serif;
                background-color: #1000;
                color: black;
                padding: 20px;
                text-align: center;
                height: 100px;
                position: relative;
                margin-bottom: 10px;
                
            }
    
            /* 내비게이션 메뉴 스타일 */
            nav ul {
                list-style: none;
                padding: 0;
                display: flex;
                justify-content: center;
            }
    
            nav ul li {
                margin-right: 20px;
            }
    
            nav ul li:last-child {
                margin-right: 0;
            }
    
            nav ul li a {
                color: black;
                text-decoration: none;
                font-size: 20px;
            }
    
            /* 로그인/아웃, 마이페이지 스타일 */
            .header-links {
                @import url('https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap');
                font-family: 'Sunflower', sans-serif;
                position: absolute;
                top: 50%;
                right: 20px;
                transform: translateY(-50%);
            }
    
            .header-links a {
                @import url('https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap');
                font-family: 'Sunflower', sans-serif;
                text-decoration: none;
                color: black;
                margin-right: 10px;
            }
    
            /* 원 스타일 */
            .circle {
                width: 30px;
                height: 30px;
                background-color: rgba(0, 0, 0, 0);
                border-radius: 50%;
                display: flex;
                justify-content: center;
                align-items: center;
                color: black;
                font-weight: bold;
                font-size: 11px;
                border: 1px solid rgba(0, 0, 0, 0.5);
            }
    
            /* 추가된 스타일 */
            .circle-container {
                float: right;
                margin-top: 50px;
            }
    
            hr { /* 회색 선 스타일 */
                border: none;
                height: 1px;
                background-color: rgba(0, 0, 0, 0.2);
                margin: 10px 0;
            }
        </style>
    </head>
    <body>
        <!-- 헤더 -->
        <header>
        <h1 id="homeLink">펫도 놀자</h1>
    
        <!-- 로그인/아웃, 마이페이지 -->
        <div class="header-links">
            <a href="/ehr/login/loginView.do">로그인/아웃</a> | <a href="/ehr/user/doRetrieve.do">마이 페이지</a>
        </div>
	    <script type="text/javascript">
		 // jQuery: h1 데이터 선택
	        $("#homeLink").on("click", function (e) {
	            e.preventDefault(); // 기본 동작 중단
	            console.log('----------------------------');
	            console.log('homeLink clicked');
	            console.log('----------------------------');
	
	            window.location.href = "/ehr/main/Main.do"; // 홈으로 이동
	        });
	    
		 // jquery: table 데이터 선택     
		    $("#userTable>tbody").on("click", "tr", function(e) {
		        console.log('----------------------------');
		        console.log('userTable>tbody');
		        console.log('----------------------------');
		
		        let tdArray = $(this).children(); // td
		
		        let userId = tdArray.eq(1).text();
		        console.log('userId:' + userId);
		
		        window.location.href = "/ehr/user/doSelectOne.do?userId=" + userId;
		
		    });
		
		    // 홈으로 돌아가는 이벤트 처리
		    $("#petLink").on("click", function(e) {
		        e.preventDefault(); // 기본 동작 중단
		        console.log('----------------------------');
		        console.log('petLink clicked');
		        console.log('----------------------------');
		
		        window.location.href = "/ehr/main/Main.do"; // 홈으로 이동
		    });
	
	    </script>
    
            <!-- 지도와 공지를 오른쪽에 정렬한 상태에서 공지를 원 안에 추가 -->
        <hr>
    </header>
</body>
</html>