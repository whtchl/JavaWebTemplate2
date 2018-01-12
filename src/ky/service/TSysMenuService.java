
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

	public abstract List selectPage(String paramString);
	public List<TSysMenu> selectList(TSysMenu obj);
	public int save(TSysMenu obj);
	public int delete(String idArray);
	public int update(TSysMenu obj);
	public abstract List getchildMenu(int paramInt);
	public abstract List<TSysMenu> selectList(String paramString1, String paramString2, String paramString3);
	public abstract List allMenu();
	  public abstract List childMenu2(String id,List<TSysMenu> mlist);
	  public List selectList1(TSysMenu paramTSysMenu);

	  public abstract List<TSysMenu> getMenus(TSysMenu paramTSysMenu);

	  public abstract List allMenu1(List<TSysMenu> paramList);

	  public abstract List allMenu2(List<TSysMenu> paramList,int pId);
}

