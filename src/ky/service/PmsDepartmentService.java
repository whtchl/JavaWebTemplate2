
package ky.service;

import ky.entity.PmsDepartment;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: PmsDepartmentService
* @Description: ???
* @author 生成service类
* @date 2018-01-12 下午 03:56:01 
*******************************************************
*/
@Service
public interface PmsDepartmentService{

	public PageView selectPage(PageView pageView) ;
	public List<PmsDepartment> selectList(PmsDepartment obj);
	public int save(PmsDepartment obj);
	public int delete(String idArray);
	public int update(PmsDepartment obj);

}

