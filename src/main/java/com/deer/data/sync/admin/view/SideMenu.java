package com.deer.data.sync.admin.view;

import animatefx.animation.AnimateFXInterpolator;
import animatefx.animation.AnimationFX;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.util.ParallelAnimationFX;
import atlantafx.base.theme.Tweaks;
import com.deer.data.sync.admin.event.DefaultEventBus;
import com.deer.data.sync.admin.event.Event;
import com.deer.data.sync.admin.event.EventType;
import com.deer.data.sync.admin.icon.WIcon;
import com.deer.data.sync.admin.model.MenuInfo;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

import static atlantafx.base.theme.Styles.FLAT;

public class SideMenu extends StackPane {

    private VBox menuBar;
    private TreeView<MenuInfo>treeView;
    public SideMenu(List<MenuInfo> menuInfoList){
        createView();
        createTreeItem(menuInfoList);
        DefaultEventBus.getInstance().registerConsumer(EventType.EXPAND_MENU_EVENT,event -> {
          Boolean selected = (Boolean) event.getEventData();
            expansion(selected);
        });

    }

    private void createView() {
        this.minWidthProperty().bind(this.prefWidthProperty());
        this.maxWidthProperty().bind(this.prefWidthProperty());
        menuBar = new VBox();
        menuBar.setMaxWidth(Double.MAX_VALUE);
        menuBar.setAlignment(Pos.TOP_CENTER);
        treeView = new TreeView<>();
        treeView.setShowRoot(false);

        treeView.getStyleClass().addAll(Tweaks.EDGE_TO_EDGE);
        treeView.setCellFactory(new Callback<>() {
            @Override
            public TreeCell<MenuInfo> call(TreeView<MenuInfo> menuInfoTreeView) {
                TreeCell<MenuInfo> treeCell = new TreeCell<>() {
                    @Override
                    protected void updateItem(MenuInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label titleLb = new Label(item.getMeta().getTitle());
                            String iconStr = item.getMeta().getIcon();
                            titleLb.setMaxWidth(Double.MAX_VALUE);
                            FontIcon titleIcon = FontIcon.of(Feather.CHEVRON_DOWN);
                            titleLb.setGraphic(FontIcon.of(WIcon.findByDescription(iconStr), 24, Color.CYAN));
                            titleLb.setGraphicTextGap(10);
                            HBox box = new HBox(titleLb, titleIcon);
                            HBox.setHgrow(titleLb, Priority.ALWAYS);

                            titleIcon.setVisible(!getTreeItem().isLeaf());
                            if (isSelected()) {
                                titleLb.setId("side-menu-cell-selected");
                            }
                            box.setPadding(new Insets(7, 7, 7, 0));
                            setGraphic(box);
                        }


                    }
                };
                treeCell.setOnMouseClicked(event -> {
                    if (!treeCell.isEmpty()) {
                        treeCell.getTreeItem().setExpanded(!treeCell.getTreeItem().isExpanded());
                    }
                });
                return treeCell;

            }
        });
        treeView.getSelectionModel().selectedItemProperty().addListener((observable ,oldValue,newValue)->{

            if(newValue!=null && newValue.isLeaf()){
                //TODO 发送事件添加tab
                System.out.println(newValue);
                Event<MenuInfo> event = new Event<>(EventType.ADD_TAB_EVENT, newValue.getValue());
                DefaultEventBus.getInstance().sendEvent(event);
            }

        });
        setId("side-menu");
        getChildren().addAll(menuBar, treeView);


    }
    private void createTreeItem(List<MenuInfo>  menuInfoList) {
        TreeItem<MenuInfo> root = new TreeItem<>();
        root.setExpanded(true);

        menuInfoList.forEach(obj -> {
            TreeItem<MenuInfo> child = new TreeItem<>(obj);
            String iconStr =obj.getMeta().getIcon();
            MenuButton menuButton = new MenuButton();
            menuButton.setPopupSide(Side.RIGHT);
            menuButton.setGraphic(FontIcon.of(WIcon.findByDescription( iconStr), 32));
            menuButton.getStyleClass().addAll(FLAT, Tweaks.NO_ARROW);
            menuButton.setId("side-menu-button");

            List<MenuInfo> childObj = obj.getChildren();
            if (childObj != null) {
                generateTree(child, childObj);
                generateMenu(menuButton, childObj);
            } else {
                menuButton.setOnMouseClicked(actionEvent->{
                    Event<MenuInfo> event = new Event<>(EventType.ADD_TAB_EVENT, obj);
                    DefaultEventBus.getInstance().sendEvent(event);
                });
            }

//            Platform.runLater(() -> {
                root.getChildren().add(child);
                menuBar.getChildren().add(menuButton);
//            });

        });

        treeView.setRoot(root);
    }

    private void generateTree(TreeItem<MenuInfo> parent,   List<MenuInfo> children) {
        children.forEach(obj -> {


            TreeItem<MenuInfo> current = new TreeItem<>(obj);
            List<MenuInfo> childObj = obj.getChildren();
            if (childObj != null) {
                generateTree(current, childObj);
            }

            Platform.runLater(() -> {
                parent.getChildren().add(current);

            });

        });
    }

    private void generateMenu(MenuButton parent,  List<MenuInfo> children) {
        children.forEach(obj -> {

            List<MenuInfo> childObj = obj.getChildren();
            var text = obj.getMeta().getTitle();
            String iconStr = obj.getMeta().getIcon();
            if (childObj != null) {
                var child = new Menu(text);
                child.setGraphic(FontIcon.of(WIcon.findByDescription( iconStr), 24));
                generateMenu2(child, childObj);

                Platform.runLater(() -> {
                    parent.getItems().add(child);

                });
            } else {
                var child = new MenuItem(text);
                child.setGraphic(FontIcon.of(WIcon.findByDescription( iconStr), 32));
                child.setOnAction(actionEvent -> {
                    Event<MenuInfo> event = new Event<>(EventType.ADD_TAB_EVENT, obj);
                    DefaultEventBus.getInstance().sendEvent(event);
                }
                );



                Platform.runLater(() -> {
                    parent.getItems().add(child);

                });
            }

        });
    }
    private void generateMenu2(Menu parent, List<MenuInfo> jsonArray) {
        jsonArray.forEach(obj -> {

            var text = obj.getMeta().getTitle();
            String iconStr = obj.getMeta().getIcon();
            List<MenuInfo> childObj = obj.getChildren();
            if (childObj != null) {
                var child = new Menu(text);
                child.setGraphic(FontIcon.of(WIcon.findByDescription(iconStr), 24));
                generateMenu2(child, childObj);

                Platform.runLater(() -> {
                    parent.getItems().add(child);

                });
            } else {
                var child = new MenuItem(text);
                child.setGraphic(FontIcon.of(WIcon.findByDescription( iconStr)));
                child.setOnAction(actionEvent -> {
                    Event<MenuInfo> event = new Event<>(EventType.ADD_TAB_EVENT, obj);
                    DefaultEventBus.getInstance().sendEvent(event);
                });
                Platform.runLater(() -> {
                    parent.getItems().add(child);

                });
            }

        });
    }
    public void expansion(boolean expansion) {


        if (expansion) {
            menuBar.setVisible(false);
            treeView.setVisible(true);
            AnimationFX animationFX = new FadeOut(this);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(0),
                    new KeyValue(this.prefWidthProperty(), 50D, AnimateFXInterpolator.EASE)),
                    new KeyFrame(Duration.millis(200),
                            new KeyValue(this.prefWidthProperty(), 300D, AnimateFXInterpolator.EASE)));

            animationFX.setTimeline(timeline);
            ParallelAnimationFX parallelAnimationFX
                    = new ParallelAnimationFX(new FadeIn(treeView), animationFX);
            parallelAnimationFX.play();
        } else {
            treeView.setVisible(false);
            menuBar.setVisible(true);
            AnimationFX animationFX = new FadeOut(this);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(0),
                    new KeyValue(this.prefWidthProperty(), 300D, AnimateFXInterpolator.EASE)),
                    new KeyFrame(Duration.millis(200),
                            new KeyValue(this.prefWidthProperty(), 50D, AnimateFXInterpolator.EASE)));

            animationFX.setTimeline(timeline);
            ParallelAnimationFX parallelAnimationFX
                    = new ParallelAnimationFX(new FadeIn(menuBar), animationFX);
            parallelAnimationFX.play();
        }
    }
}
