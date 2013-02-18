package com.nutzside.common.util;

import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

/**
 * @author Administrator
 * 
 */
public class AjaxResData implements ResponseData {

	/**
	 * 业务数据
	 */
	private Object logic;

	private Map<String, Object> notices;

	public static final int OK = 200;
	public static final int FAIL = 300;

	public AjaxResData() {
		super();
	}

	public AjaxResData(Object logic, Map<String, Object> notices) {
		super();
		this.logic = logic;
		this.notices = notices;
	}

	public static AjaxResData getInstanceNotify(Notify notify) {

		// Map<String, Object> map = Lang.obj2map(notify);

		// if (!Strings.isEmpty(notify.getNavTabId())) {
		// //map.put("navTabId", notify.getNavTabId());
		// map.put("callbackType", "closeCurrent");
		// }

		return new AjaxResData(null, Lang.obj2map(notify));
	}
	

	public static int getstatusCode(String status) {

		return (status == "OK") ? OK : FAIL;
	}

	public static Map<String, Object> dialogAjaxMsg(String statusCode,
			String navTabId, String Msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusCode", getstatusCode(statusCode));
		map.put("message", Msg);
		if (!Strings.isEmpty(navTabId)) {
			map.put("navTabId", navTabId);
			map.put("callbackType", "closeCurrent");
		}
		return map;
	}

	public static Map<String, Object> dialogAjaxMsg(Notify notify) {
		return Lang.obj2map(notify);
	}

	public static Map<String, Object> dialogAjaxDone(String statusCode) {
		return dialogAjaxMsg(statusCode, null, null);
	}

	public Object getLogic() {
		return logic;
	}

	public void setLogic(Object logic) {
		this.logic = logic;
	}

	public Map<String, Object> getNotices() {
		return notices;
	}

	public void setNotices(Map<String, Object> notices) {
		this.notices = notices;
	}

}