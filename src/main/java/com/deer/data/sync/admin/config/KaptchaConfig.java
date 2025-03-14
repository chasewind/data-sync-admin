package com.deer.data.sync.admin.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;

public class KaptchaConfig {

    private static DefaultKaptcha kaptcha = null;

    public static DefaultKaptcha getDefaultKaptcha() {
        if (kaptcha != null) {
            return kaptcha;
        }
        kaptcha = new DefaultKaptcha();

        Properties properties = new Properties();
        properties.put("kaptcha.border", "no"); // 验证码边框
        properties.put("kaptcha.textproducer.font.color", "black"); // 字体颜色
        properties.put("kaptcha.textproducer.char.space", "5"); // 字符间距
        properties.put("kaptcha.textproducer.char.length", "6"); // 字符长度
        properties.put("kaptcha.image.width", "125"); // 图片宽度
        properties.put("kaptcha.image.height", "45"); // 图片高度
        properties.put("kaptcha.textproducer.font.size", "25"); // 字体大小
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑"); // 字体样式
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise"); // 不显示干扰线

        Config config = new Config(properties);
        kaptcha.setConfig(config);

        return kaptcha;
    }
}
