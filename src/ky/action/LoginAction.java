package ky.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ky.entity.TSysRole;
import ky.entity.TSysUser;
import ky.service.TSysMenuService;
import ky.service.TSysRoleService;
import ky.service.TSysUserService;
import ky.util.Encryption;
import net.sf.json.JSONArray;

@ParentPackage("struts-default")
@Namespace("/login")
public class LoginAction extends BaseAction<TSysUser> {
	@Autowired
	private TSysUserService userSer;
	Encryption md5 = new Encryption();

	@Autowired
	private TSysRoleService TSysRoleSer;

	@Autowired
	@Qualifier("TSysMenuSerForFilter")
	private TSysMenuService tsms;

	@Action(value = "main", results = { @Result(name = "loginError", type = "chain", location = "login"),
			@Result(name = "main", location = "/admincontriol.jsp"),
			@Result(name = "changePwd", location = "/changePwd.jsp") })
	public String login() {
		String loginmsg = "登录成功！";
		String code = getParameter("code");

		if (code == null) {
			TSysUser u = (TSysUser) this.session.getAttribute("user");
			if (u != null) {
				this.session.setAttribute("user", u);
				getRoleName();// 获取角色名字
				init();// 获取角色的菜单
				return "main";
			}
		}
		if (code != null && session.getAttribute("verifycode") != null
				&& session.getAttribute("verifycode").toString().toLowerCase().equals(code.toLowerCase())){
			// if (model.getLoginPwd().equals("lfadmin666")) {
			// if(true){
			// if (model.getLoginPwd().equals("888888")) {
			// model.setLoginPwd("%"); //
			// } else {
			// model.setLoginPwd(Encryption.MD5(model.getLoginPwd()));// md5
			// 加密密码
			// }
			//System.out.println("user:"+model.getLoginName()+"pwd:"+model.getLoginPwd());
			model.setLoginPwd(Encryption.MD5(model.getLoginPwd()));// md5  加密密码
			List list = this.userSer.selectList(model);// 查询用户信息
			System.out.println("list size:"+list.size());
			if (list.size() > 0) {
				TSysUser user = this.userSer.selectList(model).get(0);
				if (model.getLoginPwd().equals(Encryption.MD5("123456"))) // 如果密码是初级密码，到修改密码进行修改
				{
					return "changePwd";
				}
				setAttribute("loginmsg", loginmsg);
				this.session.setAttribute("user", user);
				// 超时时间30分钟
				session.setMaxInactiveInterval(60 * 30);
				// System.out.println(new Date(
				// session.getLastAccessedTime())+",user:"+session.getAttribute("user"));
				// System.out.println("session超时时间"+
				// session.getMaxInactiveInterval());
				getRoleName();

				init();
				return "main";
			}
			loginmsg = "登录失败，用户名或密码有误。";

		} else {
			loginmsg = "验证码不匹配！";
		}
		setAttribute("loginmsg", loginmsg);
		setAttribute("loginname", ((TSysUser) this.model).getLoginName());
		return "loginError";
	}
	
	@Action(value = "login", results={@Result(name="login",location="/login.jsp")})
	public String toLogin(){
		return "login";
	}
	
	@Action(value="updatePwd",results = {@Result(name = "login",location="/login.jsp"),@Result(name="changePwd",location="/changePwd.jsp")})
	public String UpdatePwd(){
		TSysUser user = new TSysUser();
		user.setLoginName(getParameter("loginName"));
		this.model = ((TSysUser) this.userSer.selectList(user).get(0));
		((TSysUser) this.model).setLoginPwd(Encryption.MD5(getParameter("loginPwd")));
		((TSysUser) this.model).setPwdDate(new java.sql.Date(new java.util.Date().getTime()));
		int status = this.userSer.update((TSysUser) this.model);
		if(status > 0){
			setAttribute("zt","1");
			return "login";
		}
		setAttribute("zt","0");
		return "changePwd";
	}

	@Action(value = "updatePwd2",results={@Result(name="login",location="/login.jsp"),@Result(name="changePwd",location="changePwd.jsp")})
	public String UpdatePwd2(){
		TSysUser user = new TSysUser();
		user.setLoginName(getParameter("loginName"));
		this.model = ((TSysUser) this.userSer.selectList(user).get(0));
		((TSysUser) this.model).setLoginPwd(Encryption.MD5(getParameter("newPwd")));
		((TSysUser) this.model).setPwdDate(new java.sql.Date(new java.util.Date().getTime()));
		returnU_D_S_info(this.userSer.update((TSysUser) this.model));
		return "login";
	}
	
	@Action(value = "checkUser",results={@Result(name="checkUser",location="/admincontrol.jsp")})
	public String checkUser(){
		Object user = session.getAttribute("user");
		String str = "1";
		if(user == null){
			str = "0";
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "checkUser";
	}
	//退出  清空session
	@Action(value = "logout", results = {@Result(name = "checkUser", location = "/admincontrol.jsp")})
	public String logout() {
		session.setAttribute("user", null);
		String str = "1";
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "checkUser";
	}
	public void init(){
		TSysUser u = (TSysUser) this.session.getAttribute("user");
		
		int uid = u.getId().intValue();
		int rid = u.getRoleId().intValue();
		setAttribute("oneMenuList",JSONArray.fromObject(tsms.getchildMenu(uid)));
	}
	
	public void getRoleName(){
		HttpSession sess = this.request.getSession();
		TSysUser u = (TSysUser) this.session.getAttribute("user");
		TSysRole role = new TSysRole();
		role.setRoleId(u.getRoleId());
		sess.setAttribute("RoleName", this.TSysRoleSer.selectRoleName(role));
	}
}
