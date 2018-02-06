package ky.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ChineseToEnglish {

	// 将中文转换为英文
	public static String getEname(String name) {
		HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
		pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		return PinyinHelper.toHanyuPinyinString(name, pyFormat, "");
	}

	// 姓、名的第一个字母需要为大写

	public static String getUpEname(String name) {

		char[] strs = name.toCharArray();
		String newname = null;
		// 名字的长度
		if (strs.length == 2) {
			newname = toUpCase(getEname("" + strs[0])) + "" + toUpCase(getEname("" + strs[1]));
		} else if (strs.length == 3) {
			newname = toUpCase(getEname("" + strs[0])) + "" + toUpCase(getEname("" + strs[1] + strs[2]));
		} else if (strs.length == 4) {
			newname = toUpCase(getEname("" + strs[0] + strs[1])) + "" + toUpCase(getEname("" + strs[2] + strs[3]));
		} else {
			newname = toUpCase(getEname(name));
		}
		return newname;

	}

	// 首字母大写
	private static String toUpCase(String str) {
		StringBuffer newstr = new StringBuffer();
		newstr.append((str.substring(0, 1)).toUpperCase()).append(str.substring(1, str.length()));
		return newstr.toString();

	}

	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 */
	public static String getFirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
				if (temp != null) {
					pybf.append(temp[0].charAt(0));
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}

	public static void main(String[] args) {

		System.out.println(getUpEname("李宇春"));
		System.out.println(getEname("李宇春"));
		System.out.println(getFirstSpell("李宇春"));

	}

}
