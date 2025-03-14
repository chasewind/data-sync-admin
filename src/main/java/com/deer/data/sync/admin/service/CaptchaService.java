package com.deer.data.sync.admin.service;

import com.deer.data.sync.admin.config.KaptchaConfig;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class CaptchaService {

    public static BufferedImage generateCaptcha(){

        DefaultKaptcha kaptcha =  KaptchaConfig.getDefaultKaptcha();
        String captchaText = kaptcha.createText(); // 生成验证码文本
        String uuid = UUID.randomUUID().toString();
        // 生成验证码图片
        BufferedImage image = kaptcha.createImage(captchaText);

        // 将图片转换为Base64字符串
        String base64Image = convertImageToBase64(image);
        return image;
    }

    /**
     * 将图片转换为Base64字符串
     * @param image 图片
     * @return Base64字符串
     */
    private static String convertImageToBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert image to Base64", e);
        }
    }
}
