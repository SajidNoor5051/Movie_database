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

public class PrintMaxRevenueTable {
    List<Movie> list=new ArrayList<>();

    public PrintMaxRevenueTable(){
        this.list=showProductionCompanyInfo.movielistOfMaxRevenue;
    }

    public TableView<Movie> table;
    public TableColumn<Movie , String> movieName;
    public TableColumn<Movie , Integer> revenue;
    public TableColumn<Movie , String> productionCompany;

    public ObservableList<Movie> movieObservableList= FXCollections.observableArrayList();

    //@Override
    public  void initialize() {
        movieObservableList.addAll(this.list);
        movieName.setCellValueFactory(new PropertyValueFactory<Movie , String>("title"));
        revenue.setCellValueFactory(new PropertyValueFactory<Movie , Integer>("revenue"));
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
