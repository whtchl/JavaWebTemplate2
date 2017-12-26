
package ky.service.Impl;

import ky.entity.TSysMenu;
import ky.dao.TSysMenuDao;
import ky.service.TSysMenuService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysMenuServiceImpl
* @Description: ???
* @author 生成service类
* @date 2017-12-21 下午 02:03:26 
*******************************************************
*/
@Service
public class TSysMenuServiceImpl extends BaseServiceImpl implements TSysMenuService{

	@Autowired
	private TSysMenuDao tsysmenuDao;

	public PageView selectPage(PageView pageView) {
		return tsysmenuDao.getPageView(pageView);
	}

	public List<TSysMenu> selectList(TSysMenu obj) {
		return tsysmenuDao.selectList(obj);
	}

	public int update(TSysMenu obj) {
		int param=1;
		int param1=tsysmenuDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(TSysMenu obj){
		int param=1;
		int param1=tsysmenuDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = tsysmenuDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=tsysmenuDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

	@Override
	public List getchildMenu(int paramInt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

