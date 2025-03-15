package com.deer.data.sync.admin.icon;

import org.kordamp.ikonli.IkonProvider;
import org.kordamp.jipsy.annotations.ServiceProviderFor;

//https://blog.luckly-mjw.cn/tool-show/iconfont-preview/index.html
@ServiceProviderFor(IkonProvider.class)
public class WIconIkonProvider implements IkonProvider<WIcon> {
    @Override
    public Class<WIcon> getIkon() {
        return WIcon.class;
    }
}