package WriteClass;

import java.io.File;
import java.util.Properties;

public class NewEntity {

	public static String entityName = "PMS_DEPARTMENT";

	public static void main(String[] args) {
		new WriteEntity().Write(entityName.trim());
	}
}