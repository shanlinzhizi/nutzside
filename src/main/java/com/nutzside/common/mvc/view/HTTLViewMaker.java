package com.nutzside.common.mvc.view;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.View;
import org.nutz.mvc.ViewMaker;


public class HTTLViewMaker implements ViewMaker {

	@Override
	public View make(Ioc ioc, String type, String value) {
		if("httl".equalsIgnoreCase(type)){
			return new HTTLView(value);
		}
		return null;
	}
	

}
