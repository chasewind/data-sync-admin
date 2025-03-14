package com.deer.data.sync.admin.view;

import animatefx.animation.RotateInDownRight;
import animatefx.animation.RotateInUpRight;
import animatefx.animation.RotateOutDownRight;
import animatefx.animation.RotateOutUpRight;
import animatefx.util.ParallelAnimationFX;
import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.EventType;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Objects;

public class LoginRegisterView extends AnchorPane {

    private StackPane switchPane;

    private LoginView loginView;
    private RegisterView registerView;


    public LoginRegisterView() {
        this.setMaxHeight(Double.MAX_VALUE);
        this.setMaxWidth(Double.MAX_VALUE);
        //
        switchPane = new StackPane();
        switchPane.setPrefWidth(887.0);
        switchPane.setPrefHeight(525.0);

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource("/images/login.png")).toString());
        // 设置背景图片
        BackgroundImage bImage = new BackgroundImage(backgroundImage, null, null, null, new BackgroundSize(100, 100, true, true, true, false));
        switchPane.setBackground(new Background(bImage));
        AnchorPane.setBottomAnchor(switchPane, 0.0);
        AnchorPane.setLeftAnchor(switchPane, 0.0);
        AnchorPane.setRightAnchor(switchPane, 0.0);
        AnchorPane.setTopAnchor(switchPane, 0.0);

        loginView = new LoginView();
        registerView = new RegisterView();
        switchPane.getChildren().add(loginView);

        //
        Button closeBtn = new Button();
        closeBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        closeBtn.setStyle("-fx-background-color: red;");

        FontIcon closeIcon = new FontIcon("fa-close");
        closeIcon.setIconColor(Color.web("#fcfafa"));
        closeBtn.setGraphic(closeIcon);

        AnchorPane.setRightAnchor(closeBtn,10.0);
        AnchorPane.setTopAnchor(closeBtn,10.0);

        this.getChildren().clear();
        this.getChildren().add(switchPane);
        this.getChildren().add(closeBtn);


        closeBtn.setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });

        DefaultEventBus.getInstance().registerConsumer(EventType.LOGIN_EVENT, event -> {
            RotateOutUpRight rotateOutDownRight = new RotateOutUpRight(registerView);
            rotateOutDownRight.setResetOnFinished(true);
            RotateInUpRight rotateInDownRight = new RotateInUpRight(loginView);
            rotateInDownRight.setResetOnFinished(true);
            rotateInDownRight.setOnFinished(event1 -> registerView.setVisible(false));
            ParallelAnimationFX parallelAnimationFX
                    = new ParallelAnimationFX(rotateOutDownRight, rotateInDownRight);
            loginView.setVisible(true);
            switchPane.getChildren().clear();
            switchPane.getChildren().add(loginView);
            parallelAnimationFX.play();
        });
        DefaultEventBus.getInstance().registerConsumer(EventType.REGISTER_EVENT, event -> {

            RotateOutDownRight rotateOutDownRight = new RotateOutDownRight(loginView);
            rotateOutDownRight.setResetOnFinished(true);
            RotateInDownRight rotateInDownRight = new RotateInDownRight(registerView);
            rotateInDownRight.setResetOnFinished(true);
            rotateInDownRight.setOnFinished(event1 -> loginView.setVisible(false));
            ParallelAnimationFX parallelAnimationFX
                    = new ParallelAnimationFX(rotateOutDownRight, rotateInDownRight);
            registerView.setVisible(true);
            switchPane.getChildren().clear();
            switchPane.getChildren().add(registerView);
            parallelAnimationFX.play();
        });
    }
}
