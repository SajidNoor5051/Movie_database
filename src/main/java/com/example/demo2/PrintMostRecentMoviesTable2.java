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

public class PrintMostRecentMoviesTable2 {
    List<Movie> list = new ArrayList<>();

    //static List<Movie> listFromServer=new ArrayList<>();
    public PrintMostRecentMoviesTable2() {
        this.list = HomePageLogin.basedOnProductionCompanyReleaseYear;
    }

    public TableView<Movie> table;
    public TableColumn<Movie, String> movieName;
    public TableColumn<Movie, Integer> releaseYear;
    public TableColumn<Movie, String> productionCompany;

    public ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();

    //@Override
    public void initialize() {
        movieObservableList.addAll(this.list);
        movieName.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        releaseYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("yearOfRelease"));
        productionCompany.setCellValueFactory(new PropertyValueFactory<Movie, String>("productionCompany"));
        table.setItems(movieObservableList);
    }

    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoggedInProductionCompany.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 420);
        stage.setTitle("You are logged in as " + HomePageLogin.productionCompany);
        stage.setScene(scene);

        stage.show();
    }

    public void onReloadButtonClick(ActionEvent actionEvent) throws IOException {
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

        HomePageLogin.basedOnProductionCompanyReleaseYear = MovieList.MoviesWithLatestReleaseYear(HomePageLogin.listFromServer);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RecentMoviesPrintScreen2.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Most Recent Movie By " + HomePageLogin.productionCompany);
        stage.setScene(scene);
        stage.show();

    }

}
