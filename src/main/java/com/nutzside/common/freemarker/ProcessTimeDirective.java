package com.nutzside.common.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

/**
 * 执行时间标签
 * 
 */
@IocBean
public class ProcessTimeDirective implements TemplateDirectiveModel {
	private static final Logger log = LoggerFactory.getLogger(ProcessTimeDirective.class);
	private static final DecimalFormat FORMAT = new DecimalFormat("0.000");

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		long time ;
		
			time = System.currentTimeMillis() ;
			Writer out = env.getOut();
			out.append("Processed in " + FORMAT.format(time / 1000F)
					+ " second(s)");
		}
	}


