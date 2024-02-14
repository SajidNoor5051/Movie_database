package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddMovie {

    public TextField addMovieName, addReleaseYear, addGenres, addRunningTime, addProductionCompany, addBudget, addRevenue;
    public Label addTitleLabel1;
    public Label addTitleLabel2;


    public void onCheckMovieClick(ActionEvent actionEvent) throws Exception {
        //MovieList.fileOperations();
        System.out.println(MovieList.movieList.get(101).getRunningTime());
        for (int i=0 ; i<MovieList.movieList.size() ; i++){
        if(addMovieName.getText().equalsIgnoreCase(MovieList.movieList.get(i).getTitle())){
            addTitleLabel1.setText("A movie already exists with this title!");
            addTitleLabel2.setText("Please Enter Again ");
            break;
        }
        else{
            addTitleLabel1.setText("Valid Input :D");
            addTitleLabel2.setText("Movie Title");
        }
        }
    }

    public void onAddClick(ActionEvent actionEvent) {
        String MovieInfo=addMovieName.getText()+","+addReleaseYear.getText()+","+addGenres.getText()+","+addRunningTime.getText()+
                ","+addProductionCompany.getText()+","+addBudget.getText()+","+addRevenue.getText();
        String [] out=MovieInfo.split(",");
        MovieList.movieList.add(new Movie(out[0],Integer.parseInt(out[1]),out[2],out[3],out[4],Integer.parseInt(out[5]),out[6],Integer.parseInt(out[7]),Integer.parseInt(out[8])));
    System.out.println(MovieList.movieList.size());
    }

    public void onBackToMainMenuClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 420);
        stage.setTitle("Main Menu ");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }
}
