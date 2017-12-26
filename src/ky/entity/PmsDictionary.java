
package ky.entity;
import java.util.Date; 

/**
* ********************************************************
* @ClassName: PmsDictionary
* @Description: ???
* @author 用wzl写的自动生成
* @date 2017-12-26 上午 10:03:47 
*******************************************************
*/
public class PmsDictionary{

	private String description;		//??
	private Integer id;		//ID
	private String type;		//??
	private String value;		//?
	private String key;		//??
	private String canupdate;		//0 ??? 1???

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCanupdate() {
		return this.canupdate;
	}

	public void setCanupdate(String canupdate) {
		this.canupdate = canupdate;
	}

}

