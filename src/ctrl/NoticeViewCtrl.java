package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/notice_view")
public class NoticeViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeViewCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cnidx = Integer.parseInt(request.getParameter("cnidx"));
		
		NoticeViewSvc noticeViewSvc = new NoticeViewSvc();
		int result = noticeViewSvc.readUpdate(cnidx);			// 사용자가 선택한 게시글의 조회수를 증가시키는 메소드 호출
		
		CsNotice cn = noticeViewSvc.getFreeInfo(cnidx);		// 사용자가 선택한 게시글의 내용들을 CsNotice형 인스턴스로 받아옴
		

		if (cn == null) {		// 보여줄 게시글이 없으면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로로 들어오셨습니다.);");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		
		} else {		// 보여줄 게시글이 있으면
			request.setAttribute("cn", cn);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cs/notice_view.jsp");
			dispatcher.forward(request, response);
		
		}		
	}

}
