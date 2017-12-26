package ky.dao.Impl;

import java.io.PrintStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.Resource;
import ky.dao.BaseDao;
import ky.util.PageView;
import org.mybatis.spring.SqlSessionTemplate;

public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource(name = "sqlSessionTemplate")
	protected SqlSessionTemplate session;
	private final String path = "ky.xml.";
	protected Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		this.clazz = ((Class) pt.getActualTypeArguments()[0]);
	}

	// //获取Mapper的路径
	public String getPath(Class<? extends Object> class1, String methodType) {
		return path + class1.getSimpleName() + "Mapper." + methodType;
	}

	public String getPath(String methodType) {
		return "ky.xml." + this.clazz.getSimpleName() + "Mapper." + methodType;
	}

	public int save(T t) {
		return this.session.insert(getPath("save"), t);
	}

	public int delete(int id) {
		return this.session.delete(getPath("delete"), Integer.valueOf(id));
	}

	public int update(T t) {
		return this.session.update(getPath("update"), t);
	}

	public T selectOne(T t) {
		return this.session.selectOne(getPath("selectOne"), t);
	}

	public List<T> selectList(T t) {
		return this.session.selectList(getPath("selectList"), t);
	}

	public PageView getPageView(PageView pageView) {
		return new PageView(Integer.parseInt(this.session.selectList(getPath("total"), pageView).get(0).toString()),
				this.session.selectList(getPath("selectPage"), pageView));
	}

	public List<T> selectListForExp(T obj) {
		return this.session.selectList(getPath("selectListForExp"), obj);
	}

}
