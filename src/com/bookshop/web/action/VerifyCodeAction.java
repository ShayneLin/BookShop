package com.bookshop.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.bookshop.utils.VerifyCode;

public class VerifyCodeAction extends BaseAction {

	public void verifyCode() throws IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();// 获取一次性验证码图片
		// 该方法必须在getImage()方法之后来调用
		VerifyCode.output(image, response.getOutputStream());// 把图片写到指定流中
		// 把文本保存到session中，为LoginServlet验证做准备
		request.getSession().setAttribute("vCode", vc.getText());
		System.out.println("验证码");
	}

}
