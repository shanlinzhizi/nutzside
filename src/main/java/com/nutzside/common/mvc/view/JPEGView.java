package com.nutzside.common.mvc.view;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;

import com.nutzside.common.captcha.CaptchaServiceSingleton;
import com.octo.captcha.service.CaptchaServiceException;

public class JPEGView implements View {
	private static final Log log = Logs.getLog(JPEGView.class);

	@Override
	public void render(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Throwable {
		log.info("");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		/*
		 * // 输出图象到页面 ImageVerification iv = new ImageVerification();
		 * 
		 * if (ImageIO.write(iv.creatImage(), "JPEG", out)) {
		 * log.debugf("写入输出流成功:%s.", iv.getVerifyCode()); } else {
		 * log.debugf("写入输出流失败:%s.", iv.getVerifyCode()); }
		 * 
		 * session.setAttribute(CAPTCHA, iv.getVerifyCode());
		 */
		ServletOutputStream out = response.getOutputStream();
		try {
			String captchaId = request.getSession(true).getId();
			BufferedImage challenge = (BufferedImage) CaptchaServiceSingleton
					.getInstance().getChallengeForID(captchaId,
							request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
		} finally {
			out.close();
		}
	}
}
