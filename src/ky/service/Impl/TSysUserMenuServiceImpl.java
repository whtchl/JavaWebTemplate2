
package ky.service.Impl;

import ky.entity.TSysUserMenu;
import ky.dao.TSysUserMenuDao;
import ky.service.TSysUserMenuService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysUserMenuServiceImpl
* @Description: ???????
* @author 生成service类
* @date 2018-01-11 下午 04:43:52 
*******************************************************
*/
@Service
public class TSysUserMenuServiceImpl extends BaseServiceImpl implements TSysUserMenuService{

	@Autowired
	private TSysUserMenuDao tsysusermenuDao;

	public PageView selectPage(PageView pageView) {
		return tsysusermenuDao.getPageView(pageView);
	}

	public List<TSysUserMenu> selectList(TSysUserMenu obj) {
		return tsysusermenuDao.selectList(obj);
	}

	public int update(TSysUserMenu obj) {
		int param=1;
		int param1=tsysusermenuDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(TSysUserMenu obj){
		int param=1;
		int param1=tsysusermenuDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = tsysusermenuDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=tsysusermenuDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

}

