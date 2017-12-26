package WriteClassForMysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewEntity {

	// 数据库表名
	
	
	public static String entityName = "tbl_merchant_pos";

	/**
	 * 执行完还需配置两个文件 将生成的xml配置到mybatis.xml com/lng/manage/model/xml/***.xml
	 * 将生成的actionxml配置到struts-actions.xml struts/actions/lng/***.xml
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("确定要形成:" + entityName + "表的相关文件?确定请按回车!");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		do {
			str = bf.readLine();
			// if (str.length() == 0) { // 如果输入的字符串为空，则说明只输入了一个回车
			// System.out.println("输入的是回车！");
			// } else {
			// System.out.println("输入内容是：" + str);
			// }
		} while (str.length() != 0);

		new WriteEntity().Write(entityName.trim());
	}
}
