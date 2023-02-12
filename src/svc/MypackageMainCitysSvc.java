package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class MypackageMainCitysSvc {
	
	public ArrayList<CtgrCity> getCityList(String nation){
		ArrayList<CtgrCity> cityList = new ArrayList<CtgrCity>();
		
		Connection conn = getConnection();
		MypackageMainDao mypackageMainDao = MypackageMainDao.getInstance();
		mypackageMainDao.setConnection(conn);
		
		cityList = mypackageMainDao.getCityList(nation);
		close(conn);
		
		return cityList;
		
	}
}
