package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;


@WebServlet("/mypackage_main_citys")
public class MypackageMainCitysCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    public MypackageMainCitysCtrl() { super(); }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nation = request.getParameter("nation");
		
		MypackageMainCitysSvc mypackageMainCitysSvc = new MypackageMainCitysSvc();
		ArrayList<CtgrCity> cityList = mypackageMainCitysSvc.getCityList(nation);
		
		request.setAttribute("cityList", cityList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("front/mypackage/mypackage_main.jsp");
		dispatcher.forward(request, response);
		
	}
}
