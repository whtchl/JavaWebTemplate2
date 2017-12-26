
package ky.entity;
import java.util.Date; 

/**
* ********************************************************
* @ClassName: TSysUser
* @Description: ???
* @author 用wzl写的自动生成
* @date 2017-12-21 下午 01:59:42 
*******************************************************
*/
public class TSysUser{

	private Integer userStatus;		//?????0??1???
	private String loginName;		//???
	private String trueName;		//????
	private Integer roleId;		//???????
	private String departmentId;		//????
	private Date pwdDate;		//??????
	private Integer id;		//???ID
	private String mobileno;		//????
	private String loginPwd;		//????
	private String email;		//????

	public Integer getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getTrueName() {
		return this.trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Date getPwdDate() {
		return this.pwdDate;
	}

	public void setPwdDate(Date pwdDate) {
		this.pwdDate = pwdDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobileno() {
		return this.mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

