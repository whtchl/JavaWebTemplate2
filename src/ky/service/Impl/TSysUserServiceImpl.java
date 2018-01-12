
package ky.service.Impl;

import ky.entity.TSysUser;
import ky.dao.TSysUserDao;
import ky.service.TSysUserService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ky.util.Encryption;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysUserServiceImpl
* @Description: ???
* @author 生成service类
* @date 2017-12-21 下午 01:59:42 
*******************************************************
*/
@Service
public class TSysUserServiceImpl extends BaseServiceImpl implements TSysUserService{

	@Autowired
	private TSysUserDao tsysuserDao;

	public PageView selectPage(PageView pageView) {
		return tsysuserDao.getPageView(pageView);
	}

	public List<TSysUser> selectList(TSysUser obj) {
		return tsysuserDao.selectList(obj);
	}

	public int update(TSysUser obj) {
		int param=1;
		int param1=tsysuserDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(TSysUser obj){
		int param=1;
		int param1=tsysuserDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = tsysuserDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=tsysuserDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

	
	public int resetPassword(String id) {
		int param = 0;
		TSysUser tSysUser = new TSysUser();
		tSysUser.setId(Integer.parseInt(id));
		List<TSysUser> list = tsysuserDao.selectList(tSysUser);
		for (int i = 0; i < list.size(); i++) {
			tSysUser = list.get(0);
			tSysUser.setLoginPwd(Encryption.MD5("123456"));
			
			param = this.tsysuserDao.update(tSysUser);
		}
		
		return param;
	}
}

