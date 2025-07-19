package com.deer.data.sync.admin.global;

import com.deer.data.sync.admin.icon.CustomIkonManager;
import javafx.stage.Stage;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

public class GlobalTools {

    private static  CustomIkonManager customIkonManager=null;

    private static Stage primaryStage=null;

    public static void registerMainStage(Stage mainStage){
        primaryStage = mainStage;
    }

    public static Stage getMainStage(){
        return primaryStage;
    }

    public static void initCustomIkonManager() {
          customIkonManager = new CustomIkonManager();
    }

    public static FontIcon findFontIcon(String description){
        Ikon icon = customIkonManager.findIkon(description);

        if(icon == null){
           return null;
        }
        return (FontIcon)icon;
    }

    public static Ikon findIkon(String description){
        return customIkonManager.findIkon(description);
    }

}
