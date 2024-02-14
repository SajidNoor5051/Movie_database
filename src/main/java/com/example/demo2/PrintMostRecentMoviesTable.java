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

public class PrintMostRecentMoviesTable {
    List<Movie> list=new ArrayList<>();
    public  PrintMostRecentMoviesTable(List<Movie> list) {
        this.list=list;

    }
    public PrintMostRecentMoviesTable(){
        this.list=HomePageLogin.basedOnProductionCompanyReleaseYear;
    }

    public TableView<Movie> table;
    public TableColumn<Movie , String> movieName;
    public TableColumn<Movie , Integer> releaseYear;
    public TableColumn<Movie , String> productionCompany;

    public ObservableList<Movie> movieObservableList= FXCollections.observableArrayList();

    //@Override
    public  void initialize() {
        movieObservableList.addAll(this.list);
        movieName.setCellValueFactory(new PropertyValueFactory<Movie , String>("title"));
        releaseYear.setCellValueFactory(new PropertyValueFactory<Movie , Integer>("yearOfRelease"));
        productionCompany.setCellValueFactory(new PropertyValueFactory<Movie , String>("productionCompany"));
        table.setItems(movieObservableList);
    }
    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("searchByProductionCompany.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 420);
        stage.setTitle("Production Company Searching Options: ");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }
}
