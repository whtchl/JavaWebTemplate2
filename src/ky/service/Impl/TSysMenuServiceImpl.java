
package ky.service.Impl;

import ky.entity.TSysMenu;
import ky.dao.TSysMenuDao;
import ky.service.TSysMenuService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysMenuServiceImpl
* @Description: ???
* @author 生成service类
* @date 2017-12-21 下午 02:03:26 
*******************************************************
*/
@Service
public class TSysMenuServiceImpl extends BaseServiceImpl implements TSysMenuService{

	@Autowired
	private TSysMenuDao tsysmenuDao;

	public PageView selectPage(PageView pageView) {
		return tsysmenuDao.getPageView(pageView);
	}

	public List<TSysMenu> selectList(TSysMenu obj) {
		return tsysmenuDao.selectList(obj);
	}

	public int update(TSysMenu obj) {
		int param=1;
		int param1=tsysmenuDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(TSysMenu obj){
		int param=1;
		int param1=tsysmenuDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = tsysmenuDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=tsysmenuDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

	@Override
	public List getchildMenu(int uid) {
		TSysMenu m = new TSysMenu();
		m.setUserId(uid+"");
		List<TSysMenu> tumList = tsysmenuDao.selectList1(m);
		List<TSysMenu> firstlist=new ArrayList<TSysMenu>();
		List<TSysMenu> secondlist=new ArrayList<TSysMenu>();
		List<TSysMenu> thirdlist=new ArrayList<TSysMenu>();
		List<TSysMenu> fourthlist=new ArrayList<TSysMenu>();
		for (int i = 0; i < tumList.size(); i++) {
			TSysMenu sm=tumList.get(i);
			int level=sm.getMenuLevel();
			if(level==1){
				firstlist.add(sm);
			}else if(level==2){
				secondlist.add(sm);
			}else if(level==3){
				thirdlist.add(sm);
			}else if(level==4){
				fourthlist.add(sm);
			}
		}
        try{
			
			if(fourthlist.size()>0){
				for(int i=0;i<thirdlist.size();i++){
					int id=thirdlist.get(i).getMenuId();
					List list=new ArrayList();
					for(int k=0;k<fourthlist.size();k++){
						int parentId=fourthlist.get(k).getParentid();
						if(parentId==id){
							Map map=new HashMap();
							map.put("id", fourthlist.get(k).getMenuId().toString());
							map.put("text", fourthlist.get(k).getMenuName());
							if(fourthlist.get(k).getChildren()!=null){
								map.put("children", fourthlist.get(k).getChildren());
							}
							list.add(map);
						}
					}
					thirdlist.get(i).setChildren(list);
				}
			}
			
			if(thirdlist.size()>0){
				for(int i=0;i<secondlist.size();i++){
					int id=secondlist.get(i).getMenuId();
					List list=new ArrayList();
					for(int k=0;k<thirdlist.size();k++){
						int parentId=thirdlist.get(k).getParentid();
						if(parentId==id){
							Map map=new HashMap();
							map.put("id", thirdlist.get(k).getMenuId().toString());
							map.put("text", thirdlist.get(k).getMenuName());
							if(thirdlist.get(k).getChildren()!=null){
								map.put("children", thirdlist.get(k).getChildren());
							}
							list.add(map);
						}
					}
					secondlist.get(i).setChildren(list);
				}
			}
			
			if(secondlist.size()>0){
				for(int i=0;i<firstlist.size();i++){
					int id=firstlist.get(i).getMenuId();
					List list=new ArrayList();
					for(int k=0;k<secondlist.size();k++){
						int parentId=secondlist.get(k).getParentid();
						if(parentId==id){
							Map map=new HashMap();
							map.put("id", secondlist.get(k).getMenuId().toString());
							map.put("text", secondlist.get(k).getMenuName());
							if(secondlist.get(k).getChildren()!=null){
								map.put("children", secondlist.get(k).getChildren());
							}
							list.add(map);
						}
					}
					firstlist.get(i).setChildren(list);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return firstlist;
	}
	

	

}

