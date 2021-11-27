package com.example.ticktacktoe;

import com.example.ticktacktoe.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class tictactoeMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(tictactoeMain.class.getResource("tictactoe-view.fxml"));
        fxmlLoader.setController(Controller.getInstance());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("tictactoe");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}