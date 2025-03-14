package com.deer.data.sync.admin;

import atlantafx.base.theme.PrimerLight;
import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.EventSubscriber;
import com.deer.data.sync.admin.exception.DefaultExceptionHandler;
import com.deer.data.sync.admin.view.MainView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DataSyncClient extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //
        Thread.currentThread().setUncaughtExceptionHandler(new DefaultExceptionHandler(primaryStage));
        //注册事件机制
        EventSubscriber eventSubscriber = new EventSubscriber();
        DefaultEventBus.getInstance().register(eventSubscriber);
        //
        MainView mainView = new MainView();
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setCenter(mainView);
        root.setStyle("-fx-background-color:transparent;");

        Scene scene = new Scene(root);


        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("数据同步客户端管理工具");
        primaryStage.setResizable(true);
        primaryStage.setOnCloseRequest(t -> Platform.exit());
        primaryStage.setWidth(1280);
        primaryStage.setHeight(768);

        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        Platform.runLater(() -> {
            primaryStage.show();
            primaryStage.requestFocus();
        });
    }
}
