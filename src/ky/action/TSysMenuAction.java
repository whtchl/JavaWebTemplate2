
package ky.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ky.entity.TSysMenu;
import ky.entity.TSysUser;
import ky.entity.TSysUserMenu;
import ky.service.TSysMenuService;
import ky.service.TSysUserMenuService;
import ky.service.TSysUserService;

/**
* ********************************************************
* @ClassName: TSysMenuAction
* @Description: ???
* @author 生成Action类
* @date 2017-12-21 下午 02:03:26 
*******************************************************
*/
@ParentPackage("tiles-default")
@Namespace("/tsysmenu")
public class TSysMenuAction extends BaseAction<TSysMenu>{

	@Autowired
	@Qualifier("TSysMenuSerForFilter")
	private TSysMenuService TSysMenuSer;

	@Autowired
	private TSysUserMenuService tsysusermenuSer;
	
	@Autowired
	private TSysUserService tsysUserService;
	
	@Action(value = "select", results = {@Result(name = "select", location = "/pages/TSysMenu/list.jsp") })///pages/TSysMenu/list.jsp
	public String select(){

		//List<TSysMenu> list = TSysMenuSer.selectList(model);
		//this.jsonArray(list);
		System.out.println("test");
		String idArray = getParameter("idArray");
		String parentId = getParameter("parentId");
		System.out.println("idArray:"+idArray+" parentId:"+parentId);
		List list = this.TSysMenuSer.selectList(idArray, null, parentId);
		jsonArray(list);
		return "select";
	}

	@Action(value = "save", results = {@Result(name = "save", location = "/pages/TSysMenu/list.jsp") })
	public String save(){

		this.returnU_D_S_info(TSysMenuSer.save(model));
		return "save";
	}

	@Action(value = "detele", results = {@Result(name = "detele", location = "/pages/TSysMenu/list.jsp") })
	public String detele() throws UnsupportedEncodingException{

		String idArray=this.getRequest("idArray");//获得前台复选框的值
		this.returnU_D_S_info(TSysMenuSer.delete(idArray));
		return "detele";
	}

	@Action(value = "update", results = {@Result(name = "update", location = "/pages/TSysMenu/list.jsp") })
	public String update(){

		this.returnU_D_S_info(TSysMenuSer.update(model));
		return "update";
	}
	
	@Action(value = "allMenu", results = { @org.apache.struts2.convention.annotation.Result(name = "allMenu", location = "/pages/TSysMenu/list.jsp") })
	public String allMenu() {
		List list = this.TSysMenuSer.allMenu();
		jsonArray(list);

		return "allMenu";
	}
	
	@Action(value = "selectPage", results = { @org.apache.struts2.convention.annotation.Result(name = "selectPage", location = "/pages/TSysMenu/list.jsp") })
	public String seletePage() {
		String idArray = getParameter("idArray");
		jsonArray(this.TSysMenuSer.selectPage(idArray));

		return "selectPage";
	}
	@Action(value = "allMenuById", results = { @org.apache.struts2.convention.annotation.Result(name = "allMenuById", location = "/pages/TSysUser/edit.jsp") })
	public String allMenuById() {
		TSysUserMenu usermenu = new TSysUserMenu();
		List usermenuList = null;
		List mlist = new ArrayList();
		String userId = getParameter("userId");
		usermenu.setUserId(Integer.valueOf(Integer.parseInt(userId)));
		usermenuList = this.tsysusermenuSer.selectList(usermenu);
		TSysUser user = new TSysUser();
		user.setId(Integer.valueOf(Integer.parseInt(userId)));
		List users = this.tsysUserService.selectList(user);
		int roleId = ((TSysUser) users.get(0)).getRoleId().intValue();

		if (roleId == 2 || roleId == 3 || roleId == 61 || roleId == 62
				|| roleId == 63 || roleId == 64) {
			TSysMenu tmenu = new TSysMenu();
			tmenu.setUserId(userId);
			tmenu.setPlatformId(1);
			List<TSysMenu> a = this.TSysMenuSer.selectList1(tmenu);
			List list = this.TSysMenuSer.allMenu2(a,1);
			jsonArray(list);
			return "allMenuById";
		}

		TSysUser u = new TSysUser();
		u.setId(Integer.parseInt(userId));
		List<TSysUser> us = tsysUserService.selectList(u);
		String loginname = us.get(0).getLoginName();
		if (roleId == 21) {
			mlist = new ArrayList();
			TSysMenu tmenu = new TSysMenu();
			int pId=1;
			if(loginname.length() > 4){
				tmenu.setPlatformId(1);
				pId=1;
			}
			tmenu.setUserId(userId);
			List list = this.TSysMenuSer.selectList1(tmenu);
			if (list.size() != 0) {
				mlist.addAll(list);
			}
			List list1 = this.TSysMenuSer.allMenu2(mlist,pId);
			jsonArray(list1);
			return "allMenuById";
		}

		mlist = new ArrayList();
		for (int i = 0; i < usermenuList.size(); i++) {
			int mid = ((TSysUserMenu) usermenuList.get(i)).getMenuId();
			TSysMenu tmenu = new TSysMenu();
			tmenu.setMenuId(Integer.valueOf(mid));
			List list = this.TSysMenuSer.getMenus(tmenu);
			if (list.size() != 0) {
				mlist.addAll(list);
			}
		}
		List list = this.TSysMenuSer.allMenu1(mlist);
		jsonArray(list);
		return "allMenuById";
	}
}

