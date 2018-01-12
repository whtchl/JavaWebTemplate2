
package ky.service.Impl;

import ky.entity.PmsDepartment;
import ky.dao.PmsDepartmentDao;
import ky.service.PmsDepartmentService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: PmsDepartmentServiceImpl
* @Description: ???
* @author 生成service类
* @date 2018-01-12 下午 03:56:01 
*******************************************************
*/
@Service
public class PmsDepartmentServiceImpl extends BaseServiceImpl implements PmsDepartmentService{

	@Autowired
	private PmsDepartmentDao pmsdepartmentDao;

	public PageView selectPage(PageView pageView) {
		return pmsdepartmentDao.getPageView(pageView);
	}

	public List<PmsDepartment> selectList(PmsDepartment obj) {
		return pmsdepartmentDao.selectList(obj);
	}

	public int update(PmsDepartment obj) {
		int param=1;
		int param1=pmsdepartmentDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(PmsDepartment obj){
		int param=1;
		int param1=pmsdepartmentDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = pmsdepartmentDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=pmsdepartmentDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

}

