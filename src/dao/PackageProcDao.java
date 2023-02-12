package dao;

import static db.JdbcUtil.close;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringJoiner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import vo.*;

public class PackageProcDao {
	private static PackageProcDao packgeProcDao;
	private Connection conn;
	private PackageProcDao() {}
	public static PackageProcDao getInstance() {
		if (packgeProcDao == null)	packgeProcDao = new PackageProcDao();
		return packgeProcDao;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public JsonArray getPackageListCount(String ccid) {
		Statement stmt = null;
		ResultSet rs = null;
		JsonArray packageList = new JsonArray();
		
		try {
			
			StringJoiner queryJoiner = new StringJoiner(" ");
			queryJoiner.add("SELECT a.cc_id, pi_code, pi_name, pi_img1, pi_adult");
			queryJoiner.add(  "FROM t_ctgr_city a");
			queryJoiner.add(  "JOIN t_package_info b ON a.cc_id = b.cc_id");
			queryJoiner.add( "WHERE a.cc_id like '" + ccid + "%'");
			queryJoiner.add(   "AND pi_suggest = 'y' ");
			
			
			System.out.println(queryJoiner.toString());
			
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryJoiner.toString());
			
			while (rs.next()) {
				JsonObject piDataObject = new JsonObject();
				
				piDataObject.addProperty("cc_id", rs.getString("cc_id"));
				piDataObject.addProperty("pi_code", rs.getString("pi_code"));
				piDataObject.addProperty("pi_name", rs.getString("pi_name"));
				piDataObject.addProperty("pi_img1", rs.getString("pi_img1"));
				piDataObject.addProperty("pi_adult", rs.getString("pi_adult"));
				packageList.add(piDataObject);
			}
			
			System.out.println("packageList : " + packageList);
			
		} catch (Exception e) {
			System.out.println("PackageProcDao 클래스의 getPackageListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return packageList;
	}
	
	public PackageInfo getDetailInfo(String picode) {
			Statement stmt = null;
			ResultSet rs = null;
			PackageInfo pi = null;
			
			try {
				String sql = "select * from t_package_info where pi_isview = 'y' and pi_code = '" + picode + "'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				if (rs.next()) {
					pi = new PackageInfo();
					pi.setPi_code(picode);
					pi.setCc_id(rs.getString("cc_id"));
					pi.setHi_code(rs.getString("hi_code"));
					pi.setPn_idx(rs.getInt("pn_idx"));
					pi.setPi_name(rs.getString("pi_name"));
					pi.setPi_period(rs.getString("pi_period"));
					pi.setPi_keyword(rs.getString("pi_keyword"));
					pi.setPi_img1(rs.getString("pi_img1"));
					pi.setPi_img2(rs.getString("pi_img2"));
					pi.setPi_img3(rs.getString("pi_img3"));
					pi.setPi_img4(rs.getString("pi_img4"));
					pi.setPi_img5(rs.getString("pi_img5"));
					pi.setPi_desc(rs.getString("pi_desc"));
					pi.setPi_special(rs.getString("pi_special"));
					pi.setPi_sale(rs.getInt("pi_sale"));
					pi.setPi_stock(rs.getInt("pi_stock"));
					pi.setPi_tour(rs.getString("pi_tour"));
					pi.setPi_food(rs.getString("pi_food"));
					pi.setPi_price(rs.getInt("pi_price"));
					pi.setPi_adult(rs.getInt("pi_adult"));
					pi.setPi_child(rs.getInt("pi_child"));
				}
			} catch (Exception e) {
				System.out.println("PackageProcDao 클래스의 getDetailInfo() 메소드 오류");
			} finally {
				close(rs);		close(stmt);
			}
			
			return pi;
		}
}
