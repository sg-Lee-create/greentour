package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeListSvc {
	public int getNoticeListCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		rcnt = noticeProcDao.getNoticeListCount(where);
		close(conn);
		
		return rcnt;
	}
	public ArrayList<CsNotice> getNoticeList(String where, int cpage, int psize) {
		ArrayList<CsNotice> noticeList = new ArrayList<CsNotice>();
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		noticeList = noticeProcDao.getNoticeList(where, cpage, psize);
		close(conn);
		
		return noticeList;
	}
}
