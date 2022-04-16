module com.example.distributeprofit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.math;
    requires mysql.connector.java;


    opens App to javafx.fxml;
    exports App;
}