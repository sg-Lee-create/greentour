package ctrl;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/package_main")
public class PackageMainCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PackageMainCtrl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("front/package/package_main.jsp");
		dispatcher.forward(request, response);
	}

}
