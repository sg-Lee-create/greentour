package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class PackageDetailSvc {
	public PackageInfo getDetailInfo(String picode) {
		PackageInfo pi = null;
		Connection conn = getConnection();
		PackageProcDao packageProcDao = PackageProcDao.getInstance();
		packageProcDao.setConnection(conn);
		
		pi = packageProcDao.getDetailInfo(picode);
		close(conn);
		
		return pi;
	}
}
