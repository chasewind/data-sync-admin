package com.deer.data.sync.admin.view;

import atlantafx.base.layout.InputGroup;
import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.Event;
import com.deer.data.sync.admin.event.EventType;
import com.deer.data.sync.admin.model.UserInfo;
import com.deer.data.sync.admin.service.CaptchaService;
import com.kitfox.svg.app.beans.SVGIcon;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.Objects;

public class LoginView extends HBox {

    private VBox leftBox;
    private VBox rightBox;

    private boolean skipVerify = true;
    public LoginView(){
        this.setMaxHeight(369.0);
        this.setMaxWidth(630.0);
        this.setPrefHeight(369.0);
        this.setPrefWidth(530.0);
        this.setStyle("-fx-background-color: #ffffff;-fx-background-radius: 10;-fx-effect: dropshadow(gaussian, black, 30, 0, 0, 0) ");

        SVGIcon svgIcon = new SVGIcon();

        try {
            svgIcon.setSvgURI(Objects.requireNonNull(LoginRegisterView.class.getResource("/images/dd.svg")).toURI());
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
        Label loginLb = new Label("登 录");
        loginLb.setFont(new Font("System Bold" ,36));
        ProgressBar progressBar = new ProgressBar(1.0);
        progressBar.setMinHeight(10);
        progressBar.setMaxWidth(Double.MAX_VALUE);
//https://kordamp.org/ikonli/cheat-sheet-fontawesome.html
        InputGroup userNameGroup = new InputGroup();
        userNameGroup.setSpacing(10);
        Label userNameLb = new Label();
        userNameLb.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        userNameLb.setPrefHeight(40);
        userNameLb.setPrefWidth(42);
        // 创建FontIcon并设置图标
        FontIcon userNameIcon = new FontIcon("fa-user");
//        userNameIcon.setIconSize(24); // 设置图标大小
        userNameLb.setGraphic(userNameIcon);

        TextField userNameTextField = new TextField("用户名");
        userNameTextField.setFont(new Font(16));
        userNameTextField.setMaxWidth(Double.MAX_VALUE);
        userNameTextField.setPrefHeight(36);
        userNameTextField.setPrefWidth(228);
        userNameGroup.getChildren().add(userNameIcon);
        userNameGroup.getChildren().add(userNameTextField);




        InputGroup passwordGroup = new InputGroup();
        passwordGroup.setSpacing(10);
        Label passwordLb = new Label();
        passwordLb.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        passwordLb.setPrefHeight(40);
        passwordLb.setPrefWidth(42);
        // 创建FontIcon并设置图标
        FontIcon passwordIcon = new FontIcon("fa-lock");
//        passwordIcon.setIconSize(24); // 设置图标大小
        passwordLb.setGraphic(passwordIcon);


        PasswordField pwdTextField = new PasswordField();
        pwdTextField.setPromptText("密码");
        pwdTextField.setFont(new Font(16));
        pwdTextField.setMaxWidth(Double.MAX_VALUE);
        pwdTextField.setPrefHeight(36);
        pwdTextField.setPrefWidth(228);
        passwordGroup.getChildren().add(passwordIcon);
        passwordGroup.getChildren().add(pwdTextField);


        //
        AnchorPane verifyCodePane = new AnchorPane();
        verifyCodePane.setMaxWidth(40);
        TextField verifyCodeTextField = new TextField("验证码");
        verifyCodeTextField.setFont(new Font(16));
        AnchorPane.setTopAnchor(verifyCodeTextField,0.0);
        AnchorPane.setLeftAnchor(verifyCodeTextField,0.0);
        AnchorPane.setBottomAnchor(verifyCodeTextField,0.0);
        AnchorPane.setRightAnchor(verifyCodeTextField,-3.0);
        verifyCodeTextField.setPrefWidth(260.0);
        verifyCodeTextField.setPrefHeight(45);

        ImageView verifyImg = new ImageView();
        verifyImg.setFitHeight(34);
        verifyImg.setFitWidth(80);
        verifyImg.setLayoutX(170);
        verifyImg.setLayoutY(6);
        verifyImg.setPickOnBounds(true);
        verifyImg.setPreserveRatio(true);
        AnchorPane.setTopAnchor(verifyImg,6.0);
        AnchorPane.setLeftAnchor(verifyImg,170.0);
        AnchorPane.setBottomAnchor(verifyImg,5.0);
        AnchorPane.setRightAnchor(verifyImg,7.0);
        BufferedImage image =  CaptchaService.generateCaptcha();
        verifyImg.setImage(SwingFXUtils.toFXImage(image, null));

        verifyCodePane.getChildren().add(verifyCodeTextField);
        verifyCodePane.getChildren().add(verifyImg);

        Label msgLb = new Label("");
        msgLb.setAlignment(Pos.CENTER);
        msgLb.setStyle("-fx-text-fill:red;");

        Button loginBtn = new Button("登   录");
        loginBtn.setFont(new Font(18));
        loginBtn.setMaxWidth(Double.MAX_VALUE);

        HBox rememberPanel= new HBox();
        rememberPanel.setAlignment(Pos.CENTER_LEFT);

        CheckBox rememberCheckBox = new CheckBox("记住密码");
        rememberPanel.getChildren().add(rememberCheckBox);

        HBox gotoRegisterPanel = new HBox();
        gotoRegisterPanel.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(gotoRegisterPanel,Priority.ALWAYS);

        Label  registerLb = new Label("还没有账号?");
        registerLb.setPrefHeight(40);
        Hyperlink registerLink = new Hyperlink("注册");
        registerLink.setFont(new Font(16));
        gotoRegisterPanel.getChildren().add(registerLb);
        gotoRegisterPanel.getChildren().add(registerLink);

        rightBox.getChildren().add(loginLb);
        rightBox.getChildren().add(progressBar);
        rightBox.getChildren().add(userNameGroup);
        rightBox.getChildren().add(passwordGroup);
        rightBox.getChildren().add(verifyCodePane);
        rightBox.getChildren().add(msgLb);
        rightBox.getChildren().add(loginBtn);
        rightBox.getChildren().add(rememberPanel);
        rightBox.getChildren().add(gotoRegisterPanel);

        //
        loginBtn.setOnAction(actionEvent -> {
            //mock logic
            if(skipVerify){
                Event<UserInfo> event = new Event<>(EventType.LOGIN_SUCCESS_EVENT, new UserInfo());
                DefaultEventBus.getInstance().sendEvent(event);
            }
        });
        //
        registerLink.setOnAction(actionEvent -> {
            Event<Void> event = new Event<>(EventType.REGISTER_EVENT, null);
            DefaultEventBus.getInstance().sendEvent(event);
        });
    }
}
