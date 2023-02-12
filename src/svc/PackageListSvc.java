package svc;

import static db.JdbcUtil.*;
import java.util.*;

import com.google.gson.JsonArray;

import java.sql.*;
import dao.*;
import vo.*;

public class PackageListSvc {
	public JsonArray getPackageListCount(String ccid) {
		
		JsonArray packageList = new JsonArray();
		
		Connection conn = getConnection();
		PackageProcDao packgeProcDao = PackageProcDao.getInstance();
		packgeProcDao.setConnection(conn);
		
		packageList = packgeProcDao.getPackageListCount(ccid);
		close(conn);
		
		return packageList;
	}
}
