
package ky.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ky.dao.PmsDepartmentDao;
import ky.dao.TSysRoleDao;
import ky.dao.TSysUserDao;
import ky.entity.PmsDepartment;
import ky.entity.TSysRole;
import ky.entity.TSysUser;
import ky.service.TSysUserService;
import ky.util.DateFormat;
import ky.util.Encryption;
import ky.util.PageView;

/**
* ********************************************************
* @ClassName: TSysUserServiceImpl
* @Description: ???
* @author 生成service类
* @date 2017-12-21 下午 01:59:42 
*******************************************************
*/
@Service
public class TSysUserServiceImpl extends BaseServiceImpl implements TSysUserService{

	@Autowired
	private TSysUserDao tsysuserDao;
	
	@Autowired
	private TSysRoleDao tsysroleDao;
	
	@Autowired
	private PmsDepartmentDao pmsdepartmentDao;

	public PageView selectPage(PageView pageView) {
		return tsysuserDao.getPageView(pageView);
	}

	public List<TSysUser> selectList(TSysUser obj) {
		return tsysuserDao.selectList(obj);
	}

	public int update(TSysUser obj) {
		int param=1;
		int param1=tsysuserDao.update(obj);
		if(param1<1)param=param1;
		return param;
	}

	public int save(TSysUser obj){
		int param=1;
		int param1=tsysuserDao.save(obj);
		if(param1<1) param=param1;
		return param;
	}
	public int delete(String idArray) {
		int param=1;
		String[] id_Array = idArray.split("-");
		if(id_Array.length>1){
			for (int i = 0; i < id_Array.length; i++) {
				int param1 = tsysuserDao.delete(Integer.parseInt(id_Array[i]));
				if(param1<1) param=param1;
			}
		}else{
			int param1=tsysuserDao.delete(Integer.parseInt(idArray));
			if(param1<1) param=param1;
		}
		return param;
	}

	
	public int resetPassword(String id) {
		int param = 0;
		TSysUser tSysUser = new TSysUser();
		tSysUser.setId(Integer.parseInt(id));
		List<TSysUser> list = tsysuserDao.selectList(tSysUser);
		for (int i = 0; i < list.size(); i++) {
			tSysUser = list.get(0);
			tSysUser.setLoginPwd(Encryption.MD5("123456"));
			
			param = this.tsysuserDao.update(tSysUser);
		}
		
		return param;
	}
	
	//查询在线用户的信息
	public List<Map<String, String>> selectOnlineUser(Map<String, HttpSession> map) {
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()){
				HttpSession se = map.get(it.next());
				 Object obj = se.getAttribute("user");
				if(obj==null){
					continue;//当用户未登录时，跳过
				}
				TSysUser user = (TSysUser)obj;
				Map<String, String> m=new HashMap<String, String>();
				m.put("loginName", user.getLoginName());
				m.put("trueName", user.getTrueName());
				TSysRole role=new TSysRole();
				role.setRoleId(user.getRoleId());
				List<TSysRole> roleList = tsysroleDao.selectList(role);
				if(roleList.size()==1){
					role=roleList.get(0);
				}
				String roleName=role.getRoleName();
				m.put("roleName", roleName);
				if(user.getDepartmentId()!=null){
					PmsDepartment dep=new PmsDepartment();
					dep.setDepartmentNum(user.getDepartmentId());
					List<PmsDepartment> depList = pmsdepartmentDao.selectList(dep);
					if(depList.size()==1){
						dep=depList.get(0);
					}
					m.put("depName", dep.getDepartmentName());
					m.put("agentName", "");//当有部门id存在时，必定属于总后台或总，所属代理商为空
					if(dep.getOemNumber().equals("1")){
						m.put("oemName", "");
					}else{
						
					}
				}else {
					m.put("depName", "");
					if(roleName.equals("销售员")){
						
					}else if(roleName.equals("一级代理")||roleName.equals("二级代理")||roleName.equals("三级代理")){
						
					}
					
				}
				m.put("loginTime", DateFormat.getString(new Date(se.getCreationTime())));
				list.add(m);
			}
		return list;
	}
}

