package WriteClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionManager
{
  public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//  public static final String URL = "jdbc:oracle:thin:@10.1.1.253:1521:orcl";
  public static final String URL = "jdbc:oracle:thin:@192.168.18.108:1521:huiyuan";
  public static final String UID = "huiyuan";
  public static final String PWD = "huiyuan123";
  protected Connection conn;
  protected PreparedStatement pstmt;
  protected ResultSet rs;

  public Connection getConn()
  {
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      this.conn = DriverManager.getConnection(URL, UID, PWD);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this.conn;
  }

  public void colseAll() {
    try {
      if (this.rs != null) {
        this.rs.close();
      }
      if (this.pstmt != null) {
        this.pstmt.close();
      }
      if (this.conn != null)
        this.conn.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}