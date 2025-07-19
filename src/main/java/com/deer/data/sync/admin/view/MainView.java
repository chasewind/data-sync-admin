package com.deer.data.sync.admin.view;

import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.Event;
import com.deer.data.sync.admin.event.EventType;
import com.deer.data.sync.admin.model.MenuInfo;
import com.deer.data.sync.admin.model.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.layout.StackPane;
import org.apache.commons.io.FileUtils;
import org.kordamp.ikonli.IkonHandler;
import org.kordamp.ikonli.javafx.IkonResolver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ServiceLoader;

public class MainView extends StackPane {

    private final StackPane contentPane;

    public   MainView() {
        this.setPrefHeight(455.0);
        this.setPrefWidth(539.0);
        setStyle("-fx-background-radius: 10;-fx-border-radius: 10px;-fx-border-width: 1px;-fx-effect: dropshadow(gaussian, black, 20, 0, 0, 0)");
        //
        contentPane = new StackPane();
        contentPane.setMaxHeight(Double.MAX_VALUE);
        contentPane.setMaxWidth(Double.MAX_VALUE);
        this.getChildren().clear();
        this.getChildren().add(contentPane);

        //


        //
        loadLoginView();
        //
        ClassLoader classLoader = IkonResolver.class.getClassLoader();
        ServiceLoader<IkonHandler> loader = ServiceLoader.load(IkonHandler.class,classLoader);
        for(IkonHandler ikonHandler:loader){
            System.out.println("icon handler: "+ikonHandler);
        }


        DefaultEventBus.getInstance().registerConsumer(EventType.LOGIN_SUCCESS_EVENT, event -> {
            //显示主体页面
            contentPane.getChildren().clear();
            HomePageView homePageView = new HomePageView();
            contentPane.getChildren().add(homePageView);
        });
        DefaultEventBus.getInstance().registerConsumer(EventType.LOGIN_FAIL_EVENT, event -> {
            //如果登录超过3次，给出提醒
        });
        DefaultEventBus.getInstance().registerConsumer(EventType.ADD_TAB_EVENT,event -> {
            //DataSourceManageView
            MenuInfo menuInfo = (MenuInfo)event.getEventData();
            if(menuInfo.getComponent().equals("DataSourceManageView")) {
                DataSourceManageView dataSourceManageView = new DataSourceManageView();
                contentPane.getChildren().add(dataSourceManageView);
            }


        });
    }

    private void loadLoginView() {
        contentPane.getChildren().clear();
        LoginRegisterView loginRegisterView = new LoginRegisterView();
        contentPane.getChildren().add(loginRegisterView);
    }


}
