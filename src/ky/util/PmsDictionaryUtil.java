package ky.util;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ky.entity.PmsDictionary;
import ky.service.PmsDictionaryService;

/**
 * 
 * @author zhaojin
 *
 */
@Component
public class PmsDictionaryUtil {

	@Resource
	private PmsDictionaryService pmsDictionaryService;

	private static PmsDictionaryUtil util;

	@PostConstruct
	public void init() {

		util = this;

		util.pmsDictionaryService = this.pmsDictionaryService;
	}

	/**
	 * 
	 * 根据参数获取参数对象
	 * 
	 * @param paramCode
	 * @return 2015-10-13 zhaojin
	 */
	public static PmsDictionary getPmsDictionary(String type, String key) {
		PmsDictionary param = new PmsDictionary();
		param.setType(type);
		param.setKey(key);
		List result = util.pmsDictionaryService.selectList(param);
		if (result.size() == 1)
			return (PmsDictionary) result.get(0);
		return null;
	}

	/**
	 * 根据参数获取参数值
	 *
	 * @param paramCode
	 * @return 2015-10-13 zhaojin
	 */
	public static String getPmsDictionaryValue(String type, String key) {

		PmsDictionary result = getPmsDictionary(type, key);
		if (result != null)
			return result.getValue();
		else
			return null;
	}
}
