package com.nutzside.common.freemarker;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.mvc.Mvcs;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;



@IocBean(fields = { "configurer" })
public class FreeMarkerConfigurer{
	
	private Configuration configuration;
	
	public FreeMarkerConfigurer(Configuration configuration)
	{
		this.configuration = configuration;
		init();
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void init() {
		String path = Mvcs.getServletContext().getRealPath("WEB-INF");
		Ioc ioc = Mvcs.getIoc();
		try {
			initFreeMarkerConfigurer(path,ioc);
		} catch (IOException e) {
			Lang.wrapThrow(e);
		} catch (TemplateException e) {
			Lang.wrapThrow(e);
		}
	}
	private void initFreeMarkerConfigurer(String path,Ioc ioc) throws IOException, TemplateException
	{
		loadSettings(configuration);
		configuration.setDirectoryForTemplateLoading(Files.findFile(path));
		ProcessTimeDirective process_time = ioc.get(ProcessTimeDirective.class, "process");
		HtmlCutDirective html_cut = ioc.get(HtmlCutDirective.class, "htmlCut");
		configuration.setSharedVariable("process_time", process_time);
		configuration.setSharedVariable("html_cut", html_cut);

	}
	
	private void loadSettings(Configuration config){		
		InputStream in = null;
        try { 
        	File file = Files.findFile("freemarker.properties");
        	in=new BufferedInputStream(new FileInputStream(file));    
            if (in != null) {
                Properties p = new Properties();
                p.load(in);
                config.setSettings(p);
            }
        } catch (IOException e) {
        	Lang.wrapThrow(e);
        } catch (TemplateException e) {
        	Lang.wrapThrow(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch(IOException io) {
                	Lang.wrapThrow(io);
                }
            }
        }
	}
}