package com.nutzside.common.web.ajax;

import org.nutz.lang.util.NutMap;

public class DwzAjax {
	
	public static DwzAjaxReturn ok() {
		DwzAjaxReturn re = new DwzAjaxReturn();
		re.statusCode = 200;
		return re;
	}

	public static DwzAjaxReturn fail() {
		DwzAjaxReturn re = new DwzAjaxReturn();
		re.statusCode = 300;
		return re;
	}

	public static DwzAjaxReturn expired() {
		DwzAjaxReturn re = new DwzAjaxReturn();
		re.statusCode = 300;
		re.message = "ajax.expired";
		return re;
	}

	/**
	 * @return 获得一个map，用来存放返回的结果。
	 */
	public static NutMap one() {
		return new NutMap();
	}
}
