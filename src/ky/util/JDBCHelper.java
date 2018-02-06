package ky.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBCHelper {

	
	public static List<Map<String,Object>> query(Connection conn, String sql){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		PreparedStatement ps = null;
	    try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Map<String,Object> tempData = new HashMap<String,Object>();
				ResultSetMetaData rsm = rs.getMetaData();
				for(int j=1; j<=rsm.getColumnCount(); j++){
					Object obj = null;
					try{
						obj = rs.getObject(j);
					}catch(Exception e){
						System.out.println(">>>>>:" + obj);
					}
					tempData.put(rsm.getColumnLabel(j).toLowerCase(), obj); 
				}
				list.add(tempData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeStatement(ps);
		}
		
		return list;
	}


	public static void execute(Connection conn, String[] sqls){
		if(sqls == null){
			return ;
		}
		PreparedStatement ps = null;
		try {
			for(String sql : sqls){
				ps = conn.prepareStatement(sql);
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeStatement(ps);
		}
	}
	
	/**
	 * {CALL demoSp(? , ?)}
	 * @param call
	 * @return
	 */
	public static void call(Connection conn, String call){
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall(call);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeStatement(cs);
		}
	}
	
	public static void closeStatement(Statement stat){
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
