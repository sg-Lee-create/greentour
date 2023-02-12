package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;	// session �� ����ϱ� ���� http.*;(all)


@WebServlet("/sch_pw_form")
public class SchPwForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SchPwForm() {super();}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		// JSP�� �ƴϹǷ� ������ ����Ϸ��� ���� HttpSession Ŭ������ �ν��Ͻ��� �����ؾ� ��.
		if(session.getAttribute("loginInfo") != null) {
			// ���� �α����� �Ǿ��ִ� ���¶�� 
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�߸��� ��η� �����̽��ϴ�');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("sch_pw_form.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
