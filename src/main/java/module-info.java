module org.example.projectobatallagallos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires io.ktor.client.core;
    requires io.ktor.client.cio;
    requires kotlinx.coroutines.core;
    requires io.ktor.http;
    requires io.ktor.utils;
    requires kotlinx.serialization.json;

    opens org.example.projectobatallagallos to javafx.fxml;
    exports org.example.projectobatallagallos;
}