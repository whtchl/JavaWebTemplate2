package WriteClassForMysql;
import java.sql.*;
public class ConnectionManager {
//	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//	public static final String URL = "jdbc:oracle:thin:@10.1.1.253:1521:orcl";
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://192.168.1.200:3306/huitouke_v41?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;mysqlEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull";
	public static final String UID = "huitouke_v41";
	public static final String PWD = "whlshLwhy2882lLH";

	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	public Connection getConn() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, UID, PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void colseAll() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
