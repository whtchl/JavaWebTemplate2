
package ky.service;

import ky.entity.PmsPerson;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: PmsPersonService
* @Description: ???
* @author 生成service类
* @date 2018-01-18 下午 03:06:48 
*******************************************************
*/
@Service
public interface PmsPersonService{

	public PageView selectPage(PageView pageView) ;
	public List<PmsPerson> selectList(PmsPerson obj);
	public int save(PmsPerson obj);
	public int delete(String idArray);
	public int update(PmsPerson obj);

}

