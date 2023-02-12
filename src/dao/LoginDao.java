package dao;

import static db.JdbcUtil.*;	// JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰ� ����
import java.util.*;
import java.sql.*;
import vo.*;

public class LoginDao {
// �α��ο� ���õ� ���� �۾��� ó���ϴ� Ŭ����
	private static LoginDao loginDao;
	private Connection conn;
	private LoginDao() { }
	// �⺻�����ڸ� private�� �����Ͽ� �ܺο��� �Ժη� �ν��Ͻ��� �������� ���ϰ� ���Ƴ���.
	
	public static LoginDao getInstance() {
		// LoginDao Ŭ������ �ν��Ͻ��� ������ �ִ� �޼ҵ�� �̹� �ν��Ͻ��� ������ ������ �ν��Ͻ��� ���� 
		// LoginDao Ŭ������ �ν��Ͻ��� �ϳ��� �����Ͽ� ����ϰ� �ϴ� �̱��� ���
		
		if(loginDao == null) loginDao = new LoginDao();
		// �̹� ������ LoginDaoŬ������ �ν��Ͻ��� ������ ���Ӱ� �ν��Ͻ��� ���� 
		
		return loginDao;
	}
	
	public void setConnection(Connection conn) {
		// �� Dao Ŭ�������� ����� Ŀ�ؼ� ��ü�� �޾ƿͼ� �������ִ� �޼ҵ�
		this.conn = conn;
	}
	
	public MemberInfo getLoginInfo(String uid, String pwd) {
		// �޾ƿ� ���̵�� ��ȣ�� �α��� �۾��� ó���� �� ȸ������ MemberInfo �� �ν��Ͻ��� ����
		Statement stmt = null;
		ResultSet rs = null;
		MemberInfo loginInfo = null;
		
		try {
			String sql ="select * from t_member_info where mi_status <> 'c' and mi_id = '" 
		    + uid + "' and mi_pw = '" + pwd + "' ";
			//System.out.println(sql);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {	// rs�� �����Ͱ� ������(�� �α����� ����������)
				loginInfo = new MemberInfo();
				// ȸ�� ������ ������ �ν��Ͻ� ����
				loginInfo.setMi_id(uid);
				loginInfo.setMi_pw(pwd);
				loginInfo.setMi_name(rs.getString("mi_name"));
			    loginInfo.setMi_gender(rs.getString("mi_gender"));
			    loginInfo.setMi_registration(rs.getString("mi_registration"));
			    loginInfo.setMi_phone(rs.getString("mi_phone"));
			    loginInfo.setMi_email(rs.getString("mi_email"));
			    loginInfo.setMi_mileage(rs.getInt("mi_mileage"));
			    loginInfo.setMi_lastlogin(rs.getString("mi_lastlogin"));
			    loginInfo.setMi_joindate(rs.getString("mi_joindate"));
			    loginInfo.setMi_status(rs.getString("mi_status"));
			    loginInfo.setMi_lastfix(rs.getString("mi_lastfix"));
 
			}	// rs�� ������� else ���� loginInfo �ν��Ͻ��� null�� �ִ� ���·� ���� 
			
		} catch(Exception e) {
			System.out.println("LoginDao Ŭ������ getLoginInfo() �޼ҵ� �����߻�");
			e.printStackTrace();
		} finally {
			close(rs); close(stmt);
		}
		return loginInfo;
	}
}
