package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;	// session 을 사용하기 위해 http.*;(all)


@WebServlet("/mypage_reserve")
public class MypageReserveCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MypageReserveCtrl() { super(); }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/front/mypage/mypage_reserve.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
