<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
CsNotice cn = (CsNotice)request.getAttribute("cn");

int cnidx = cn.getCn_idx();
int cpage = Integer.parseInt(request.getParameter("cpage"));
String args = "?cpage=" + cpage;		// 링크에서 사용할 쿼리 스트링
String schtype = request.getParameter("schtype");
String keyword = request.getParameter("keyword");
if (schtype != null && !schtype.equals("") && keyword != null && !keyword.equals("")) {
	args += "&schtype=" + schtype + "&keyword=" + keyword;
}

%>
<style>
.reList { width: 700px; }
.reWriter {
	width: 700px; padding: 5px; 
	display: flex; 
	justify-content: space-between; 
	border: 1px solid #000;
}
.reContent {
	width: 700px; padding: 5px; 
	border: 1px solid #000; 
	border-top: none;
	margin-bottom: 5px;
}
</style>
<h2>공지사항</h2>
<table width="700" cellpadding="5">
	<tr>
		<td width="60%">[<%=cn.getCn_ctgr()%>]<%=cn.getCn_title() %></td>
		<td width="10%"><%=cn.getCn_read() %></td>
		<td width="*"><%=cn.getCn_date() %></td>
	</tr>
	<tr>
		<th>내용</th><td colspan="5"><%=cn.getCn_content().replaceAll("\r\n", "<br />") %></td>
	</tr>
</table>
<br /><hr align="left" width="700" /><br />
<p style="width: 700px" align="center">
	<input type="button" value="목록" onclick="location.href='notice_list<%=args %>';" />
</p>
<br /><hr align="left" width="700" /><br />
</body>
</html>