module com.example.movielister {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;
    requires java.sql.rowset;

    opens com.example.movielister to javafx.fxml;
    exports com.example.movielister;
    exports com.example.movielister.Controller;
    opens com.example.movielister.Controller to javafx.fxml;
    opens com.example.movielister.Model to javafx.base;
    opens com.example.movielister.Model.Dto to javafx.base;
    opens com.example.movielister.util to javafx.fxml;

}