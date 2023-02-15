package svc;

import static db.JdbcUtil.*;
import java.util.*;

import com.google.gson.JsonArray;

import java.sql.*;
import dao.*;
import vo.*;

public class PackageMainSvc {
	public JsonArray getPackageMainCount(String ccid) {
		
		JsonArray packageMain = new JsonArray();
		
		Connection conn = getConnection();
		PackageProcDao packgeProcDao = PackageProcDao.getInstance();
		packgeProcDao.setConnection(conn);
		
		packageMain = packgeProcDao.getPackageMainCount(ccid);
		close(conn);
		
		return packageMain;
	}
}
