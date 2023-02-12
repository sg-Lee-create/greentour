<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");

ArrayList<CsNotice> noticeList = (ArrayList<CsNotice>)request.getAttribute("noticeList");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");

int bsize = pageInfo.getBsize(), cpage = pageInfo.getCpage(), psize = pageInfo.getPsize();
int rcnt = pageInfo.getRcnt(), pcnt = pageInfo.getPcnt(), spage = pageInfo.getSpage();
String schtype = pageInfo.getSchtype(), keyword = pageInfo.getKeyword();

String schargs = "", args = "";
if (schtype != null && !schtype.equals("") && keyword != null && !keyword.equals("")) {		// 검색 조건(schtype)과 검색어(keyword)가 있으면 검색관련 쿼리스트링 생성
	schargs = "&schtype=" + schtype + "&keyword=" + keyword;	
}
args = "&cpage=" + cpage + schargs;
%>
<style>
#list tr { height: 30px; }
#list th, #list td { padding: 8px 3px; }
#list th { border-bottom: double black 3px; }
#list td { border-bottom: dotted black 1px; }
</style>
<h2>공지사항</h2>
<table width="700" cellpadding="0" cellspacing="0" id="list">
	<tr>
		<td colspan="2">
			<form name="frmSch" method="get">
			<!-- 
				<select name="schtype">
					<option value="nhf" <% if (schtype.equals("nhf")) { %>selected="selected"<% } %>>전체</option>
					<option value="notice" <% if (schtype.equals("notice")) { %>selected="selected"<% } %>>공지사항</option>
					<option value="hotel" <% if (schtype.equals("hotel")) { %>selected="selected"<% } %>>호텔</option>
					<option value="filght" <% if (schtype.equals("filght")) { %>selected="selected"<% } %>>항공권</option>
				</select>
			
			 -->
				<select name="schtype">
					<option value="tc" <% if (schtype.equals("tc")) { %>selected="selected"<% } %>>전체</option>
					<option value="title" <% if (schtype.equals("title")) { %>selected="selected"<% } %>>제목</option>
					<option value="content" <% if (schtype.equals("content")) { %>selected="selected"<% } %>>내용</option>
				</select>
				<input type="text" name="keyword" value="<%=keyword %>" />
				<input type="submit" value="검색" />
			</form>
		</td>
	</tr>
	<tr>
		<th width="15%">No</th>
		<th width="*">제목</th>
		<th width="20%">등록일</th>
	</tr>
<%
if (noticeList.size() > 0) {		// 게시글 목록이 있으면
	int num = rcnt - (psize * (cpage - 1));
	for (int i = 0; i < noticeList.size(); i++) {
		CsNotice cn = noticeList.get(i);
		
		String title = cn.getCn_title();
		if (title.length() > 30)		title = title.substring(0, 27) + "...";
		title = "<a href='notice_view?cnidx=" + cn.getCn_idx() + args + "'>" + title + "</a>";
		
%>
	<tr align="center">
		<td width="10%"><%=num %></td>
		<td width="*" align="left">[<%=cn.getCn_ctgr() %>]&nbsp;&nbsp;<%=title %></td>
		<td width="15%"><%=cn.getCn_date() %></td>

	</tr>
<%
		num--;
	}
} else {		// 공지사항 목록이 없으면
	out.println("<tr><td colspan='5' align='center'>등록된 공지사항이 없습니다.</td></tr>");
}
%>
</table>
<br />
<table width="700" cellpadding="5">
	<tr>
		<td width="600">
<%
if (rcnt > 0) {		// 게시글이 있으면 - 페이징 영역을 보여줌
	String lnk = "free_list?cpage=";
	pcnt = rcnt / psize;
	if (rcnt % psize > 0)		pcnt++;		// 전체 페이지 수(마지막 페이지 번호)
	
	if (cpage == 1) {
		out.println("[처음]&nbsp;&nbsp;&nbsp;[이전]&nbsp;&nbsp;");
	} else {
		out.println("<a href='" + lnk + 1 + schargs + "'>[처음]</a>&nbsp;&nbsp;&nbsp;");
		out.println("<a href='" + lnk + (cpage - 1) + schargs + "'>[이전]</a>&nbsp;&nbsp;");
	}
	
	spage = (cpage - 1) / bsize * bsize + 1;		// 현재 블록에서의 시작 페이지 번호
	for (int i = 1, j = spage; i <= bsize && j <= pcnt; i++, j++) {
	// i : 블록에서 보여줄 페이지의 개수 만큼 루프를 돌릴 조건으로 사용되는 변수
	// j : 실제 출력할 페이지 번호로 전체 페이지 개수(마지막 페이지 번호)를 넘지 않게 사용해야 함
		if (cpage == j)		out.println("&nbsp;<strong>" + j + "</strong>&nbsp;");
		else 				out.println("&nbsp;<a href='" + lnk + j + schargs + "'>" + j + "</a>&nbsp;");
	}
	
	if (cpage == pcnt) {
		out.println("&nbsp;&nbsp;[다음]&nbsp;&nbsp;&nbsp;[마지막]");	
	} else {
		out.println("&nbsp;&nbsp;<a href='" + lnk + (cpage + 1) + schargs + "'>[다음]</a>");
		out.println("&nbsp;&nbsp;&nbsp;<a href='" + lnk + pcnt + schargs + "'>[마지막]</a>");
	}
}
%>
		</td>
		<td width="*">
			<input type="button" value="글 등록" />
		</td>
	</tr>
</table>
</body>
</html>