package com.deer.data.sync.admin.icon;

import org.kordamp.ikonli.Ikon;

public enum WIcon implements Ikon {
    TOOL("tool", '\ue911'),
    MONITOR("monitor", '\ue906'),
    SYSTEM_COPY("system", '\ue908'),

    MESSAGE("message", '\ue90e'),

    LOG("log", '\ue909'),

    EDIT("edit", '\ue90c'),

    DICT("dict", '\ue90b'),

    POST("post", '\ue902'),

    TREE("tree", '\ue90f'),

    PEOPLES("peoples", '\ue90d'),

    USER("user", '\ue907'),

    TREE_TABLE("tree-table", '\ue900'),
    YUANDIAN("form", '\ue903'),
    HOME("home", '\ue916');

    public static WIcon findByDescription(String description) {
        for (WIcon font : values()) {
            if (font.getDescription().equals(description)) {
                return font;
            }
        }
        return YUANDIAN;
//        throw new IllegalArgumentException("Icon description '" + description + "' is invalid!");
    }

    private String description;
    private int code;

    WIcon(String description, int code) {
        this.description = description;
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getCode() {
        return code;
    }
}
