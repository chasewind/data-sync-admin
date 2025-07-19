package com.deer.data.sync.admin.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class DataSourceInfo {

    /**自增ID*/
    private Long id;

    /**自定义的一个有意义的名字*/
    private String bizName;

    /**分组*/
    private String groupName;

    /**数据库链接URL*/
    private String jdbcUrl;
    /**数据库链接用户名*/
    private String username;
    /**数据库链接密码*/
    private String password;
    /**数据库链接端口*/
    private Integer port;
    /**备注信息*/
    private String memo;

    private LocalDateTime createdTime;


    private LocalDateTime updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSourceInfo that = (DataSourceInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(bizName, that.bizName) && Objects.equals(groupName, that.groupName) && Objects.equals(jdbcUrl, that.jdbcUrl) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(port, that.port) && Objects.equals(memo, that.memo) && Objects.equals(createdTime, that.createdTime) && Objects.equals(updatedTime, that.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bizName, groupName, jdbcUrl, username, password, port, memo, createdTime, updatedTime);
    }
}
