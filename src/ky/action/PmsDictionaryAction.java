
package ky.action;

import ky.entity.PmsDictionary;
import ky.service.PmsDictionaryService;
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
* @ClassName: PmsDictionaryAction
* @Description: ???
* @author 生成Action类
* @date 2017-12-26 上午 10:03:47 
*******************************************************
*/
@ParentPackage("tiles-default")
@Namespace("/pmsdictionary")
public class PmsDictionaryAction extends BaseAction<PmsDictionary>{

	@Autowired()
	private PmsDictionaryService PmsDictionarySer;
	@Action(value = "selectPage", results = {@Result(name = "selectPage", location = "/pages/PmsDictionary/list.jsp") })
	public String seletePage(){

		//接受前台参数，并进行数据查询
		PageView pageView = PmsDictionarySer.selectPage(this.pageParam("page", "rows","order", "sort",model ));

		//返回数据到界面
		this.returnPageInfo(pageView);

		return "selectPage";
	}

	@Action(value = "select", results = {@Result(name = "select", location = "/pages/PmsDictionary/list.jsp") })
	public String selete(){

		List<PmsDictionary> list = PmsDictionarySer.selectList(model);
		this.jsonArray(list);
		return "select";
	}

	@Action(value = "save", results = {@Result(name = "save", location = "/pages/PmsDictionary/list.jsp") })
	public String save(){

		this.returnU_D_S_info(PmsDictionarySer.save(model));
		return "save";
	}

	@Action(value = "detele", results = {@Result(name = "detele", location = "/pages/PmsDictionary/list.jsp") })
	public String detele() throws UnsupportedEncodingException{

		String idArray=this.getRequest("idArray");//获得前台复选框的值
		this.returnU_D_S_info(PmsDictionarySer.delete(idArray));
		return "detele";
	}

	@Action(value = "update", results = {@Result(name = "update", location = "/pages/PmsDictionary/list.jsp") })
	public String update(){

		this.returnU_D_S_info(PmsDictionarySer.update(model));
		return "update";
	}

}

