package ky.util;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class SequenceUtil {
	
	public static String getSequence(String code, int length){
		String sql = "SELECT f_nextseq('"+code+"') seq";
		Connection conn = SpringContextUtil.getConnection("dataSource2");
		List<Map<String, Object>> list = JDBCHelper.query(conn, sql);
		Object l = (Long)list.get(0).get("seq");
		return StringUtils.lengthFix(""+l, length, '0', false);
	}

	public static void main(String[] args) {
		for(int i=0; i<10; i++){
			System.out.println(getSequence("order_no", 8));
		}
	}
}
