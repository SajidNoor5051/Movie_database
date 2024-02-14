package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintTable3 {
    List<Movie> list=new ArrayList<>();
    public PrintTable3(){
        this.list= movieSearchingOptiom.movielistForGenre;
    }

    public TableView<Movie> printTable;
    public TableColumn<Movie , String> movieName=new TableColumn<>("Movie Title");
    public TableColumn<Movie , Integer> releaseYear=new TableColumn<>("Year");
    public TableColumn<Movie , String> genres=new TableColumn<>("Genre");
    public TableColumn<Movie , String> genre1=new TableColumn<>("Genre 1");
    public TableColumn<Movie , String> genre2=new TableColumn<>("Genre 2");
    public TableColumn<Movie , String> genre3=new TableColumn<>("Genre 3");
    public TableColumn<Movie , Integer> runningTime=new TableColumn<>("Runtime");
    public TableColumn<Movie , String> productionCompanyName=new TableColumn<>("Production Company");
    public TableColumn<Movie , Integer> budget=new TableColumn<>("Budget");
    public TableColumn<Movie , Integer> revenue=new TableColumn<>("Revenue");
    public ObservableList<Movie> movieObservableList= FXCollections.observableArrayList();

    //@Override
    public void initialize() {
        System.out.println("Initializable enabled!");
        movieObservableList.addAll(this.list);
        movieName.setCellValueFactory(new PropertyValueFactory<Movie , String>("title"));
        releaseYear.setCellValueFactory(new PropertyValueFactory<Movie , Integer>("yearOfRelease"));
        genre1.setCellValueFactory(new PropertyValueFactory<Movie , String>("genre1"));
        genre2.setCellValueFactory(new PropertyValueFactory<Movie , String>("genre2"));
        genre3.setCellValueFactory(new PropertyValueFactory<Movie , String>("genre3"));
        runningTime.setCellValueFactory(new PropertyValueFactory<Movie , Integer>("runningTime"));
        productionCompanyName.setCellValueFactory(new PropertyValueFactory<Movie , String>("productionCompany"));
        budget.setCellValueFactory(new PropertyValueFactory<Movie , Integer>("budget"));
        revenue.setCellValueFactory(new PropertyValueFactory<Movie , Integer>("revenue"));
        printTable.setItems(movieObservableList);
    }

    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("searchMovies.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 420);
        stage.setTitle("Production Company Searching Options: ");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }
}
