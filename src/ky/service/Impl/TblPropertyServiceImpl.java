
package ky.service.Impl;

import ky.entity.TblProperty;
import ky.dao.TblPropertyDao;
import ky.service.TblPropertyService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TblPropertyServiceImpl
* @Description: null
* @author ÁîüÊàêserviceÁ±?
* @date 2018-02-06 œ¬ŒÁ 03:11:05 
*******************************************************
*/
@Service
public class TblPropertyServiceImpl extends BaseServiceImpl implements TblPropertyService{

	@Autowired
	private TblPropertyDao tblpropertyDao;

	public PageView selectPage(PageView pageView) {
		return tblpropertyDao.getPageView(pageView);
	}

	public List<TblProperty> selectList(TblProperty obj) {
		return tblpropertyDao.selectList(obj);
	}

	public int update(TblProperty obj) {
		int param=1;
		int param1=tblpropertyDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(TblProperty obj){
		int param=1;
		int param1=tblpropertyDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = tblpropertyDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=tblpropertyDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

}

