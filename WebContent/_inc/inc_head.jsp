<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*" %>
<%
MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
boolean isLogin = false;
String miid = "", miname = "";
if (loginInfo != null) { 
	isLogin = true;
	miid = loginInfo.getMi_id();
	miname = loginInfo.getMi_name();
}



// 로그인 여부를 판단할 변수 isLogin 생성
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<script>
function onlyNum(obj){
	if(isNaN(obj.value)){	// 숫자가 아니면 참인 함수 isNan()
		obj.value = "";
	}
}
</script>
<style>
body{ max-width: 1130px; margin: 0 auto; }
a{
	color:black;
	text-decoration-line: none;
}

header{width: 1130px; 
    margin: 0 auto;
}
#title {
	width:100%;
    display: flex; height: 130px;
    justify-content: space-between;
    align-items: center;
}
h1 {font-size:45px; color:rgb(112,173,71);}
.frmSch {background-color:white; width:700px; text-align:center; }
.sch {height:32px;}
.frmSch .text {width:60%; height:25px; padding:2px; 
    border-radius: 5px; border: 1px solid grey; }

#m_menu { width:230px; 
    display: flex;
    flex-wrap: wrap;
    
}
#m_menu a {
    margin:5px 5px;
    
}
.my_menu{
    border:1px solid lightgrey; line-height: 40px;
    width:90px; height: 40px; border-radius: 10px;
    display:inline-block; text-align: center; 
    
}
#menu {display: flex;
    justify-content: space-between;
}
#menu a {}
#menu .main_menu {
    border:1px solid lightgrey; line-height: 60px;
    width:150px; height: 60px;
    display:inline-block; text-align: center; 
}

</style>
<body>
    <header>
        <div id="title">
            <h1>그린투어</h1>
            <form class="frmSch" name="frmSch" method="get">
                <input class="text" type="text" name="keyword" placeholder="&nbsp;여행지를 검색해보세요."/>
                <input class="sch" type="submit" value="검색" />
            </form>
            <div id="m_menu">
            	<%if(!isLogin){ %>
                <a href="login_form"><span class="my_menu">로그인</span></a>
                <a href="join_form"><span class="my_menu">회원가입</span></a>
                <a href="#"><span class="my_menu">마이페이지</span></a>
                <a href="#"><span class="my_menu">예약확인</span></a>
                <% } else { %>
                <a href="#"><span class="my_menu"><%=miid %>님</span></a>
                <a href="/greenTourSite/logout.jsp"><span class="my_menu">로그아웃</span></a>
                <a href="mypage_reserve"><span class="my_menu">마이페이지</span></a>
                <a href="#"><span class="my_menu">예약확인</span></a>
                <% } %>
            </div>
        </div>
        
        <nav id="menu">
            <a href="package_main"><span class="main_menu">패키지</span></a>
            <a href="#"><span class="main_menu">나만의 패키지</span></a>
            <a href="#"><span class="main_menu">패키지 후기</span></a>
            <a href="#"><span class="main_menu">여행도시</span></a>
            <a href="notice_list"><span class="main_menu">고객센터</span></a>
        </nav>
    </header>