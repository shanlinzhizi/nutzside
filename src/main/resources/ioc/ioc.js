var ioc = {
	configuration : {
		type : "freemarker.template.Configuration"
	},
	freeMarkerConfigurer: {
		type : "com.nutzside.common.freemarker.FreeMarkerConfigurer",
		args : [ {
			refer : "configuration"
		} ]
	}
};