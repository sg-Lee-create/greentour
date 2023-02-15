package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import svc.PackageMainSvc;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String ccid = request.getParameter("ccid");
		// System.out.println(ccid);
		
		PackageMainSvc packageMainSvc = new PackageMainSvc();

		JsonArray packageMain = packageMainSvc.getPackageMainCount(ccid);

		response.setContentType("application/json; charset=UTF-8;");
		PrintWriter out = response.getWriter();
		out.println(packageMain); // 장바구니 담기 기능을 호출했던 ajax를 사용한 곳으로 결과값을 리턴
	}
}
