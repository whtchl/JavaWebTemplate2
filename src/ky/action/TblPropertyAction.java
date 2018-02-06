
package ky.action;

import ky.entity.TblProperty;
import ky.service.TblPropertyService;
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
* @ClassName: TblPropertyAction
* @Description: null
* @author 生成Action�?
* @date 2018-02-06 ���� 03:11:05 
*******************************************************
*/
@ParentPackage("tiles-default")
@Namespace("/tblproperty")
public class TblPropertyAction extends BaseAction<TblProperty>{

	@Autowired()
	private TblPropertyService TblPropertySer;
	@Action(value = "selectPage", results = {@Result(name = "selectPage", location = "/pages/TblProperty/list.jsp") })
	public String seletePage(){

		//接受前台参数，并进行数据查询
		PageView pageView = TblPropertySer.selectPage(this.pageParam("page", "rows","order", "sort",model ));

		//返回数据到界�?
		this.returnPageInfo(pageView);

		return "selectPage";
	}

	@Action(value = "select", results = {@Result(name = "select", location = "/pages/TblProperty/list.jsp") })
	public String selete(){

		List<TblProperty> list = TblPropertySer.selectList(model);
		this.jsonArray(list);
		return "select";
	}

	@Action(value = "save", results = {@Result(name = "save", location = "/pages/TblProperty/list.jsp") })
	public String save(){

		this.returnU_D_S_info(TblPropertySer.save(model));
		return "save";
	}

	@Action(value = "detele", results = {@Result(name = "detele", location = "/pages/TblProperty/list.jsp") })
	public String detele() throws UnsupportedEncodingException{

		String idArray=this.getRequest("idArray");//获得前台复�?�框的�??
		this.returnU_D_S_info(TblPropertySer.delete(idArray));
		return "detele";
	}

	@Action(value = "update", results = {@Result(name = "update", location = "/pages/TblProperty/list.jsp") })
	public String update(){

		this.returnU_D_S_info(TblPropertySer.update(model));
		return "update";
	}

}

