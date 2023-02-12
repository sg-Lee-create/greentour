package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeViewSvc {
	public int readUpdate(int cnidx) {
		int result = 0;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		result = noticeProcDao.readUpdate(cnidx);
		if (result == 1)		commit(conn);
		else					rollback(conn);
		close(conn);
		
		return result;
	}
	
	public CsNotice getFreeInfo(int cnidx) {
		CsNotice cn = null;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		cn = noticeProcDao.getFreeInfo(cnidx);
		close(conn);
		
		return cn;
	}

}
