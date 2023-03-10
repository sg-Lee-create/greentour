package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;	// session 을 사용하기 위해 http.*;(all)

@WebServlet("/sch_id_form")
public class SchIdForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SchIdForm() {super();}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		// JSP가 아니므로 세션을 사용하려면 직접 HttpSession 클래스의 인스턴스를 생성해야 함.
		if(session.getAttribute("loginInfo") != null) {
			// 현재 로그인이 되어있는 상태라면 
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로로 들어오셨습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("sch_id_form.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
