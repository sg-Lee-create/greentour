<%@page import="com.sun.javafx.geom.PickRay"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../_inc/inc_head.jsp"%>

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>

 
<script>
var befActive; // 이전 active값

function city(chkRs) {
	$("#"+befActive).removeClass("active"); // 이전 클릭된 active값 제거
	var data = new Object();
	data.ccid = chkRs;
	$.ajax({
		type : "POST",
		url : "/greenTourSite/package_main", 
		data : data,
		success : function(packageMain) {
			console.log(packageMain);
			$("#"+chkRs).addClass("active"); // 현재 클릭한곳에 active값 추가
			for (var i = 0; i < packageMain.length; i++) {
				var ccId = packageMain[i].cc_id;
				var pIcode = packageMain[i].pi_code;
				var pIname = packageMain[i].pi_name;
				var pIimg1 = packageMain[i].pi_img1;	
				var pIadult = packageMain[i].pi_adult;	
				console.log(packageMain[i]);
				$('.city' + i).find('img').attr("src", packageMain[i].pi_img1);
				$('.city' + i).find('a').attr("href", "package_list?picode=" + packageMain[i].pi_code);
				$('.city' + i).find('.info').find('.name').text(packageMain[i].pi_name);
				$('.city' + i).find('.info').find('.price').find('strong').text(packageMain[i].pi_adult);
			}
		}
	});
	
	befActive = chkRs; // 현재값이 전에 선택한 값이 됨
}


$(function(){
	city("JPN");
});

	
$( document ).ready( function() {
	$('.multiple-items').slick({
		infinite: true,
		slidesToShow: 3,
		slidesToScroll: 3
	});
});
</script>

<style>

img {
	width: 100%;
	height: 100%;
}

.content {
	width: 1130px;
	margin: 40px auto 30px auto;
}

.ctgr {
	padding-top: 25px;
	display: flex;
	justify-content: space-between;
}

.ctgr>a>span {
	border: 1px solid black;
	line-height: 60px;
	width: 150px;
	height: 60px;
	display: inline-block;
	text-align: center;
}

.content {
	position: relative;
	padding-top: 61px;
	margin: 0 0 70px;
	overflow: hidden;
	z-index: 1;
}

.recommand {
	position: relative;
	height: 630px;
}

ul {
	list-style: none;
}

.background {
	background: url(/greenTourSite/img/greentourhot.jpg) no-repeat center 0;
	background-size: cover;
	height: 220px;
}

.recommand>ul {
	top: 0px;
	left: 0px;
	z-index: 99;
	opacity: 1;
	display: block;
}

.city0 {
	position: absolute;
	left: 0;
	top: 0;
	width: 555px;
	height: 630px;
}

.city0 .info {
	position: absolute;
	left: 0;
	bottom: 0;
	width: 495px;
	padding: 30px;
}

.city0 .info .name {
	display: block;
	font-size: 22px;
	max-height: 67px;
	margin: 0 0 10px;
	line-height: 1.5;
	font-weight: bold;
	word-break: break-all;
	word-wrap: break-word;
	overflow: hidden;
}

.price {
	display: block;
	font-size: 19px;
}

.city1 {
	position: absolute;
	right: 0;
	top: 0;
	width: 546px;
	height: 300px;
}

.city1 .info {
	position: absolute;
	left: 0;
	bottom: 0;
	width: 506px;
	padding: 20px;
}

.city1 .info .name {
	display: block;
	height: 30px;
	margin: 0 0 13px;
	font-size: 20px;
	line-height: 1.5;
	font-weight: bold;
	white-space: nowrap;
	text-overflow: ellipsis;
	word-break: break-all;
	word-wrap: break-word;
	overflow: hidden;
}

.city1 .info .price {
	display: block;
	font-size: 14px;
}

.city2 {
	position: absolute;
	left: 585px;
	bottom: 0;
	width: 258px;
	height: 300px;
}

.city2 .info {
	position: absolute;
	left: 0;
	bottom: 0;
	width: 218px;
	padding: 20px;
}

.city2 .info .name {
	display: block;
	height: 30px;
	margin: 0 0 11px;
	font-size: 20px;
	line-height: 1.4;
	font-weight: bold;
	white-space: nowrap;
	text-overflow: ellipsis;
	word-break: break-all;
	word-wrap: break-word;
	overflow: hidden;
}

.city2 .info .price {
	display: block;
	font-size: 14px;
}

.city3 {
	position: absolute;
	right: 0;
	bottom: 0;
	width: 257px;
	height: 300px;
}

.city3 .info {
	position: absolute;
	left: 0;
	bottom: 0;
	width: 217px;
	padding: 20px;
}

.city3 .info .name {
	display: block;
	height: 30px;
	margin: 0 0 11px;
	font-size: 20px;
	line-height: 1.4;
	font-weight: bold;
	white-space: nowrap;
	text-overflow: ellipsis;
	word-break: break-all;
	word-wrap: break-word;
	overflow: hidden;
}

.city3 .info .price {
	display: block;
	font-size: 14px;
}

.keyword {
	padding: 20px 36px;
}

.keyword>h2 {
	color: white
}

.links>a {
	display: inline-block;
	color: white;
	margin-left: 5px;
	padding: 0 10px;
	font-size: 18px;
}

.active {
	background-color: skyblue;
}

.tourslide{
	margin-bottom: 70px;
}

.multiple-items{
	border: 1px solid #ccc;
    border-right: none;
    background: #fff;
    height: 343px;
    width: 1132px;
}

