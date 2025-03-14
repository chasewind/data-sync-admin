package com.deer.data.sync.admin.view;

import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.EventType;
import javafx.scene.layout.StackPane;

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
        loadLoginView();
        //

        DefaultEventBus.getInstance().registerConsumer(EventType.LOGIN_SUCCESS_EVENT, event -> {
            //显示主体页面
            contentPane.getChildren().clear();
            HomePageView homePageView = new HomePageView();
            contentPane.getChildren().add(homePageView);
        });
        DefaultEventBus.getInstance().registerConsumer(EventType.LOGIN_FAIL_EVENT, event -> {
            //如果登录超过3次，给出提醒
        });
    }

    private void loadLoginView() {
        contentPane.getChildren().clear();
        LoginRegisterView loginRegisterView = new LoginRegisterView();
        contentPane.getChildren().add(loginRegisterView);
    }
}
