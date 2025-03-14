package com.deer.data.sync.admin.view;

import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.Event;
import com.deer.data.sync.admin.event.EventType;
import com.kitfox.svg.app.beans.SVGIcon;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.Objects;

public class RegisterView extends HBox {
    private VBox leftBox;
    private VBox rightBox;
    public RegisterView(){
        this.setMaxHeight(369.0);
        this.setMaxWidth(630.0);
        this.setPrefHeight(369.0);
        this.setPrefWidth(530.0);
        this.setStyle("-fx-background-color: #ffffff;-fx-background-radius: 10;-fx-effect: dropshadow(gaussian, black, 30, 0, 0, 0) ");

        SVGIcon svgIcon = new SVGIcon();

        try {
            svgIcon.setSvgURI(Objects.requireNonNull(LoginRegisterView.class.getResource("/images/bb.svg")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        leftBox = new VBox();
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setPadding(new Insets(40));
        leftBox.setSpacing(30);
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        leftBox.setStyle("-fx-background-color: linear-gradient(#00247f, #1c56c5);-fx-background-radius: 10 0 0 10;-fx-background-insets: 0px;");


        rightBox = new VBox();
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setMaxWidth(300);
        rightBox.setPrefWidth(300);
        rightBox.setSpacing(10);
        rightBox.setPadding(new Insets(40,20,10,20));



        getChildren().clear();
        getChildren().add(leftBox);
        getChildren().add(rightBox);

        VBox infoBox = new VBox();
        infoBox.setAlignment(Pos.TOP_CENTER);
        infoBox.setSpacing(20);
        VBox.setVgrow(infoBox,Priority.ALWAYS);

        //
        Label infoLb = new Label("数据同步管理系统");
        infoLb.setTextFill(Color.WHITE);
        infoLb.setFont(new Font(30));
        infoLb.setEffect(new Reflection(-10,0.57,0.26,0));

        //
        Label welcomeLb = new Label("欢迎访问");
        welcomeLb.setTextFill(Color.WHITE);
        welcomeLb.setFont(new Font(22));

        //
        ImageView imageView = new ImageView();
        imageView.setFitHeight(167.0);
        imageView.setFitWidth(254.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(SwingFXUtils.toFXImage((BufferedImage) svgIcon.getImage(), null));
        VBox.setVgrow(imageView,Priority.ALWAYS);

        //
        Label authLb = new Label("作者: 于东伟");
        authLb.setTextFill(Color.WHITE);
        authLb.setFont(new Font(18));


        infoBox.getChildren().add(infoLb);
        infoBox.getChildren().add(welcomeLb);
        infoBox.getChildren().add(imageView);
        infoBox.getChildren().add(authLb);


        leftBox.getChildren().add(infoBox);

        //
        Label registerLb = new Label("注 册");
        registerLb.setFont(new Font("System Bold" ,36));


        TextField userNameTextField = new TextField("用户名");
        userNameTextField.setFont(new Font(16));
        userNameTextField.setMaxWidth(Double.MAX_VALUE);
        userNameTextField.setPrefHeight(36);
        userNameTextField.setPrefWidth(228);


        PasswordField pwdTextField = new PasswordField();
        pwdTextField.setPromptText("密码");
        pwdTextField.setFont(new Font(16));
        pwdTextField.setMaxWidth(Double.MAX_VALUE);
        pwdTextField.setPrefHeight(36);
        pwdTextField.setPrefWidth(228);

        PasswordField pwdConfirmTextField = new PasswordField();
        pwdConfirmTextField.setPromptText("确认密码");
        pwdConfirmTextField.setFont(new Font(16));
        pwdConfirmTextField.setMaxWidth(Double.MAX_VALUE);
        pwdConfirmTextField.setPrefHeight(36);
        pwdConfirmTextField.setPrefWidth(228);


        Button registerBtn = new Button("注   册");
        registerBtn.setFont(new Font(18));
        registerBtn.setMaxWidth(Double.MAX_VALUE);


        HBox gotoLoginPanel = new HBox();
        gotoLoginPanel.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(gotoLoginPanel,Priority.ALWAYS);

        Label loginLb = new Label("已有账号去");
        loginLb.setPrefHeight(40);
        Hyperlink loginLink = new Hyperlink("登录");
        loginLink.setFont(new Font(16));
        gotoLoginPanel.getChildren().add(loginLb);
        gotoLoginPanel.getChildren().add(loginLink);

        rightBox.getChildren().add(registerLb);
        rightBox.getChildren().add(userNameTextField);
        rightBox.getChildren().add(pwdTextField);
        rightBox.getChildren().add(pwdConfirmTextField);

        rightBox.getChildren().add(registerBtn);
        rightBox.getChildren().add(gotoLoginPanel);
        //
        loginLink.setOnAction(actionEvent->{
            Event<Void> event = new Event<>(EventType.LOGIN_EVENT, null);
            DefaultEventBus.getInstance().sendEvent(event);
        });
    }
}
