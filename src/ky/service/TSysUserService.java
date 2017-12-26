
package ky.service;

import ky.entity.TSysUser;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysUserService
* @Description: ???
* @author 生成service类
* @date 2017-12-21 下午 01:59:42 
*******************************************************
*/
@Service
public interface TSysUserService{

	public PageView selectPage(PageView pageView) ;
	public List<TSysUser> selectList(TSysUser obj);
	public int save(TSysUser obj);
	public int delete(String idArray);
	public int update(TSysUser obj);

}

