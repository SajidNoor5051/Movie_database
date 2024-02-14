package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MovieList.fileOperations();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load() , 680, 420 );
        stage.setTitle("Main menu");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();

}
    public static void main(String[] args) {
        Application.launch();
    }
}