
package ky.service.Impl;

import ky.entity.TSysRole;
import ky.dao.TSysRoleDao;
import ky.service.TSysRoleService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysRoleServiceImpl
* @Description: ???
* @author 生成service类
* @date 2017-12-21 下午 02:04:24 
*******************************************************
*/
@Service
public class TSysRoleServiceImpl extends BaseServiceImpl implements TSysRoleService{

	@Autowired
	private TSysRoleDao tsysroleDao;

	public PageView selectPage(PageView pageView) {
		return tsysroleDao.getPageView(pageView);
	}

	public List<TSysRole> selectList(TSysRole obj) {
		return tsysroleDao.selectList(obj);
	}

	public int update(TSysRole obj) {
		int param=1;
		int param1=tsysroleDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(TSysRole obj){
		int param=1;
		int param1=tsysroleDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = tsysroleDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=tsysroleDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

	@Override
	public String selectRoleName(TSysRole paramTSysRole) {
		// TODO Auto-generated method stub
		return null;
	}

}

