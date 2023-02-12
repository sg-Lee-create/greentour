package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/schPw")
public class SchPwCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SchPwCtrl() {super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String regist = request.getParameter("regist1") + "-" + request.getParameter("regist2");
		String url = request.getParameter("url").replace('$', '&');
		
		SchPwSvc schPwSvc = new SchPwSvc(); 
		MemberInfo mi = schPwSvc.getSchPw(uid, name, regist);
		
		request.setAttribute("mi", mi);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(mi != null) {	
			out.println("<script>");
			out.println("alert('ȸ������ ��й�ȣ�� " + mi.getMi_pw() + " �Դϴ�.');");
			out.println("location.replace('" + url + "');");
			out.println("</script>");
			out.close();
			
		} else if(uid == "" || name == "" || regist == "-") {
			out.println("<script>");
			out.println("alert('ȸ�� ������ �Է����ּ���.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		else {
			out.println("<script>");
			out.println("alert('��ġ�ϴ� ȸ�� ������ �����ϴ�.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
	}

}
