package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class movieSearchingOptiom{


    public Stage stage = new Stage();
    public Scene scene;
    public FXMLLoader fxmlLoader;
    public AnchorPane anchorPaneLeft;
    public AnchorPane anchorPaneRight;
    public TextField releaseYearTextField,movieNameTextField,genreTextField,productionCompanyTextField;
    public TextField lowerRangeInput,upperRangeInput;
    public static List<Movie> movielistforMovieName = new ArrayList<>();
    public static List<Movie> movielistForYear = new ArrayList<>();
    public static List<Movie> movielistForGenre = new ArrayList<>();
    public static List<Movie> movielistForProductionCompany = new ArrayList<>();
    public static List<Movie> movieListForRunningTime = new ArrayList<>();
    public static List<Movie> topTenMoviesList = new ArrayList<>();
    public Label yearLAbel;

    //public PrintTable printTable;
    public void onMovieTitleClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("MovieSearchingOptionInput.fxml"));
        anchorPaneRight.getChildren().setAll(pane);

    }

    public void onByReleaseYearClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("MovieReleaseYearInput.fxml"));
        anchorPaneRight.getChildren().setAll(pane);
    }

    public void onByGenreClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("MovieGenreInput.fxml"));
        anchorPaneRight.getChildren().setAll(pane);
    }

    public void onByProductionCompanyClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("MovieProductionCompanyInput.fxml"));
        anchorPaneRight.getChildren().setAll(pane);
    }

    public void onByRunningTimeClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("Movie Running Time Input.fxml"));
        anchorPaneRight.getChildren().setAll(pane);
    }

    public void onTopTenMoviesClick(ActionEvent actionEvent) throws IOException {
        topTenMoviesList = MovieList.topTenMovies();
        //PrintTable printTable=new PrintTable(topTenMoviesList);
        //printTable.initialize();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrintOptionForMovies.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Top 10 Movies : ");
        stage.setScene(scene);
        stage.show();
    }

    public void onBackToMainMenuClick(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load() , 680, 420 );
        stage.setTitle("Main Menu ");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    public void onPrintByMovieTitleClick(ActionEvent actionEvent) throws IOException {
        movielistforMovieName = MovieList.searchByMovieName(movieNameTextField.getText());
        //PrintTable printTable=new PrintTable(movielistforMovieName);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrintOptionForMoviesByTitle.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Total Profit : ");
        stage.setScene(scene);
        stage.show();
    }

    public void onPrintWithThisYearClick(ActionEvent actionEvent) throws IOException {
        movielistForYear = MovieList.searchByYear(Integer.parseInt(releaseYearTextField.getText()));
        if(movielistForYear.size()==0){
            yearLAbel.setText("No such movies with this release year!");
        }
        else {
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrintOptionForMoviesByYear.fxml"));
            scene = new Scene(fxmlLoader.load());
            stage.setTitle("Total Profit : ");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onPrintWithThisGenreClick(ActionEvent actionEvent) throws IOException {
        movielistForGenre = MovieList.searchByGenre(genreTextField.getText());

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrintOptionForMoviesByGenre.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Total Profit : ");
        stage.setScene(scene);
        stage.show();
    }

    public void onPrintWithThisProductionCompanyClick(ActionEvent actionEvent) throws IOException {
        movielistForProductionCompany = MovieList.searchByProductionCompany(productionCompanyTextField.getText());
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrintOptionForMoviesByProductionCompany.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Total Profit : ");
        stage.setScene(scene);
        stage.show();
    }

    public void onPrintWithThisRunningTimeClick(ActionEvent actionEvent) throws IOException {
        movieListForRunningTime = MovieList.searchByRunningTime(Integer.parseInt(lowerRangeInput.getText()),Integer.parseInt(upperRangeInput.getText()));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrintOptionForMoviesByRunningTime.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Movies By Running Time : ");
        stage.setScene(scene);
        stage.show();
    }
    public void onBackClick(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("searchMovies.fxml"));
        scene = new Scene(fxmlLoader.load() , 680, 420 );
        stage.setTitle("Movie Searching Options: ");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }


}
