package ky.xml;

import java.util.List;
import ky.util.PageView;

public abstract interface BaseMapper<T>
{
  public abstract int save(T paramT);

  public abstract int update(T paramT);

  public abstract int delete(int paramInt);

  public abstract int delete(T paramT);

  public abstract T selectOne(T paramT);

  public abstract List<T> selectList(T paramT);

  public abstract List<T> selectPage(PageView paramPageView);

  public abstract int total(PageView paramPageView);
}

/* Location:           C:\Users\Administrator\Desktop\ky_pms\WEB-INF\classes\
 * Qualified Name:     ky.xml.BaseMapper
 * JD-Core Version:    0.6.0
 */