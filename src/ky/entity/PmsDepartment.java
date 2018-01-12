
package ky.entity;
import java.util.Date; 

/**
* ********************************************************
* @ClassName: PmsDepartment
* @Description: ???
* @author 用wzl写的自动生成
* @date 2018-01-12 下午 03:56:01 
*******************************************************
*/
public class PmsDepartment{

	private String oemNumber;		//??????
	private String departmentName;		//????
	private Integer id;		//??id
	private String departmentNum;		//????

	public String getOemNumber() {
		return this.oemNumber;
	}

	public void setOemNumber(String oemNumber) {
		this.oemNumber = oemNumber;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentNum() {
		return this.departmentNum;
	}

	public void setDepartmentNum(String departmentNum) {
		this.departmentNum = departmentNum;
	}

}

