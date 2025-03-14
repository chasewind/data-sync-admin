module com.deer.datasyncadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    //
    requires com.google.gson;
    requires org.apache.commons.io;
    requires org.apache.commons.lang3;
    requires org.apache.commons.collections4;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires com.google.common;
    requires javafx.swing;
    requires com.kitfox.svg;
    requires atlantafx.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome;
    requires kaptcha;
    requires AnimateFX;

    //
    opens com.deer.data.sync.admin.event to com.google.common;



    opens com.deer.data.sync.admin to javafx.fxml;
    exports com.deer.data.sync.admin;
}