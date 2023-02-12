package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/login")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid").trim().toLowerCase();
		String pwd = request.getParameter("pwd").trim();
		String url = request.getParameter("url").replace('$', '&');
		// ������Ʈ���� �ִ� url ���� '&'�� '$'�� ������� �޾ƿ��� ������ �ٽ� ������Ѿ� �� 
		
		LoginSvc loginSvc = new LoginSvc(); 
		MemberInfo loginInfo = loginSvc.getLoginInfo(uid, pwd);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(loginInfo != null) {		// �α��� ������
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			
			out.println("<script>");
			out.println("location.replace('" + url + "');");
			out.println("</script>");
			out.close();
			
		}else {		// �α��� ���н� 
			
			out.println("<script>");
			out.println("alert('���̵�� ��ȣ�� Ȯ���ϼ���');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}
}