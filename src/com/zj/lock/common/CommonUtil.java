package com.zj.lock.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 * 
 * @author 周健
 * 
 */
public class CommonUtil {

	/**
	 * 校验邮箱格式
	 * @param address
	 * @return
	 */
	public static boolean isEmail(String address) {
		String regEx = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
				+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
				+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
				+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
				+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
				+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
		Matcher matcherObj = Pattern.compile(regEx).matcher(address);
		if (matcherObj.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
