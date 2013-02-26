package com.nutzside.common.mvc.view;

import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;

import com.nutzside.common.captcha.ImageVerification;

public class JPEGView implements View {
	private static final Log log = Logs.getLog(JPEGView.class);
	public static String CAPTCHA = "CAPTCHA_NAME";

	@Override
	public void render(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Throwable {
		log.info("");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		HttpSession session = request.getSession();
		// 输出图象到页面
		OutputStream out = response.getOutputStream();
		ImageVerification iv = new ImageVerification();
		ImageIO.write(iv.creatImage(), "JPEG", out);
		session.setAttribute(CAPTCHA, iv.getVerifyCode());

	}
}
