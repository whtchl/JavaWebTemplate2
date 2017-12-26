
package ky.service;

import ky.entity.TSysMenu;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysMenuService
* @Description: ???
* @author 生成service类
* @date 2017-12-21 下午 02:03:26 
*******************************************************
*/
@Service
public interface TSysMenuService{

	public PageView selectPage(PageView pageView) ;
	public List<TSysMenu> selectList(TSysMenu obj);
	public int save(TSysMenu obj);
	public int delete(String idArray);
	public int update(TSysMenu obj);
	 public abstract List getchildMenu(int paramInt);
}

