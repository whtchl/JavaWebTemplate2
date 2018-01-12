
package ky.service;

import ky.entity.TSysUserMenu;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysUserMenuService
* @Description: ???????
* @author 生成service类
* @date 2018-01-11 下午 04:43:52 
*******************************************************
*/
@Service
public interface TSysUserMenuService{

	public PageView selectPage(PageView pageView) ;
	public List<TSysUserMenu> selectList(TSysUserMenu obj);
	public int save(TSysUserMenu obj);
	public int delete(String idArray);
	public int update(TSysUserMenu obj);

}

