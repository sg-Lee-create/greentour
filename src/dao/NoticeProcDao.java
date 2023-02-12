package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class NoticeProcDao {
// �������� �Խ��� ���� ���� �۾�(���, ���, ����, ����)���� ��� ó���ϴ� Ŭ����
	private static NoticeProcDao noticeProcDao;
	private Connection conn;
	private NoticeProcDao() {}
	public static NoticeProcDao getInstance() {
		if (noticeProcDao == null)	noticeProcDao = new NoticeProcDao();
		return noticeProcDao;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public int getNoticeListCount(String where) {
	// ���������� �˻��� ����� ���ڵ�(�Խñ�) ������ �����ϴ� �޼ҵ�
		int rcnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(*) from t_cs_notice " + where;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())		rcnt = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("NoticeProcDao Ŭ������ getFreeListCount() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs);		close(stmt);
		}
		
		return rcnt;
	}
	
	public ArrayList<CsNotice> getNoticeList(String where, int cpage, int psize) {
	// �������׿��� �˻��� ����� ���ڵ�(�Խñ�) ����� ArrayList�� �����ϴ� �޼ҵ�
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CsNotice> noticeList = new ArrayList<CsNotice>();		// �˻��� �Խñ۵��� ������ ArrayList<CsNotice>
		CsNotice cn = null;		// noticeList�� ������ �ϳ��� �Խñ� ������ ���� �ν��Ͻ�
		
		try {
			String sql = "select cn_idx, cn_ctgr, cn_title, cn_always, cn_isview, if (curdate() = date(cn_date), mid(cn_date, 12, 5), "
				+ " replace(mid(cn_date, 3, 8), '-', '.')) wdate from t_cs_notice " + where + " order by cn_idx desc limit " 
				+ ((cpage - 1) * psize) + ", " + psize;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				cn = new CsNotice();
				cn.setCn_idx(rs.getInt("cn_idx"));
				cn.setCn_ctgr(rs.getString("cn_ctgr"));
				cn.setCn_title(rs.getString("cn_title"));
				cn.setCn_always(rs.getString("cn_always"));
				cn.setCn_isview(rs.getString("cn_isview"));
				cn.setCn_date(rs.getString("wdate"));
				noticeList.add(cn);
			}
			
		} catch (Exception e) {
			System.out.println("NoticeProcDao Ŭ������ getNoticeList() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs);		close(stmt);
		}
		return noticeList;
	}

	public int readUpdate(int cnidx) {
	// ������ �Խñ��� ��ȸ���� ������Ű�� �޼ҵ�
		int result = 0;
		Statement stmt = null;
		
		try {
			String sql = "update t_cs_notice set cn_read = cn_read + 1 where cn_idx = " + cnidx;
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			System.out.println("NoticeProcDao Ŭ������ readUpdate() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
	
	public CsNotice getFreeInfo(int cnidx) {
	// ������ �Խñ��� �������� CsNotice �� �ν��Ͻ��� �����ϴ� �޼ҵ�
		Statement stmt = null;
		ResultSet rs = null;
		CsNotice cn = null;
		
		try {
			String sql = "select * from t_cs_notice where cn_isview = 'y' and cn_idx = " + cnidx;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				cn = new CsNotice();
				cn.setCn_idx(cnidx);
				cn.setCn_ctgr(rs.getString("cn_ctgr"));
				cn.setCn_title(rs.getString("cn_title"));
				cn.setCn_content(rs.getString("cn_content"));
				cn.setCn_read(rs.getInt("cn_read"));
				cn.setCn_date(rs.getString("cn_date"));
			}
			
		} catch (Exception e) {
			System.out.println("NoticeProcDao Ŭ������ getFreeInfo() �޼ҵ� ����");
		} finally {
			close(rs);		close(stmt);
		}
		
		return cn;
	}
	
}
