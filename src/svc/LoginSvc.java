package svc;

import static db.JdbcUtil.*;	// JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰ� ����
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoginSvc {
	// �α��ο� �ʿ��� ���̵�� ��ȣ�� �޾� ����Ͻ� ������ ó��(���� �۾� ����. ������ dao ����.)�ϴ� Ŭ����
	public static MemberInfo getLoginInfo(String uid, String pwd) {
		Connection conn = getConnection();
		LoginDao loginDao = LoginDao.getInstance();
		loginDao.setConnection(conn);
		
		MemberInfo loginInfo = loginDao.getLoginInfo(uid, pwd);
		close(conn);
		
		return loginInfo;
	}
}