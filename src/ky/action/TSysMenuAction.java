
package ky.action;

import ky.entity.TSysMenu;
import ky.service.TSysMenuService;
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
	private TSysMenuService TSysMenuSer;
	@Action(value = "selectPage", results = {@Result(name = "selectPage", location = "/pages/TSysMenu/list.jsp") })
	public String seletePage(){

		//接受前台参数，并进行数据查询
		PageView pageView = TSysMenuSer.selectPage(this.pageParam("page", "rows","order", "sort",model ));

		//返回数据到界面
		this.returnPageInfo(pageView);

		return "selectPage";
	}

	@Action(value = "select", results = {@Result(name = "select", location = "/index.jsp") })///pages/TSysMenu/list.jsp
	public String select(){

		//List<TSysMenu> list = TSysMenuSer.selectList(model);
		//this.jsonArray(list);
		System.out.println("test");
		String idArray = getParameter("idArray");
		String parentId = getParameter("parentId");
		System.out.println("idArray:"+idArray+" parentId:"+parentId);
		/*List list = this.TSysMenuSer.selectList(idArray, null, parentId);
		jsonArray(list);*/
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

}

