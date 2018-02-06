
package ky.action;

import ky.entity.PmsPerson;
import ky.service.PmsPersonService;
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
* @ClassName: PmsPersonAction
* @Description: ???
* @author ç”ŸæˆActionç±?
* @date 2018-02-06 ÏÂÎç 02:23:13 
*******************************************************
*/
@ParentPackage("tiles-default")
@Namespace("/pmsperson")
public class PmsPersonAction extends BaseAction<PmsPerson>{

	@Autowired()
	private PmsPersonService PmsPersonSer;
	@Action(value = "selectPage", results = {@Result(name = "selectPage", location = "/pages/PmsPerson/list.jsp") })
	public String seletePage(){

		//接受前台参数，并进行数据查询
		PageView pageView = PmsPersonSer.selectPage(this.pageParam("page", "rows","order", "sort",model ));

		//返回数据到界面
		this.returnPageInfo(pageView);

		return "selectPage";
	}

	@Action(value = "select", results = {@Result(name = "select", location = "/pages/PmsPerson/list.jsp") })
	public String selete(){

		List<PmsPerson> list = PmsPersonSer.selectList(model);
		this.jsonArray(list);
		return "select";
	}

	@Action(value = "save", results = {@Result(name = "save", location = "/pages/PmsPerson/list.jsp") })
	public String save(){

		this.returnU_D_S_info(PmsPersonSer.save(model));
		return "save";
	}

	@Action(value = "detele", results = {@Result(name = "detele", location = "/pages/PmsPerson/list.jsp") })
	public String detele() throws UnsupportedEncodingException{

		String idArray=this.getRequest("idArray");//获得前台复选框的值
		this.returnU_D_S_info(PmsPersonSer.delete(idArray));
		return "detele";
	}

	@Action(value = "update", results = {@Result(name = "update", location = "/pages/PmsPerson/list.jsp") })
	public String update(){

		this.returnU_D_S_info(PmsPersonSer.update(model));
		return "update";
	}

}

