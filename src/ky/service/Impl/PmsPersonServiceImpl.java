
package ky.service.Impl;

import ky.entity.PmsPerson;
import ky.dao.PmsPersonDao;
import ky.service.PmsPersonService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: PmsPersonServiceImpl
* @Description: ???
* @author 生成service类
* @date 2018-01-18 下午 03:06:48 
*******************************************************
*/
@Service
public class PmsPersonServiceImpl extends BaseServiceImpl implements PmsPersonService{

	@Autowired
	private PmsPersonDao pmspersonDao;

	public PageView selectPage(PageView pageView) {
		return pmspersonDao.getPageView(pageView);
	}

	public List<PmsPerson> selectList(PmsPerson obj) {
		return pmspersonDao.selectList(obj);
	}

	public int update(PmsPerson obj) {
		int param=1;
		int param1=pmspersonDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(PmsPerson obj){
		int param=1;
		int param1=pmspersonDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = pmspersonDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=pmspersonDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

}

