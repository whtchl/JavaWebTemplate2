package ky.dao;

import java.util.List;

import ky.util.PageView;

public abstract interface BaseDao<T>
{
  public abstract int save(T paramT);

  public abstract int delete(int paramInt);

  public abstract int update(T paramT);

  public abstract T selectOne(T paramT);

  public abstract List<T> selectList(T paramT);
  
  public abstract PageView getPageView(PageView paramPageView);
  
  public List<T> selectListForExp(T paramT);
  
}

/* Location:           C:\Users\Administrator\Desktop\ky_pms\WEB-INF\classes\
 * Qualified Name:     ky.dao.BaseDao
 * JD-Core Version:    0.6.0
 */