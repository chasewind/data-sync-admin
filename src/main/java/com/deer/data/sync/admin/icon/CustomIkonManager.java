package com.deer.data.sync.admin.icon;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.IkonHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class CustomIkonManager {
    private final List<IkonHandler> providers;

    public CustomIkonManager() {
        // 初始化服务提供者列表
        this.providers = loadProviders();
    }

    private List<IkonHandler> loadProviders() {
        List<IkonHandler> providers = new ArrayList<>();
        ServiceLoader<IkonHandler> loader = ServiceLoader.load(IkonHandler.class);

        // 加载所有服务提供者，但排除自定义图标提供者
        for (IkonHandler provider : loader) {
            if (!(provider instanceof WIconIkonProvider)) {
                providers.add(provider);
            }
        }

        // 最后添加自定义图标提供者
        providers.add(new WIconIkonHandler());
        return providers;
    }

    /**
     * 根据图标名称查找图标
     * @param description 图标描述
     * @return 找到的图标，如果未找到则返回 null
     */
    public Ikon findIkon(String description) {
        for (IkonHandler provider : providers) {
            Ikon ikon = provider.resolve(description);
            if (ikon != null) {
                return ikon;
            }
        }
        return null; // 如果未找到图标，返回 null
    }

}
