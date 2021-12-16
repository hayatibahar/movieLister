module com.example.movielister {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.example.movielister to javafx.fxml;
    exports com.example.movielister;
}