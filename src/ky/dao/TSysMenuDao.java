
package ky.dao;

import ky.entity.TSysMenu;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysMenuDao
* @Description: ???
* @author 用wzl写的自动生成dao包
* @date 2017-12-21 下午 02:03:26 
*******************************************************
*/
@Repository
public interface TSysMenuDao extends BaseDao<TSysMenu>{
	  public abstract List<Integer> menuLevelGroup();
	 public List<TSysMenu> selectList1(TSysMenu tsm);

}

