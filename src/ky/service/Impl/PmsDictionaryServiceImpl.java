
package ky.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ky.dao.PmsDictionaryDao;
import ky.entity.PmsDictionary;
import ky.service.PmsDictionaryService;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: PmsDictionaryServiceImpl
* @Description: ???
* @author 生成service类
* @date 2017-12-26 上午 10:03:47 
*******************************************************
*/
@Service
public class PmsDictionaryServiceImpl extends BaseServiceImpl implements PmsDictionaryService{

	@Autowired
	private PmsDictionaryDao pmsdictionaryDao;

	public PageView selectPage(PageView pageView) {
		return pmsdictionaryDao.getPageView(pageView);
	}

	public List<PmsDictionary> selectList(PmsDictionary obj) {
		return pmsdictionaryDao.selectList(obj);
	}

	public int update(PmsDictionary obj) {
		int param=1;
		int param1=pmsdictionaryDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(PmsDictionary obj){
		int param=1;
		int param1=pmsdictionaryDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = pmsdictionaryDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=pmsdictionaryDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

}

