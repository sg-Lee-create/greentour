package dao;

import static db.JdbcUtil.close;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import vo.*;

public class PackageProcDao {
	private static PackageProcDao packageProcDao;
	private Connection conn;
	private PackageProcDao() {}
	public static PackageProcDao getInstance() {
		if (packageProcDao == null)	packageProcDao = new PackageProcDao();
		return packageProcDao;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public JsonArray getPackageMainCount(String ccid) {
		Statement stmt = null;
		ResultSet rs = null;
		JsonArray packageMain = new JsonArray();
		
		try {
			String sql = "select a.cc_id, pi_code, pi_name, pi_img1, FORMAT(pi_adult, 0) as pi_adult from t_ctgr_city a join t_package_info b on a.cc_id = b.cc_id where a.cc_id like '" + ccid + "%' and pi_suggest = 'y' ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				JsonObject piDataObject = new JsonObject();
				
				piDataObject.addProperty("cc_id", rs.getString("cc_id"));
				piDataObject.addProperty("pi_code", rs.getString("pi_code"));
				piDataObject.addProperty("pi_name", rs.getString("pi_name"));
				piDataObject.addProperty("pi_img1", rs.getString("pi_img1"));
				piDataObject.addProperty("pi_adult", rs.getString("pi_adult"));
				packageMain.add(piDataObject);
				// System.out.println(piDataObject);
			}
			
		} catch (Exception e) {
			System.out.println("PackageProcDao 클래스의 getPackageMainCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return packageMain;
	}
	
	public ArrayList<PackageDate> getPackageListCount(String picode) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<PackageDate> pdList = new ArrayList<PackageDate>();
		PackageDate pd = null;
		
		try {
			String sql = "select distinct a.pi_code, b.pi_name, b.pi_img1, FORMAT(b.pi_adult, 0) as pi_adult, date_format(c.fi_departure, '%Y-%m-%d') "
					+ " as fi_departure from t_package_date a join t_package_info b on a.pi_code = b.pi_code "
					+ " join t_flight_info c on a.fi_code = c.fi_code where a.pi_code = '" + picode + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pd = new PackageDate();		// 상품 정보를 저장할 인스턴스 생성
				pd.setPi_code(rs.getString("pi_code"));
				pd.setPi_name(rs.getString("pi_name"));
				pd.setPi_img1(rs.getString("pi_img1"));
				pd.setPi_adult(rs.getString("pi_adult"));
				pd.setFi_departure(rs.getString("fi_departure"));
				pdList.add(pd);
			}
			
		} catch (Exception e) {
			System.out.println("PackageProcDao 클래스의 getPackageListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return pdList;
	} 
	
	public JsonArray getPackageDateList(String picode, String fiDeparture) {
		Statement stmt = null;
		ResultSet rs = null;
		JsonArray packageList = new JsonArray();
		
		try {
			String sql = "select a.pi_code, b.fi_departure, b.fi_cityleave, c.fr_name, d.pi_period, "
					+ "d.pi_name, FORMAT(d.pi_price, 0) as pi_price, d.pi_stock, d.pi_sale "
					+ " from t_package_date a "
					+ " join t_flight_info b on a.fi_code = b.fi_code "
					+ " join t_flight_realprice c on b.cc_id = c.cc_id "
					+ " join t_package_info d on a.pi_code = d.pi_code "
					+ " where c.fr_grade = 'a' "
					+ " and a.pi_code = '"+picode+"'"
					+ " and date_format(b.fi_departure, '%Y-%m-%d') = '"+fiDeparture+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				JsonObject piDataObject = new JsonObject();
				
				piDataObject.addProperty("pi_code", rs.getString("pi_code"));
				piDataObject.addProperty("fi_departure", rs.getString("fi_departure"));
				piDataObject.addProperty("fi_cityleave", rs.getString("fi_cityleave"));
				piDataObject.addProperty("fr_name", rs.getString("fr_name"));
				piDataObject.addProperty("pi_period", rs.getString("pi_period"));
				piDataObject.addProperty("pi_name", rs.getString("pi_name"));
				piDataObject.addProperty("pi_price", rs.getString("pi_price"));
				piDataObject.addProperty("pi_stock", rs.getString("pi_stock"));
				piDataObject.addProperty("pi_sale", rs.getString("pi_sale"));
				packageList.add(piDataObject);
				// System.out.println(piDataObject);
			}
			
		} catch (Exception e) {
			System.out.println("PackageProcDao 클래스의 getPackageDateList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return packageList;
	}
	
}
