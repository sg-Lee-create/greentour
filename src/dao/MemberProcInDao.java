package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class MemberProcInDao {
// ȸ�� ���Կ� ���õ� ���� �۾����� ó���ϴ� Ŭ����
	private static MemberProcInDao memberProcInDao;
	private Connection conn;
	private MemberProcInDao() {}	// �⺻ �����ڸ� private���� �����Ͽ� �ܺο��� �Ժη� �ν��Ͻ��� �������� ���ϰ� ����
	
	public static MemberProcInDao getInstance() {
	// MemberProcInDao Ŭ������ �ν��Ͻ��� ������ �ִ� �޼ҵ�� �̹� �ν��Ͻ��� ������ ������ �ν��Ͻ��� ����
	// MemberProcInDao Ŭ������ �ν��Ͻ��� �ϳ��� �����Ͽ� ����ϰ� �ϴ� �̱��� ���
		
		if (memberProcInDao == null)	memberProcInDao = new MemberProcInDao();
		// �̹� ������ MemberProcInDao Ŭ������ �ν��Ͻ��� ������ ���Ӱ� MemberProcInDao Ŭ������ �ν��Ͻ��� ����
		
		return memberProcInDao;
	}
	
	public void setConnection(Connection conn) {
	// �� MemberProcInDao Ŭ�������� ����� Ŀ�ؼ� ��ü�� �޾ƿͼ� ������ �ִ� �޼ҵ�
		this.conn = conn;
	}
	
	public int memberProcIn(MemberInfo memberInfo) {
	// ����ڰ� �Է��� �����͵�� ȸ������ ��Ű�� �޼ҵ�
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = "insert into t_member_info (mi_id, mi_pw, mi_name, mi_gender, mi_registration, mi_phone, mi_email, "
					+ "mi_mileage) values (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberInfo.getMi_id());
			pstmt.setString(2, memberInfo.getMi_pw());
			pstmt.setString(3, memberInfo.getMi_name());
			pstmt.setString(4, memberInfo.getMi_gender());
			pstmt.setString(5, memberInfo.getMi_registration());
			pstmt.setString(6, memberInfo.getMi_phone());
			pstmt.setString(7, memberInfo.getMi_email());
			pstmt.setInt(8, memberInfo.getMi_mileage());
			System.out.println(sql);
			result = pstmt.executeUpdate();
				
				if (result == 1) {		// t_member_info ���̺� insert ������ ���������� ����������
					sql = "insert into t_member_mileage (mi_id, mm_mileage, mm_desc, mm_detail) values (?, ?, '���� ���ϱ�', '')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, memberInfo.getMi_id());
					pstmt.setInt(2, memberInfo.getMi_mileage());
					result += pstmt.executeUpdate();

				}
		} catch (Exception e) {
			System.out.println("MemberProcInDao�� memberProcIn() �޼ҵ� ����");
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
}