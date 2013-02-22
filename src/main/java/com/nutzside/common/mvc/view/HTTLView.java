package com.nutzside.common.mvc.view;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import httl.*;
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

	/* 渲染页面
	 * @see org.nutz.mvc.View#render(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public void render(HttpServletRequest req, HttpServletResponse resp,
			Object obj) throws Throwable {
		 Engine engine = Engine.getEngine();//加载模板引擎
			
		
		String path = evalPath(req, obj);

		// 空路径，采用默认规则
		if (Strings.isBlank(path)) {
			path = Mvcs.getRequestPath(req);
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
		
		httl.Template template = engine.getTemplate(path);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("obj", obj);
		parameters.put("request", req);
		parameters.put("response", resp);
		parameters.put("session", req.getSession());
		Enumeration<?> reqs = req.getAttributeNames();
		while (reqs.hasMoreElements()) {
			String strKey = (String) reqs.nextElement();
			parameters.put(strKey, req.getAttribute(strKey));
		}
		template.render(parameters, resp.getOutputStream());
	}
}