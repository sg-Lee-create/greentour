package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/member_proc_in")
public class MemberProcInCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberProcInCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// �޾ƿ� ȸ���������� ������ �ν��Ͻ� ����
		MemberInfo memberInfo = new MemberInfo();
		
		memberInfo.setMi_id(request.getParameter("mi_id").trim().toLowerCase());
		memberInfo.setMi_pw(request.getParameter("mi_pw1"));
		String pwd2 = request.getParameter("mi_pw2");
		memberInfo.setMi_name(request.getParameter("mi_name").trim());
		memberInfo.setMi_gender(request.getParameter("mi_gender"));
		memberInfo.setMi_registration(request.getParameter("mi_registration1") + "-" + request.getParameter("mi_registration2"));
		memberInfo.setMi_phone(request.getParameter("p1") + "-" + request.getParameter("p2") + "-" + request.getParameter("p3"));
		memberInfo.setMi_email(request.getParameter("e1").trim() + "@" + request.getParameter("e3").trim());
		memberInfo.setMi_mileage(1000);
		
		
		MemberProcInSvc memberProcInSvc = new MemberProcInSvc();
		int result = memberProcInSvc.memberProcIn(memberInfo);
		
		if (result == 2) {		// ���������� ȸ�������� �̷�� ������
			response.sendRedirect("login_form");
		} else {		// ȸ�� ���� ����
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ȸ�� ���Կ� �����߽��ϴ�.\\n�ٽ� �õ��ϼ���.");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}

}
