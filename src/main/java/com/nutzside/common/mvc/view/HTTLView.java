package com.nutzside.common.mvc.view;

import httl.Context;
import httl.web.WebEngine;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.AbstractPathView;

public class HTTLView extends AbstractPathView implements View {

	private final String ext = ".httl";

	public HTTLView(String dest) {
		super(dest);

	}

	/*
	 * 渲染页面
	 * 
	 * @see org.nutz.mvc.View#render(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 * object 只能为Map 否则有无
	 */
	public void render(HttpServletRequest req, HttpServletResponse resp,
			Object obj) throws Throwable {

		String path = evalPath(req, obj);

		Context context = Context.getContext();
		context.put("request", req);
		context.put("response", resp);
		Enumeration<?> reqs = req.getAttributeNames();
		while (reqs.hasMoreElements()) {
			String strKey = (String) reqs.nextElement();
			context.put(strKey, req.getAttribute(strKey));
		}

		WebEngine.getEngine()
				.getTemplate(getTemplatePath(path, req), req.getLocale())
				.render(obj, resp);
		
	}

	protected String getTemplatePath(String path, HttpServletRequest request) {

		// 空路径，采用默认规则
		if (Strings.isBlank(path)) {
			path = Mvcs.getRequestPath(request);
			path = (path.startsWith("/") ? "" : "/")
					+ Files.renameSuffix(path, ext);
		}
		// 绝对路径 : 以 '/' 开头的路径不增加 '/WEB-INF'
		else if (path.charAt(0) == '/') {
			if (!path.toLowerCase().endsWith(ext))
				path += ext;
		}
		// 包名形式的路径
		else {
			path = path.replace('.', '/') + ext;
		}

		return path;
	}
}