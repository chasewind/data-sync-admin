package com.deer.data.sync.admin.global;

import com.deer.data.sync.admin.icon.CustomIkonManager;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

public class GlobalTools {

    private static  CustomIkonManager customIkonManager=null;
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
