
package ky.action;

import ky.entity.TSysUser;
import ky.service.TSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import ky.util.PageView;
import ky.util.SessionListener;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
* ********************************************************
* @ClassName: TSysUserAction
* @Description: ???
* @author 生成Action类
* @date 2017-12-21 下午 01:59:42 
*******************************************************
*/
@ParentPackage("tiles-default")
@Namespace("/tsysuser")
public class TSysUserAction extends BaseAction<TSysUser>{

	@Autowired()
	private TSysUserService TSysUserSer;
	@Action(value = "selectPage", results = {@Result(name = "selectPage", location = "/pages/TSysUser/list.jsp") })
	public String seletePage(){

		//接受前台参数，并进行数据查询
		PageView pageView = TSysUserSer.selectPage(this.pageParam("page", "rows","order", "sort",model ));

		//返回数据到界面
		this.returnPageInfo(pageView);

		return "selectPage";
	}

	@Action(value = "select", results = {@Result(name = "select", location = "/pages/TSysUser/list.jsp") })
	public String selete(){

		List<TSysUser> list = TSysUserSer.selectList(model);
		this.jsonArray(list);
		return "select";
	}

	@Action(value = "save", results = {@Result(name = "save", location = "/pages/TSysUser/list.jsp") })
	public String save(){

		this.returnU_D_S_info(TSysUserSer.save(model));
		return "save";
	}

	@Action(value = "detele", results = {@Result(name = "detele", location = "/pages/TSysUser/list.jsp") })
	public String detele() throws UnsupportedEncodingException{

		String idArray=this.getRequest("idArray");//获得前台复选框的值
		this.returnU_D_S_info(TSysUserSer.delete(idArray));
		return "detele";
	}

	@Action(value = "update", results = {@Result(name = "update", location = "/pages/TSysUser/list.jsp") })
	public String update(){

		this.returnU_D_S_info(TSysUserSer.update(model));
		return "update";
	}

	@Action(value = "resetPassword")
	public void resetPassword() throws UnsupportedEncodingException {
		String id = this.getRequest("id");
		returnU_D_S_info(this.TSysUserSer.resetPassword(id));
	}
	
	@Action(value = "selectUserOnline", results = {
			@Result(name = "selectUserOnline", location = "/pages/TSysUser/userOnlineList.jsp") })
	public String selectUserOnline() {
		Map<String, HttpSession> map = SessionListener.getSessionOnline();
		TSysUser user = (TSysUser) session.getAttribute("user");
		List<Map<String, String>> list = TSysUserSer.selectOnlineUser(map);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userOnline", list);
		m.put("userNum", list.size());
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(JSONObject.fromObject(m));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "selectUserOnline";
	}
}

