package com.deer.data.sync.admin.model;

import lombok.Data;

import java.util.List;

@Data
public class MenuInfo {
    private String name;
    private String path;
    private boolean hidden;
    private String redirect;
    private String component;

    private boolean alwaysShow;
    private MenuMeta meta;
    private List<MenuInfo> children;


}
