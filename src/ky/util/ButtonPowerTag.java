package ky.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import ky.entity.TSysUser;

public class ButtonPowerTag extends TagSupport   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4080131382305336189L;
	//禁止展示的用户id
	private String userIdDeny;
	//允许展示的用户id
	private String userIdAllowDeny;
	//角色id
	private String roleIdDeny;
	//部门
	private String departmentDeny;
	//允许展示的用户ID  可多个
	private String userIdAllow;
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		TSysUser user =  (TSysUser)request.getSession().getAttribute("user");
		if(user==null)
		{
			return SKIP_BODY;
		}
		
		//如果用户id包含在允许的id中跳过
		if(StringUtils.isNotEmpty(userIdAllowDeny))
		{
			String[] userids = userIdAllowDeny.split(",");
			for (int i = 0; i < userids.length; i++) {
				if(user.getId()!=Integer.parseInt(userids[i]))
				{
					return SKIP_BODY;
				}
				
			}
		}
		
		//如果用户id包含在允许给改ID显示 信息
		if(StringUtils.isNotEmpty(userIdAllow))
		{
			String par = "0";
			String[] userids = userIdAllow.split(",");
			for (int i = 0; i < userids.length; i++) {
				if(user.getId()==Integer.parseInt(userids[i]))
				{
					par = "1";
				}
			}
			//par==1 则显示
			if("1".equals(par)){
				return EVAL_BODY_INCLUDE;
			}else{
				return SKIP_BODY;
			}
			
		}
		
		
		
		
		
		//如果用户id包含在被禁的id中跳过
		if(StringUtils.isNotEmpty(userIdDeny))
		{
			String[] userids = userIdDeny.split(",");
			for (int i = 0; i < userids.length; i++) {
				if(user.getId()==Integer.parseInt(userids[i]))
				{
					return SKIP_BODY;
				}
				
			}
		}
		//如果角色被禁
		if(StringUtils.isNotEmpty(roleIdDeny))
		{
			String[] roleids = roleIdDeny.split(",");
			for (int i = 0; i < roleids.length; i++) {
				if(user.getRoleId()==Integer.parseInt(roleids[i]))
				{
					return SKIP_BODY;
				}
				
			}
		}
		//如果部门被禁
		if(StringUtils.isNotEmpty(departmentDeny))
		{
			String[] department = departmentDeny.split(",");
			for (int i = 0; i < department.length; i++) {
				if(department[i].equals(user.getDepartmentId()))
				{
					return SKIP_BODY;
				}
				
			}
		}
		
		return EVAL_BODY_INCLUDE;
		//return super.doStartTag();
	}

	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		return super.doAfterBody();
	}

	public String getUserIdAllow() {
		return userIdAllow;
	}

	public void setUserIdAllow(String userIdAllow) {
		this.userIdAllow = userIdAllow;
	}

	public String getUserIdDeny() {
		return userIdDeny;
	}

	public void setUserIdDeny(String userIdDeny) {
		this.userIdDeny = userIdDeny;
	}

	public String getRoleIdDeny() {
		return roleIdDeny;
	}

	public void setRoleIdDeny(String roleIdDeny) {
		this.roleIdDeny = roleIdDeny;
	}

	public String getDepartmentDeny() {
		return departmentDeny;
	}

	public void setDepartmentDeny(String departmentDeny) {
		this.departmentDeny = departmentDeny;
	}

	public String getUserIdAllowDeny() {
		return userIdAllowDeny;
	}

	public void setUserIdAllowDeny(String userIdAllowDeny) {
		this.userIdAllowDeny = userIdAllowDeny;
	}

	
}
