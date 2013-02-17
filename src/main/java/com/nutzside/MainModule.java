package com.nutzside;

import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@IocBy(type=ComboIocProvider.class,args={"*org.nutz.ioc.loader.json.JsonLoader","ioc/",
	  "*org.nutz.ioc.loader.annotation.AnnotationIocLoader","cn.nutzside"})
@Encoding(input="utf8",output="utf8")
@Modules(scanPackage=true)
@Localization("msg")
@Ok("ioc:json")
@Fail("json")
public class MainModule {
}
