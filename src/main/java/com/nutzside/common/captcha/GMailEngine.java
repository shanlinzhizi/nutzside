package com.nutzside.common.captcha;

import java.awt.Color;
import java.awt.Font;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * 仿照JCaptcha2.0编写GMail验证码样式的图片引擎.
 * 
 * @author calvin
 */
public class GMailEngine extends ListImageCaptchaEngine {
	private static final Log log = Logs.getLog(GMailEngine.class);
	public static final String IMAGE_CAPTCHA_KEY = "imageCaptcha";// ImageCaptcha对象存放在Session中的key
	public static final String CAPTCHA_INPUT_NAME = "j_captcha";// 验证码输入表单名称
	public static final String CAPTCHA_IMAGE_URL = "/captcha";// 验证码图片URL
	private static final Integer MIN_WORD_LENGTH = 4;// 验证码最小长度
	private static final Integer MAX_WORD_LENGTH = 4;// 验证码最大长度
	private static final Integer IMAGE_HEIGHT = 26;// 验证码图片高度
	private static final Integer IMAGE_WIDTH = 70;// 验证码图片宽度
	private static final Integer MIN_FONT_SIZE = 15;// 验证码最小字体
	private static final Integer MAX_FONT_SIZE = 15;// 验证码最大字体
	private static final String RANDOM_WORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机字符
	private static final String IMAGE_PATH ="./com/nutzside/common/util/captcha/jpg/";// 随机背景图片路径
	
	
	// 验证码随机颜色
	private static final Color[] RANDOM_COLOR = new Color[] { 
			new Color(255, 255, 255), 
			new Color(255, 220, 220), 
			new Color(220, 255, 255), 
			new Color(220, 220, 255),
			new Color(255, 255, 220), 
			new Color(220, 255, 220) 
	};
	// 验证码随机字体
	private static final Font[] RANDOM_FONT = new Font[] { 
			new Font("nyala", Font.BOLD, MIN_FONT_SIZE), 
			new Font("Arial", Font.BOLD, MIN_FONT_SIZE),
			new Font("Bell MT", Font.BOLD, MIN_FONT_SIZE), 
			new Font("Credit valley", Font.BOLD, MIN_FONT_SIZE),
			new Font("Impact", Font.BOLD, MIN_FONT_SIZE) 
	};
	@Override
	protected void buildInitialFactories() {
		log.info("building finished");
		RandomListColorGenerator randomListColorGenerator = new RandomListColorGenerator(RANDOM_COLOR);
		BackgroundGenerator backgroundGenerator = new FileReaderRandomBackgroundGenerator(IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_PATH);
		WordGenerator wordGenerator = new RandomWordGenerator(RANDOM_WORD);
		FontGenerator fontGenerator = new RandomFontGenerator(MIN_FONT_SIZE, MAX_FONT_SIZE, RANDOM_FONT);
		TextDecorator[] textDecorator = new TextDecorator[] {};
		TextPaster textPaster = new DecoratedRandomTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH, randomListColorGenerator, textDecorator);
		WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
		addFactory(new GimpyFactory(wordGenerator, wordToImage));
	}
}

