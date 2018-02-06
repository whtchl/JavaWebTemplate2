
package ky.action;

import ky.entity.PmsDepartment;
import ky.service.PmsDepartmentService;
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
* @ClassName: PmsDepartmentAction
* @Description: ???
* @author 生成Action类
* @date 2018-01-12 下午 03:56:01 
*******************************************************
*/
@ParentPackage("tiles-default")
@Namespace("/pmsdepartment")
public class PmsDepartmentAction extends BaseAction<PmsDepartment>{

	@Autowired()
	private PmsDepartmentService PmsDepartmentSer;
	@Action(value = "selectPage", results = {@Result(name = "selectPage", location = "/pages/PmsDepartment/list.jsp") })
	public String seletePage(){

		//接受前台参数，并进行数据查询
		PageView pageView = PmsDepartmentSer.selectPage(this.pageParam("page", "rows","order", "sort",model ));

		//返回数据到界面
		this.returnPageInfo(pageView);

		return "selectPage";
	}

	@Action(value = "select", results = {@Result(name = "select", location = "/pages/PmsDepartment/list.jsp") })
	public String selete(){
		 model.setOemNumber("1");
		List<PmsDepartment> list = PmsDepartmentSer.selectList(model);
		this.jsonArray(list);
		return "select";
	}

	@Action(value = "save", results = {@Result(name = "save", location = "/pages/PmsDepartment/list.jsp") })
	public String save(){
		 model.setOemNumber("1");
		this.returnU_D_S_info(PmsDepartmentSer.save(model));
		return "save";
	}

	@Action(value = "detele", results = {@Result(name = "detele", location = "/pages/PmsDepartment/list.jsp") })
	public String detele() throws UnsupportedEncodingException{

		String idArray=this.getRequest("idArray");//获得前台复选框的值
		this.returnU_D_S_info(PmsDepartmentSer.delete(idArray));
		return "detele";
	}

	@Action(value = "update", results = {@Result(name = "update", location = "/pages/PmsDepartment/list.jsp") })
	public String update(){

		this.returnU_D_S_info(PmsDepartmentSer.update(model));
		return "update";
	}

}

