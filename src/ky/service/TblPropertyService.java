
package ky.service;

import ky.entity.TblProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TblPropertyService
* @Description: null
* @author ç”Ÿæˆserviceç±?
* @date 2018-02-06 ÏÂÎç 03:11:05 
*******************************************************
*/
@Service
public interface TblPropertyService{

	public PageView selectPage(PageView pageView) ;
	public List<TblProperty> selectList(TblProperty obj);
	public int save(TblProperty obj);
	public int delete(String idArray);
	public int update(TblProperty obj);

}

