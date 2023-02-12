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
		int result = noticeViewSvc.readUpdate(cnidx);			// ����ڰ� ������ �Խñ��� ��ȸ���� ������Ű�� �޼ҵ� ȣ��
		
		CsNotice cn = noticeViewSvc.getFreeInfo(cnidx);		// ����ڰ� ������ �Խñ��� ������� CsNotice�� �ν��Ͻ��� �޾ƿ�
		

		if (cn == null) {		// ������ �Խñ��� ������
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�߸��� ��η� �����̽��ϴ�.);");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		
		} else {		// ������ �Խñ��� ������
			request.setAttribute("cn", cn);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cs/notice_view.jsp");
			dispatcher.forward(request, response);
		
		}		
	}

}
