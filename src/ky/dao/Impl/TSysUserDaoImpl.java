
package ky.dao.Impl;

import ky.entity.TSysUser;
import ky.dao.TSysUserDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysUserDaoImpl
* @Description: ???
* @author 用wzl写的自动生成daoImpl包
* @date 2017-12-21 下午 01:59:42 
*******************************************************
*/
@Repository
	public class TSysUserDaoImpl extends BaseDaoImpl<TSysUser> implements TSysUserDao{

	@Override
	public int deletep(String id) {
		return session.delete(getPath("deletep"),id);
	}

	@Override
	public int update1(TSysUser obj) {
		return session.update(getPath("update1"),obj);
	}

	@Override
	public int update2(TSysUser user) {
		return session.update(getPath("update2"),user);
	}
}