.multiple-items .slick-arrow{
	font-size: 0;
    line-height: 0;
    position: absolute;
    top: -12%;
    display: block;
    width: 20px;
    height: 20px;
    padding: 0;
}


.slick-next {
    right: 32px;
}

.slick-prev {
    left: 90%;
}
.slick-prev:before, .slick-next:before {
    font-family: 'slick';
    font-size: 47px;
    line-height: 1;
    opacity: .75;
    color: black;
}

.slimg{
	display: block;
	height: 212px;
    margin: 0 0 22px;
}
.sltag{
	display: block;
    z-index: 100;
    padding: 20px 20px 0;
    border-right: 1px solid #ccc;
}

.slider_content .info{
	height: 88px;
}

.title {
	display: block;
    width: 325px;
    height: 38px;
    font-size: 14px;
    color: #111;
    overflow: hidden;
    white-space: normal;
}
</style>

<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>



<div class="maincontainer">
	<!-- 카테고리 -->
	<div class="ctgr">
		<a href="#" onclick="city('JPN');"><span id="JPN" class="active">일본</span></a>
		<a href="#" onclick="city('THA');"><span id="THA">태국</span></a>
		<a href="#" onclick="city('PHL');"><span id="PHL">필리핀</span></a>
		<a href="#" onclick="city('HKG');"><span id="HKG">홍콩</span></a>
		<a href="#" onclick="city('SGP');"><span id="SGP">싱가포르</span></a>
		<a href="#" onclick="city('VNM');"><span id="VNM">베트남</span></a>
		<a href="#" onclick="city('TWN');"><span id="TWN">대만</span></a>
	</div>
	<!-- 추천상품 -->
	<div class="content">
		<h2>추천상품</h2>
		<div class="recommand">
			<ul>
				<li class="city0">
					<a href="">
					  <img src="" alt="">
				        <div class="info">
				            <span class="name">1</span>
				            <span class="price"><strong></strong>원~</span>
				        </div>
					</a>
				</li>
				<li class="city1">
					<a href="">
					  <img class="recoimg" src="" alt="">
				        <div class="info">
				            <span class="name"></span>
				            <span class="price"><strong></strong>원~</span>
				        </div>
					</a>
				</li>
				<li class="city2">
					<a href="">
					  <img class="recoimg" src="" alt="">
				        <div class="info">
				            <span class="name"></span>
				            <span class="price"><strong></strong>원~</span>
				        </div>
					</a>
				</li>
				<li class="city3">
					<a href="">
					  <img class="recoimg" src="" alt="">
				        <div class="info">
				            <span class="name"></span>
				            <span class="price"><strong></strong>원~</span>
				        </div>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 키워드 -->
	<div class="background">
		<div class="keyword">
			<h2>HOT 키워드<br />
				<sub>키워드를 클릭하면 해당 상품을 보실 수 있습니다.</sub>
			</h2>
			<div class="links_wrap">
				<div class="links">
					<a href="" style="background: #2DB400;"data-log="S_PKG_THE88_ARE9_hot_이집트가봤니?">#이집트가봤니?</a>
					<a href="" style="background: #2DB400;"data-log="S_PKG_THE88_ARE9_hot_동유럽발칸 출발확정">#동유럽발칸 출발확정</a> 
					<a href="" style="background: #2DB400;"data-log="S_PKG_THE88_ARE9_hot_서유럽출발확정">#서유럽출발확정</a><br />
					<a href="" style="background: #2DB400;"data-log="S_PKG_THE88_ARE9_hot_올라~스페인">#올라~스페인</a>
					<a href="" style="background: #2DB400;"data-log="S_PKG_THE88_ARE9_hot_비즈니스석타고 가는 스페인">#비즈니스석타고 가는 스페인</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 패키지 슬라이더 -->
	<div class="tourslide">
	<h3>23년 일본 인기여행지</h3>
		<div class="multiple-items">
			<a class="sltag" href="#">
			<div class="slider_content">
				<span class="slimg">
				<img src="front/package/img/1.jpg">
				</span>
				<div class="info">
	            	<span class="title">[무비자/무격리]몽골/테렐지 별빛투어[무비자/무격리]몽골/테렐지 별빛투어</span>
	            	<span class="price"><strong>100,000</strong>원~</span>
	        	</div>
			</div>
			</a>
			<a class="sltag"href="#">
			<div class="slider_content">
				<span class="slimg">
				<img src="front/package/img/1.jpg">
				</span>
				<div class="info">
	            	<span class="title">[무비자/무격리]몽골/테렐지 별빛투어</span>
	            	<span class="price"><strong>100,000</strong>원~</span>
	        	</div>
			</div>
			</a>
			<a class="sltag"href="#">
			<div class="slider_content">
				<span class="slimg">
				<img src="front/package/img/1.jpg">
				</span>
				<div class="info">
	            	<span class="title">3</span>
	            	<span class="price"><strong>100,000</strong>원~</span>
	        	</div>
			</div>
			</a>
			<a class="sltag"href="#">
			<div class="slider_content">
				<span class="slimg">
				<img src="front/package/img/1.jpg">
				</span>
				<div class="info">
	            	<span class="title">4</span>
	            	<span class="price"><strong>100,000</strong>원~</span>
	        	</div>
			</div>
			</a>
			<a class="sltag"href="#">
			<div class="slider_content">
				<span class="slimg">
				<img src="front/package/img/1.jpg">
				</span>
				<div class="info">
	            	<span class="title">5</span>
	            	<span class="price"><strong>100,000</strong>원~</span>
	        	</div>
			</div>
			</a>

		</div>
	</div>
</div>
</body>
</html>