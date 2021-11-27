module com.example.ticktacktoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ticktacktoe to javafx.fxml;
    exports com.example.ticktacktoe;
    exports com.example.ticktacktoe.controller;
    opens com.example.ticktacktoe.controller to javafx.fxml;
}