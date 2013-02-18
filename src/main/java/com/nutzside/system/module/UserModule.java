package com.nutzside.system.module;

import java.io.File;

import javax.servlet.ServletContext;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

import com.nutzside.system.service.UserService;



@IocBean
@At("/system/user")
public class UserModule {

	@Inject
	private UserService userService;

}