package dao;

import static db.JdbcUtil.*;	// JdbcUtil 클래스의 모든 멤버들을 자유롭게 사용하게 해줌
import java.util.*;
import java.sql.*;
import vo.*;

public class MypackageMainDao {
	private static MypackageMainDao mypackageMainDao;
	private Connection conn;
	private MypackageMainDao() { }
		
	public static MypackageMainDao getInstance() {		
		if(mypackageMainDao == null) mypackageMainDao = new MypackageMainDao();
		
		return mypackageMainDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<CtgrCity> getCityList(String nation){
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CtgrCity> cityList = new ArrayList<CtgrCity>();
		CtgrCity cc = null;
		
		try {
			String sql = "select * from t_ctgr_city where left(cc_id,3) = '" + nation + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				cc = new CtgrCity();
				cc.setCc_id(rs.getString("cc_id"));
				cc.setCc_name(rs.getString("cc_name"));
				cc.setCc_img1(rs.getString("cc_img1"));
				cityList.add(cc);
			}
			
		}catch(Exception e) {
			System.out.println("MypackageMainDao 클래스의 getCityList() 메소드(도시정보가져오기) 에러 발생");
			e.printStackTrace();
			
		}finally {
			close(rs);	close(stmt);
		}
		
		
		return cityList;
	}

}
