package com.deer.data.sync.admin.view;

import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.Event;
import com.deer.data.sync.admin.event.EventType;
import com.deer.data.sync.admin.model.MenuInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.apache.commons.io.FileUtils;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HomePageView extends BorderPane {

    private    HBox centerBox = new HBox();
    public HomePageView(){
        this.setPrefHeight(511);
        this.setPrefWidth(833);
        this.setStyle("-fx-background-color: white;");
        HBox bottomBox = new HBox();
        bottomBox.setPrefHeight(40);
        bottomBox.setPrefWidth(200);
        BorderPane.setAlignment(bottomBox, Pos.CENTER);
        BorderPane.setMargin(bottomBox,new Insets(3));
        this.setBottom(bottomBox);

        VBox topBox = new VBox();
        BorderPane.setAlignment(topBox, Pos.CENTER);
        BorderPane.setMargin(topBox,new Insets(3));

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setMaxHeight(60);
        header.setMaxWidth(Double.MAX_VALUE);
        header.setMinWidth(200);
        header.setMinHeight(45);
        header.setPrefHeight(60);
        header.setPrefWidth(600);

        topBox.getChildren().add(header);

        //左侧login和菜单
        HBox leftBox = new HBox();

        leftBox.setAlignment(Pos.CENTER_LEFT);

        leftBox.setPrefHeight(100);
        leftBox.setPrefWidth(200);
        leftBox.setSpacing(7);

        Button logoBtn = new Button("客户端管理");
        logoBtn.setStyle("-fx-text-fill: linear-gradient(to right, #e00db4, #0d6bde);");
        logoBtn.setTextFill(Color.web("#0d6bde"));

//        ToggleButton menuBtn = new ToggleButton();
//        menuBtn.setMnemonicParsing(false);
//        menuBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//        FontIcon menuIcon = new FontIcon("fa-align-justify");
//        menuBtn.setGraphic(menuIcon);


        leftBox.getChildren().add(logoBtn);
//        leftBox.getChildren().add(menuBtn);

        header.getChildren().add(leftBox);

        //右侧最大化，最小化和关闭

        HBox rightBox = new HBox();
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        rightBox.setPrefWidth(200);
        rightBox.setPrefHeight(100);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        Button themeBtn = new Button();
        themeBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        FontIcon themeIcon = new FontIcon("fa-themeisle");
        themeBtn.setGraphic(themeIcon);

        Button userInfoBtn = new Button();
        userInfoBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        FontIcon userInfoIcon = new FontIcon("fa-user");
        userInfoBtn.setGraphic(userInfoIcon);


        Button minBtn = new Button();
        minBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        FontIcon minIcon = new FontIcon("fa-window-minimize");
        minBtn.setGraphic(minIcon);


        Button maxBtn = new Button();
        maxBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        FontIcon maxIcon = new FontIcon("fa-window-maximize");
        maxBtn.setGraphic(maxIcon);

        Button closeBtn = new Button();
        closeBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        FontIcon closeIcon = new FontIcon("fa-close");
        closeBtn.setGraphic(closeIcon);


        rightBox.getChildren().add(themeBtn);
        rightBox.getChildren().add(userInfoBtn);
        rightBox.getChildren().add(minBtn);
        rightBox.getChildren().add(maxBtn);
        rightBox.getChildren().add(closeBtn);

        header.getChildren().add(rightBox);

        this.setTop(topBox);


        //
        BorderPane.setAlignment(centerBox,Pos.CENTER);
        centerBox.setMaxWidth(Double.MAX_VALUE);
        centerBox.setMaxHeight(Double.MAX_VALUE);
        centerBox.setSpacing(5);
        centerBox.setPadding(new Insets(5));


        this.setCenter(centerBox);

        loadAllMenu();

        //点击的时候也触发展示左侧菜单
//        menuBtn.setOnAction(actionEvent -> {
//            Event<Boolean> event = new Event<>(EventType.EXPAND_MENU_EVENT, menuBtn.isSelected());
//            DefaultEventBus.getInstance().sendEvent(event);
//        });

        //
        closeBtn.setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });
        //触发展示左侧菜单
        Platform.runLater(()->{
            Event<Boolean> event = new Event<>(EventType.EXPAND_MENU_EVENT, true);
            DefaultEventBus.getInstance().sendEvent(event);
        });

    }
    private void loadAllMenu() {
        try {

            String menuInfo = FileUtils.readFileToString(new File(getClass().getClassLoader().getResource("menu.json").getFile()), "utf-8");
            List<MenuInfo> menuInfoList = new Gson().fromJson(menuInfo, new TypeToken<List<MenuInfo>>() {
            }.getType());
            //加载完菜单数据
            SideMenu sideMenu = new SideMenu(menuInfoList);

            centerBox.getChildren().add(sideMenu);
            Event<List<MenuInfo>> event = new Event<>(EventType.LOAD_MENU_SUCCESS_EVENT, menuInfoList);
            DefaultEventBus.getInstance().sendEvent(event);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
