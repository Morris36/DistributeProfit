module com.example.distributeprofit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.math;
    requires mysql.connector.java;
    requires org.jetbrains.annotations;


    opens App to javafx.fxml;
    exports App;
}