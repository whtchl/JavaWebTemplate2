 package ky.entity;

import java.util.List;
 
 public class TSysMenu
 {
   private Integer menuId;
   private Integer platformId;
   private String menuName;
   private Integer menuLevel;
   private Integer parentid;
   private String menuHref;
   private String userId;
   private String roleId;
   private List children;
 
   public Integer getMenuId()
   {
     return this.menuId;
   }
 
   public void setMenuId(Integer menuId) {
     this.menuId = menuId;
   }
 
   public Integer getPlatformId() {
     return this.platformId;
   }
 
   public void setPlatformId(Integer platformId) {
     this.platformId = platformId;
   }
 
   public String getMenuName() {
     return this.menuName;
   }
 
   public void setMenuName(String menuName) {
     this.menuName = menuName;
   }
 
   public Integer getMenuLevel() {
     return this.menuLevel;
   }
 
   public void setMenuLevel(Integer menuLevel) {
     this.menuLevel = menuLevel;
   }
 
   public Integer getParentid() {
     return this.parentid;
   }
 
   public void setParentid(Integer parentid) {
     this.parentid = parentid;
   }
 
   public String getMenuHref() {
     return this.menuHref;
   }
 
   public void setMenuHref(String menuHref) {
     this.menuHref = menuHref;
   }

public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserId() {
	return userId;
}

public void setChildren(List children) {
	this.children = children;
}

public List getChildren() {
	return children;
}

public void setRoleId(String roleId) {
	this.roleId = roleId;
}

public String getRoleId() {
	return roleId;
}
 }

/* Location:           C:\Users\Administrator\Desktop\ky_pms\WEB-INF\classes\
 * Qualified Name:     ky.entity.TSysMenu
 * JD-Core Version:    0.6.0
 */