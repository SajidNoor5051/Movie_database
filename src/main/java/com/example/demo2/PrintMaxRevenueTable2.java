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

public class PrintMaxRevenueTable2 {
    List<Movie> list = new ArrayList<>();

    public PrintMaxRevenueTable2() {
        this.list = HomePageLogin.basedOnProductionCompanyRevenue;
    }

    public TableView<Movie> table;
    public TableColumn<Movie, String> movieName;
    public TableColumn<Movie, Integer> revenue;
    public TableColumn<Movie, String> productionCompany;

    public ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();

    //@Override
    public void initialize() {
        movieObservableList.addAll(this.list);
        movieName.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        revenue.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("revenue"));
        productionCompany.setCellValueFactory(new PropertyValueFactory<Movie, String>("productionCompany"));
        table.setItems(movieObservableList);
    }

    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoggedInProductionCompany.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 420);
        stage.setTitle("Production Company Searching Options: ");
        stage.setScene(scene);
        stage.show();
    }

    public void onReloadclick(ActionEvent actionEvent) throws IOException {
        HomePageLogin.server = new SocketWrapper("127.0.0.1", 3000);
        try {
            HomePageLogin.server.write(HomePageLogin.productionCompany);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Object data = HomePageLogin.server.read();
            HomePageLogin.listFromServer = (List<Movie>) data;

        } catch (IOException e) {
            System.out.println("Interrupted");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                HomePageLogin.server.closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        HomePageLogin.basedOnProductionCompanyRevenue = MovieList.MoviesWithMaxRenevue(HomePageLogin.listFromServer);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MaxRevenuePrintScreen2.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Max Revenue Movies By " + HomePageLogin.productionCompany);
        stage.setScene(scene);
        stage.show();
    }
}
