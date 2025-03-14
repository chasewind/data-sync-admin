package com.deer.data.sync.admin.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

public class HomePageView extends BorderPane {

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

        ToggleButton menuBtn = new ToggleButton();
        menuBtn.setMnemonicParsing(false);
        menuBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        FontIcon menuIcon = new FontIcon("fa-align-justify");
        menuBtn.setGraphic(menuIcon);


        leftBox.getChildren().add(logoBtn);
        leftBox.getChildren().add(menuBtn);

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
        closeBtn.setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });


    }
}
