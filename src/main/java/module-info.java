module org.example.rakata {
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

    // Open packages to javafx.fxml for reflection
    opens org.example.rakata.example to javafx.fxml;
    opens org.example.rakata.example.gui to javafx.fxml;

    // Export packages for use by other modules
    exports org.example.rakata.example;
    exports org.example.rakata.example.gui;
}
