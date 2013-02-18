package com.nutzside.system.module;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.nutzside.common.util.CollectionHandler;
import com.nutzside.system.domain.User;
import com.nutzside.system.service.UserService;

@IocBean
public class SystemModule {

	@Inject
	private UserService userService;
	
	@At
	@RequiresGuest
	public void login(@Param("name") String number, @Param("passwd") String password,
			@Param("rememberMe") boolean rememberMe, @Param("code") String code,HttpServletRequest request) {
		String host = request.getRemoteHost();
		//String auth=(String) Session.getAttribute(JPEGView.CAPTCHA);
		AuthenticationToken token = new UsernamePasswordToken(number, password, rememberMe, host);
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			User user = userService.fetchByName(number);
			Session session = subject.getSession();
			session.setAttribute("CurrentUser", user);
			long[] RoleArr = CollectionHandler.getIdsArr(userService.getRoleNameList(user));
			session.setAttribute("CurrentRole", RoleArr);
			//return DwzUtil.OK;
		} catch (UnknownAccountException e) {
			throw new RuntimeException("用户不存在");
		} catch (AuthenticationException e) {
			throw new RuntimeException("验证错误");
		} catch (Exception e) {
			throw new RuntimeException("登录失败", e);
		}
	

	}

	@At
	@Ok("forward:/main.html")
	@Fail("redirect:/index.html")
	public void main() {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			throw new RuntimeException("用户未登录!");
		}
	}

	@At
	@RequiresAuthentication
	public void logout(HttpServletResponse response) throws IOException {
		SecurityUtils.getSubject().logout();
		// TODO
		// 用nutz重定向视图总是报异常org.apache.shiro.session.UnknownSessionException: There is no session with id...
		// 所以暂时用这种方式重定向
		response.sendRedirect("index.html");
	}

	
}