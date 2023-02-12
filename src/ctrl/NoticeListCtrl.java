package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;

@WebServlet("/notice_list")
public class NoticeListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeListCtrl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cpage = 1, psize = 10, bsize = 10, rcnt = 0, pcnt = 0, spage = 0;
		// ���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(�Խñ�) ����, ������ ����, ���� ������ ���� ������ ������
		
		if (request.getParameter("cpage") != null)		cpage = Integer.parseInt(request.getParameter("cpage")); 
		// cpage ���� ������ �޾Ƽ� int ������ ����ȯ ��Ŵ (���Ȼ��� ������ ��� ������ �ؾ� �ϱ� ����)
		
		String schtype = request.getParameter("schtype");		// �˻� ����(����, ����, ���� + ����)
		String keyword = request.getParameter("keyword");		// �˻���
		String where = " where cn_isview = 'y' ";		// �˻� ������ ���� ��� where ���� ������ ����
		
		if (schtype == null || keyword == null) {
			schtype = "";		keyword = "";
			// ȭ��� �˻�� 'null'�� ������ �ʰ� �ϱ� ���� �� ���ڿ��� ä��
			// (�˻����� ������� �޺��ڽ� selected �ϱ� ���� equals()�� �˻��� �� nullPointerException �߻��� �����ϱ� ����) // 
		} else if (!schtype.equals("") && !keyword.equals("")) {		// �ܼ��� null�� �ƴѰ� �ƴ϶� �� ���ڿ��� �Ÿ��� ���� �� ���ǹ� //
		// �˻� ���ǰ� �˻�� ��� ���� ���
			URLEncoder.encode(keyword, "UTF-8");
			// ���� ��Ʈ������ �ְ� �޴� �˻�� �ѱ� �� ��� �������� ���� ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ
			if (schtype.equals("tc")) {		// �˻� ������ '���� + ����'�� ���
				where += " and (cn_title like '%" + keyword + "%' or cn_content like '%" + keyword + "%')";
			} else {		// �˻� ������ '����'�̳� '����'�� ���
				where += " and cn_" + schtype + " like '%" + keyword + "%'";
			}
			
		}
		
		NoticeListSvc noticeListSvc = new NoticeListSvc();
		rcnt = noticeListSvc.getNoticeListCount(where);		// �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü ������ �� ����� ���� �ʿ�
		
		ArrayList<CsNotice> noticeList = noticeListSvc.getNoticeList(where, cpage, psize);
		// ��� ȭ�鿡�� ������ �Խñ� ����� ArrayList<BbsFree> ������ �޾ƿ�, �ʿ��� ��ŭ�� �޾ƿ��� ���� cpage �� psize�� �ʿ�
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCpage(cpage);
		pageInfo.setPsize(psize);
		pageInfo.setBsize(bsize);
		pageInfo.setRcnt(rcnt);
		pageInfo.setPcnt(pcnt);
		pageInfo.setSpage(spage);
		pageInfo.setSchtype(schtype);
		pageInfo.setKeyword(keyword);
		// ����¡�� �˻��� �ʿ��� �������� PageInfo�� �ν��Ͻ��� ����
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageInfo", pageInfo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cs/notice_list.jsp");
		dispatcher.forward(request, response);
		
	}

}
