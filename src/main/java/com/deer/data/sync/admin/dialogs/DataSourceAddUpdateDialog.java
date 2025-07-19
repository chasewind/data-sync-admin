package com.deer.data.sync.admin.dialogs;

import atlantafx.base.layout.ModalBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

public class DataSourceAddUpdateDialog extends VBox {

   private GridPane grid = new GridPane();

    public DataSourceAddUpdateDialog(){
        this.setPadding(new Insets(10));
        this.setSpacing(12);
        this.setMinWidth(380);
        this.setMaxWidth(420);
        int colCount = 4;
        for (int i = 0; i < colCount; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / colCount);
            cc.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(cc);
        }
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(5));
        grid.setAlignment(Pos.CENTER);
        /* 第 1 行：3 个元素，放在前三列 */
        grid.add(new Label("链接名字"),0,0,1,1);
        TextField bizNameField = new TextField("链接名字");
        ComboBox<String> groupNameField = new ComboBox<>();
        grid.add(bizNameField,1,0,2,1);
        grid.add(groupNameField,3,0,1,1);
        /* 第 2 行：2 个元素 */
        grid.add(new Label("JDBC_URL"),0,1,1,1);
        TextArea jdbcUrlField = new TextArea();
        jdbcUrlField.setPrefRowCount(3);
        grid.add(jdbcUrlField,1,1,3,1);
        /* 第 3 行：4 个元素 */
        grid.add(new Label("账户信息"),0,2,1,1);
        TextField usernameField = new TextField("USERNAME");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("PASSWORD");
        TextField portField = new TextField();
        portField.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter(), 0,  // 默认值 0
                        change -> {
                            String newText = change.getControlNewText();
                            // 允许空串或整数
                            if (newText.matches("\\d*")) {
                                return change;
                            }
                            return null;   // 拦截
                        }));

        grid.add(usernameField,1,2,1,1);
        grid.add(passwordField,2,2,1,1);
        grid.add(portField,3,2,1,1);

        /* 第 4 行：2 个元素 */
        grid.add(new Label("备注"),0,3,1,1);
        TextArea memoField = new TextArea();
        memoField.setPrefRowCount(2);
        grid.add(memoField,1,3,3,1);

        /* 第 5 行：2 个元素 */

        Button linkTestBtn = new Button("网络测试");
        Button cancelBtn = new Button("取消");
        Button saveBtn = new Button("保存");
        grid.add(linkTestBtn,1,4,1,1);
        grid.add(cancelBtn,2,4,1,1);
        grid.add(saveBtn,3,4,1,1);

        this.getChildren().add(grid);
    }
}
