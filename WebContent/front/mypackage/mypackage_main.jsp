<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<CtgrCity> cityList = (ArrayList<CtgrCity>)request.getAttribute("cityList");


%>


<style>
.topbar_container{
	display: flex; justify-content: center; 
}
.topbar_container > div {
	margin: 20px; width:150px; height:60px; line-height : 30px; text-align: center; border: 1px solid lightgrey; border-radius: 10px;
}
.nation{
		display: flex; justify-content: center;
}

.nation > div {
	font-size:20px; margin: 20px; width:220px; height:60px; line-height : 60px; text-align: center; border: 1px solid lightgrey; border-radius: 10px;
}

.mp_all{
	display: flex;
	flex-wrap: nowrap;
	justify-content: space-between;
	
}

.change{
	width: 70%;
}

.mp_sidebar{
	width: 20%; border:1px solid lightgrey;
}
</style>

<script>
function selectNation(nation){
	$.ajax({
		type : "POST",
		url : "/greenTourSite/mypackage_main_citys",
		data : {"ciid" : nation },
		success : function(chkRs){
			if(chkRs == 0){	// 장바구니 담기에 실패했을 경우
				alert("나라 선택에 오류가 발생했습니다. 다시 시도해주세요.")
				return;
			} else {		// 장바구니 담기에 성공했을 경우
				location.reload();				
			}
		}		
	})
}
</script>

<div style="margin:80px;"></div>
<div class="topbar_container">
	<div><span>여행지 <br />STEP1</span></div>
	<div><span>날짜 <br />STEP2</span></div>
	<div><span>항공편 <br />STEP3</span></div>
	<div><span>호텔 <br />STEP4</span></div>
	<div><span>관광상품 <br />STEP5</span></div>
</div>

<div class="mp_all">
	<div class="change">
		<div class="nation">
			<div ><input type="hidden" name="JPN" value="JPN" onclick="selectNation(this.value);">일본</span></div>
			<div><input type="hidden" name="THA" value="THA" onclick="selectNation(this.value);">태국</div>
			<div><input type="hidden" name="PHL" value="PHL" onclick="selectNation(this.value);">필리핀</div>
			<div><input type="hidden" name="HKG" value="HKG" onclick="selectNation(this.value);">홍콩</div>
			<div><input type="hidden" name="SGP" value="SGP" onclick="selectNation(this.value);">싱가폴</div>
			<div><input type="hidden" name="VNM" value="VNM" onclick="selectNation(this.value);">베트남</div>
			<div><input type="hidden" name="TWN" value="TWN" onclick="selectNation(this.value);">대만</div>	
		</div>
		
		<div class="city">
		<%for(int i = 0; i < 10; i++) { %>
		<div><%= %></div>
		
		
		<% } %>
		</div>
	</div>
		

	
	<div class="mp_sidebar">
		<form name="frm">
			<input name="city" type="text" value="여행지" size="20">
			<input name="date" type="text" value="여행기간" >
			<input name="adult" type="text" value="성인">
			<input name="child" type="text" value="아동">
			<input name="air" type="text" value="항공편" >
			<input name="hotel" type="text" value="호텔">
			<br />
			<input type="button" name="prev" value="이전">
			<input type="button" name="next" value="다음">	
		</form>
		
		
	</div>
</div>




</body>
</html>