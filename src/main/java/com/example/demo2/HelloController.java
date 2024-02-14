package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    public Stage stage = new Stage();
    public Scene scene;
    public FXMLLoader fxmlLoader;
    public Label productionCompanyNameLabel;
    public Label totalProfitLabel;
    public Label totalProfit;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onSearchMoviesClick(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("searchMovies.fxml"));
        scene = new Scene(fxmlLoader.load() , 680, 420 );
        stage.setTitle("Movie Searching Options: ");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    public void onSearchProductionCompanyCLick(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("searchByProductionCompany.fxml"));
        scene = new Scene(fxmlLoader.load() , 680, 420 );
        stage.setTitle("Production Company Searching Options: ");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }
    public void onExitClick(ActionEvent actionEvent) {
        return;
    }


    public void onAddMoviesClick(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addMovies.fxml"));
        scene = new Scene(fxmlLoader.load() , 680, 420 );
        stage.setTitle("Add Movies : ");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

}