package ctrl;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import svc.*;
import vo.*;

@WebServlet("/package_list")
public class PackageListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PackageListCtrl() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		String ccid = request.getParameter("ccid");
		System.out.println("ccid : " + ccid);

		PackageListSvc packageListSvc = new PackageListSvc();
		
		JsonArray packageList = packageListSvc.getPackageListCount(ccid);
		System.out.println("packageList : " + packageList);
		
		response.setContentType("application/json; charset=UTF-8;");
		PrintWriter out = response.getWriter();
		out.println(packageList);		// ��ٱ��� ��� ����� ȣ���ߴ� ajax�� ����� ������ ������� ����
	}

}
