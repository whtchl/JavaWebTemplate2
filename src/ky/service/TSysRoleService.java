
package ky.service;

import ky.entity.TSysRole;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysRoleService
* @Description: ???
* @author 生成service类
* @date 2017-12-21 下午 02:04:24 
*******************************************************
*/
@Service
public interface TSysRoleService{

	public PageView selectPage(PageView pageView) ;
	public List<TSysRole> selectList(TSysRole obj);
	public int save(TSysRole obj);
	public int delete(String idArray);
	public int update(TSysRole obj);
	public abstract String selectRoleName(TSysRole paramTSysRole);

}

