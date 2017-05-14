package com.laidu.bishe.utils.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过正则表达式获取文本
 * @author Administrator
 *
 */
public class RegularHelper {
	/**
	 * 通过正则表达式获取数据，返回一个String的值
	 * @param content html的文档内容
	 * @param reg 正则表达式
	 * @return
	 */
	public static String getValueByReg(String content, String reg) {
		String value = "";
		if (null != content && !"".equals(content) && null != reg && !"".equals(reg)) {
			Pattern p = Pattern.compile(reg.substring(1), Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(content);
			if (m.find()) {
				if(m.groupCount() >= 1){
					value = m.group(1);
				}else{
					value = m.group();
				}
			}
		}
		return value;
	}

	/**
	 * 通过正则表达式获取数据，返回一个String的值
	 * @param content html的文档内容
	 * @param reg 正则表达式
	 * @return
	 */
	public static String getValueByRegForcw(String content, String reg) {
		String value = "";
		if (null != content && !"".equals(content) && null != reg && !"".equals(reg)) {
			Pattern p = Pattern.compile(reg,Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(content);
			if (m.find()) {
				value = m.group(0);
			}
		}
		return value;
	}
	/**
	 * 通过正则表达式获取数据，返回一个String的值
	 * @param content html的文档内容
	 * @param reg 正则表达式
	 * @return
	 */
	public static List<String> getValuesByReg(String content, String reg) {
		List<String> values = new ArrayList<String>();
		String value = "";
		Pattern p = Pattern.compile(reg.substring(1), Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(content);
		while (m.find()) {
			if(m.groupCount() >= 1){
				value = m.group(1);
			}else{
				value = m.group();
			}
			values.add(value);
		}
		return values;
	}
}
