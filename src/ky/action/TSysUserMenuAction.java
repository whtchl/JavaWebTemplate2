
package ky.action;

import ky.entity.TSysUserMenu;
import ky.service.TSysUserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;
import java.util.Map;
import ky.util.PageView;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import java.io.UnsupportedEncodingException;

/**
* ********************************************************
* @ClassName: TSysUserMenuAction
* @Description: ???????
* @author 生成Action类
* @date 2018-01-11 下午 04:43:52 
*******************************************************
*/
@ParentPackage("tiles-default")
@Namespace("/tsysusermenu")
public class TSysUserMenuAction extends BaseAction<TSysUserMenu>{

	@Autowired()
	private TSysUserMenuService TSysUserMenuSer;
	@Action(value = "selectPage", results = {@Result(name = "selectPage", location = "/pages/TSysUserMenu/list.jsp") })
	public String seletePage(){

		//接受前台参数，并进行数据查询
		PageView pageView = TSysUserMenuSer.selectPage(this.pageParam("page", "rows","order", "sort",model ));

		//返回数据到界面
		this.returnPageInfo(pageView);

		return "selectPage";
	}

	@Action(value = "select", results = {@Result(name = "select", location = "/pages/TSysUserMenu/list.jsp") })
	public String selete(){

		List<TSysUserMenu> list = TSysUserMenuSer.selectList(model);
		this.jsonArray(list);
		return "select";
	}

	@Action(value = "save", results = {@Result(name = "save", location = "/pages/TSysUserMenu/list.jsp") })
	public String save(){

		this.returnU_D_S_info(TSysUserMenuSer.save(model));
		return "save";
	}

	@Action(value = "detele", results = {@Result(name = "detele", location = "/pages/TSysUserMenu/list.jsp") })
	public String detele() throws UnsupportedEncodingException{

		String idArray=this.getRequest("idArray");//获得前台复选框的值
		this.returnU_D_S_info(TSysUserMenuSer.delete(idArray));
		return "detele";
	}

	@Action(value = "update", results = {@Result(name = "update", location = "/pages/TSysUserMenu/list.jsp") })
	public String update(){

		this.returnU_D_S_info(TSysUserMenuSer.update(model));
		return "update";
	}

}

