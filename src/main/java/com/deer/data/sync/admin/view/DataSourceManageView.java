package com.deer.data.sync.admin.view;

import atlantafx.base.controls.ModalPane;
import com.almasb.fxgl.cutscene.dialogue.DialogueGraph;
import com.deer.data.sync.admin.dialogs.DataSourceAddUpdateDialog;
import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.Event;
import com.deer.data.sync.admin.global.GlobalTools;
import com.deer.data.sync.admin.model.DataSourceInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 数据源管理
 */
public class DataSourceManageView extends VBox {

    private Button addButton;

    private TableView<DataSourceInfo> dataSourceTableView;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DataSourceManageView() {
        createView();

    }

    private void createView() {
        this.setPadding(new Insets(5));
        this.setSpacing(12);

        addButton = new Button("添加数据源");
        dataSourceTableView = new TableView<>();
        //单选
        dataSourceTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //
        TableColumn<DataSourceInfo, String> idColumn = new TableColumn<>("Id");
        idColumn.setPrefWidth(50);
        idColumn.setResizable(true);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<DataSourceInfo, String> bizNameColumn = new TableColumn<>("链接名称");
        bizNameColumn.setPrefWidth(120);
        bizNameColumn.setResizable(true);
        bizNameColumn.setCellValueFactory(new PropertyValueFactory<>("bizName"));

        TableColumn<DataSourceInfo, String> groupNameColumn = new TableColumn<>("分组名称");
        groupNameColumn.setPrefWidth(100);
        groupNameColumn.setResizable(true);
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        TableColumn<DataSourceInfo, String> jdbcUrlColumn = new TableColumn<>("数据库链接URL");
        jdbcUrlColumn.setPrefWidth(300);
        jdbcUrlColumn.setResizable(true);
        jdbcUrlColumn.setCellValueFactory(new PropertyValueFactory<>("jdbcUrl"));

        TableColumn<DataSourceInfo, String> usernameColumn = new TableColumn<>("数据库链接用户名");
        usernameColumn.setPrefWidth(100);
        usernameColumn.setResizable(true);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<DataSourceInfo, String> passwordColumn = new TableColumn<>("数据库链接密码");
        passwordColumn.setPrefWidth(100);
        passwordColumn.setResizable(true);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<DataSourceInfo, String> portColumn = new TableColumn<>("数据库链接端口");
        portColumn.setPrefWidth(80);
        portColumn.setResizable(true);
        portColumn.setCellValueFactory(new PropertyValueFactory<>("port"));


        TableColumn<DataSourceInfo, String> memoColumn = new TableColumn<>("备注信息");
        memoColumn.setPrefWidth(80);
        memoColumn.setResizable(true);
        memoColumn.setCellValueFactory(new PropertyValueFactory<>("memo"));


        TableColumn<DataSourceInfo, LocalDateTime> createdTimeColumn = new TableColumn<>("创建时间");
        createdTimeColumn.setPrefWidth(80);
        createdTimeColumn.setResizable(true);
        createdTimeColumn.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        createdTimeColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.format(formatter));
            }
        });

        TableColumn<DataSourceInfo, LocalDateTime> updatedTimeColumn = new TableColumn<>("更新时间");
        updatedTimeColumn.setPrefWidth(80);
        updatedTimeColumn.setResizable(true);
        updatedTimeColumn.setCellValueFactory(new PropertyValueFactory<>("updatedTime"));
        updatedTimeColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.format(formatter));
            }
        });

        TableColumn<DataSourceInfo, Void> actionColumn = buildActionColumn();

        dataSourceTableView.getColumns().addAll(idColumn,bizNameColumn,groupNameColumn,jdbcUrlColumn,usernameColumn,passwordColumn,portColumn,memoColumn,createdTimeColumn,updatedTimeColumn,actionColumn);


        addButton.setOnAction(actionEvent -> {
            //新增数据源
            DataSourceAddUpdateDialog dialog = new DataSourceAddUpdateDialog();
            Stage stage = new Stage();
            stage.initOwner(GlobalTools.getMainStage());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("配置数据源信息");
            Scene scene = new Scene(dialog);
            stage.setScene(scene);
            stage.showAndWait();
        });
        getChildren().addAll(addButton,dataSourceTableView);
    }

    private TableColumn<DataSourceInfo, Void> buildActionColumn( ) {
        TableColumn<DataSourceInfo, Void> actionColumn = new TableColumn<>("操作");
        actionColumn.setMinWidth(160);
        actionColumn.setResizable(true);
        actionColumn.setCellFactory(column -> new TableCell<>() {
            private final ToggleButton forbiddenBtn = new ToggleButton("禁用");
            private final Button searchPositionBtn = new Button("查询位点");

            private final Button updateBtn = new Button("修改");
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setAlignment(Pos.CENTER);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(forbiddenBtn, searchPositionBtn,updateBtn);
                    hbox.setSpacing(15);
                    hbox.setPadding(new Insets(10));
                    setGraphic(hbox);
                    forbiddenBtn.setOnAction(actionEvent -> {
                        DataSourceInfo dataSourceInfo = getTableView().getItems().get(getIndex());
                        // TODO 发起请求并触发数据改动
                        System.out.println("adjust-->"+dataSourceInfo);
                    });
                    searchPositionBtn.setOnAction(actionEvent -> {
                        //使用当前数据源做查询
                        DataSourceInfo dataSourceInfo = getTableView().getItems().get(getIndex());
                        // TODO 发起请求
                        System.out.println("searchPosition-->"+dataSourceInfo);
                    });
                    updateBtn.setOnAction(actionEvent -> {
                        //使用当前数据源做查询
                        DataSourceInfo dataSourceInfo = getTableView().getItems().get(getIndex());
                        // TODO 发起请求
                        System.out.println("update-->"+dataSourceInfo);
                    });
                }
            }

        });
        return actionColumn;
    }

}
