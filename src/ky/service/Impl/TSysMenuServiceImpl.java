
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

	
	public List<TSysMenu> selectList(String idArray, String menuLevel,
			String parentId) {
		TSysMenu obj = new TSysMenu();
		if ((idArray != null) && (idArray != "")) {
			obj.setMenuId(Integer.valueOf(Integer.parseInt(idArray)));
		}
		if ((menuLevel != null) && (menuLevel != "")) {
			obj.setMenuLevel(Integer.valueOf(Integer.parseInt(menuLevel)));
		}
		if ((parentId != null) && (parentId != "")) {
			obj.setParentid(Integer.valueOf(Integer.parseInt(parentId)));
		}
		List list = this.tsysmenuDao.selectList(obj);
		return list;
	}
	
	public List selectPage(String idArray) {
		List list = new ArrayList();

		TSysMenu obj = (TSysMenu) selectList(idArray, null, null).get(0);
		List twoList = this.tsysmenuDao.menuLevelGroup();
		List threeList = new ArrayList();

		for (int i = 0; i < twoList.size() - 1; i++) {
			List childThreeList = new ArrayList();
			List objList = selectList(null, twoList.get(i).toString(), null);
			for (int z = 0; z < objList.size(); z++) {
				childThreeList.add(objList.get(z));
			}
			threeList.add(childThreeList);
		}
		list.add(obj);
		list.add(twoList);
		list.add(threeList);

		return list;
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
	
	public List allMenu() {
		List list = new ArrayList();

		List oneList = OneTree();
		for (int i = 0; i < oneList.size(); i++) {
			TSysMenu jm = (TSysMenu) oneList.get(i);
			Map oneMap = new HashMap();
			oneMap.put("id", jm.getMenuId());
			oneMap.put("text", jm.getMenuName());
			oneMap.put("children", childMenu(jm.getMenuId() + ""));
			oneMap.put("state", "closed");
			list.add(oneMap);
		}
		return list;
	}
	
	public List<TSysMenu> OneTree() {
		TSysMenu menu = new TSysMenu();
		menu.setMenuLevel(Integer.valueOf(1));
		menu.setPlatformId(Integer.valueOf(1));
		return this.tsysmenuDao.selectList(menu);
	}
	
	@SuppressWarnings("unchecked")
	public List childMenu(String id) {
		List list = new ArrayList();

		TSysMenu twojm = new TSysMenu();
		twojm.setParentid(Integer.valueOf(Integer.parseInt(id)));

		List twoMenu = this.tsysmenuDao.selectList(twojm);
		if (twoMenu.size() > 0) {
			for (int i = 0; i < twoMenu.size(); i++) {
				Map twoMap = new HashMap();
				twoMap.put("id", ((TSysMenu) twoMenu.get(i)).getMenuId());
				twoMap.put("text", ((TSysMenu) twoMenu.get(i)).getMenuName());
				TSysMenu threejm = new TSysMenu();
				threejm.setParentid(((TSysMenu) twoMenu.get(i)).getMenuId());
				List threeMenu = this.tsysmenuDao.selectList(threejm);
				List threeList = new ArrayList();
				if (threeMenu.size() > 0) {
					for (int z = 0; z < threeMenu.size(); z++) {
						Map threeMap = new HashMap();
						threeMap.put("id", ((TSysMenu) threeMenu.get(z))
								.getMenuId());
						threeMap.put("text", ((TSysMenu) threeMenu.get(z))
								.getMenuName());
						threeList.add(threeMap);

						TSysMenu fourjm = new TSysMenu();
						fourjm.setParentid(((TSysMenu) threeMenu.get(z))
								.getMenuId());

						List fourMenu = this.tsysmenuDao.selectList(fourjm);
						List fourList = new ArrayList();
						if (fourMenu.size() > 0) {
							for (int k = 0; k < fourMenu.size(); k++) {
								Map fourMap = new HashMap();
								fourMap.put("id", ((TSysMenu) fourMenu.get(k))
										.getMenuId());
								fourMap.put("text",
										((TSysMenu) fourMenu.get(k))
												.getMenuName());
								fourList.add(fourMap);
							}
						}
						threeMap.put("children", fourList);
					}
				}
				twoMap.put("children", threeList);
				list.add(twoMap);
			}
		}

		return list;
	}
	
	public List selectList1(TSysMenu obj) {
		return tsysmenuDao.selectList1(obj);
	}

	@Override
	public List childMenu2(String id, List<TSysMenu> mlist) {
		// TODO Auto-generated method stub
		return null;
	}
	public List allMenu2(List<TSysMenu> mlist,int pId) {
		List list = new ArrayList();
		TSysMenu m = new TSysMenu();
		m.setPlatformId(pId);
		List<TSysMenu> tumList = tsysmenuDao.selectList(m);
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
		Map<String, List<TSysMenu>> menumap=new HashMap<String, List<TSysMenu>>();
		menumap.put("first", firstlist);
		menumap.put("second", secondlist);
		menumap.put("third", thirdlist);
		menumap.put("fourth", fourthlist);
		for (int i = 0; i < firstlist.size(); i++) {
			TSysMenu jm = (TSysMenu) firstlist.get(i);
			Map oneMap = new HashMap();
			oneMap.put("id", jm.getMenuId());
			oneMap.put("text", jm.getMenuName());
			oneMap.put("state", "closed");
			oneMap.put("children", childMenu1(jm.getMenuId(),menumap, mlist));
			list.add(oneMap);
		}
		return list;
	}
	public List childMenu1(Integer menuId,Map<String, List<TSysMenu>> menumap, List<TSysMenu> mlist) {
		List list = new ArrayList();
		List<TSysMenu> twoMenu = menumap.get("second");
		if (twoMenu.size() > 0) {
			for (int i = 0; i < twoMenu.size(); i++) {
				if(twoMenu.get(i).getParentid().intValue()==menuId.intValue()){
					Map twoMap = new HashMap();
					twoMap.put("id", ((TSysMenu) twoMenu.get(i)).getMenuId());
					twoMap.put("text", ((TSysMenu) twoMenu.get(i)).getMenuName());
					for (int j = 0; j < mlist.size(); j++) {
						if (((TSysMenu) mlist.get(j)).getMenuId().equals(
								((TSysMenu) twoMenu.get(i)).getMenuId())) {
							twoMap.put("checked", "true");
						}
					}
					List<TSysMenu> threeMenu = menumap.get("third");
					List threeList = new ArrayList();
					if (threeMenu.size() > 0) {
						for (int z = 0; z < threeMenu.size(); z++) {
							if(threeMenu.get(z).getParentid().intValue()==twoMenu.get(i).getMenuId()){
								Map threeMap = new HashMap();
								threeMap.put("id", ((TSysMenu) threeMenu.get(z))
										.getMenuId());
								threeMap.put("text", ((TSysMenu) threeMenu.get(z))
										.getMenuName());
								for (int j = 0; j < mlist.size(); j++) {
									if (((TSysMenu) mlist.get(j)).getMenuId().equals(
											((TSysMenu) threeMenu.get(z)).getMenuId())) {
										threeMap.put("checked", "true");
									}
								}
								threeList.add(threeMap);
								
								List<TSysMenu> fourMenu = menumap.get("fourth");
								List fourList = new ArrayList();
								if (fourMenu.size() > 0) {
									for (int k = 0; k < fourMenu.size(); k++) {
										if(fourMenu.get(k).getParentid()==threeMenu.get(z).getMenuId()){
										Map fourMap = new HashMap();
										fourMap.put("id", ((TSysMenu) fourMenu.get(k))
												.getMenuId());
										fourMap.put("text",
												((TSysMenu) fourMenu.get(k))
														.getMenuName());
										for (int j = 0; j < mlist.size(); j++) {
											if (((TSysMenu) mlist.get(j))
													.getMenuId()
													.equals(
															((TSysMenu) fourMenu.get(k))
																	.getMenuId())) {
												fourMap.put("checked", "true");
											}
										}
										fourList.add(fourMap);
									}
									}
								}
								threeMap.put("children", fourList);
							}
						}
					}
					twoMap.put("children", threeList);
					list.add(twoMap);
				}
			}
		}

		return list;
	}
	
	public List<TSysMenu> getMenus(TSysMenu obj) {
		List list = this.tsysmenuDao.selectList(obj);
		return list;
	}

	public List allMenu1(List<TSysMenu> mlist) {
		List list = new ArrayList();

		List oneList = OneTree();
		for (int i = 0; i < oneList.size(); i++) {
			TSysMenu jm = (TSysMenu) oneList.get(i);
			Map oneMap = new HashMap();
			oneMap.put("id", jm.getMenuId());
			oneMap.put("text", jm.getMenuName());
			oneMap.put("state", "closed");
			oneMap.put("children", childMenu2(jm.getMenuId() + "",mlist));
			list.add(oneMap);
		}
		return list;
	}
}

