package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;

public class DupIdDao {
	private static DupIdDao dupIdDao;
	private Connection conn;
	private DupIdDao() {}		// �⺻ �����ڸ� private���� �����Ͽ� �ܺο��� �Ժη� �ν��Ͻ��� �������� ���ϰ� ����
	
	public static DupIdDao getInstance() {
	// DupIdDao Ŭ������ �ν��Ͻ��� ������ �ִ� �޼ҵ�� �̹� �ν��Ͻ��� ������ ������ �ν��Ͻ��� ����
	// DupIdDao Ŭ������ �ν��Ͻ��� �ϳ��� �����Ͽ� ����ϰ� �ϴ� �̱��� ���
		
		if (dupIdDao == null)	dupIdDao = new DupIdDao();
		// �̹� ������ DupIdDao Ŭ������ �ν��Ͻ��� ������ ���Ӱ� DupIdDao Ŭ������ �ν��Ͻ��� ����
		
		return dupIdDao;
	}
	
	public void setConnection(Connection conn) {
	// �� DupIdDao Ŭ�������� ����� Ŀ�ؼ� ��ü�� �޾ƿͼ� ������ �ִ� �޼ҵ�
		this.conn = conn;
	}
	
	public int chkDupId(String uid) {
	// ȸ�� ���Խ� ����ڰ� ����� ���̵��� �ߺ� ���θ� ������ �޼ҵ�
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null; 
		
		try {
			stmt = conn.createStatement();
			String sql = "select count(*) from t_member_info where mi_id = '" + uid + "'";
			rs = stmt.executeQuery(sql);
			rs.next();		// count() �Լ��� ����߱⿡ �����Ͱ� ���� ���� ����
			result = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("DupIdDao Ŭ������ chkDupId() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);	
		}
		
		return result;
	}
}
