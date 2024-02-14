package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class showProductionCompanyInfo {
    public Stage stage = new Stage();
    public Scene scene;
    public FXMLLoader fxmlLoader;
    public AnchorPane anchorPaneLeft;
    public AnchorPane anchorPaneRight;
    public Button printMaxRevenueMovies;
    public Button printMostRecentMovies;
    public Label productionCompanyLabel1;
    public Label productionCompanyLabel2;
    public Label productionCompanyLabel3;
    public TextField productionCompanyInput1;
    public TextField productionCompanyInput2;
    public TextField productionCompanyInput3;
    public TextField textFieldOfRecentMovies;
    public Label outputLabel;
    public Label totalProfit;
    public Label productionCompanyShow;
    public Label outputLabel2;
    public Label outputLabel3;
    static List<Movie> movielistOfLatestReleaseYear = new ArrayList();
    static List<Movie> movielistOfMaxRevenue = new ArrayList();

    public void onMostRecentMoviesClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("MostRecentMovies.fxml"));
        anchorPaneRight.getChildren().setAll(pane);
    }


    public void onMaximumRevenueClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("MaximumRevenueInput.fxml"));
        anchorPaneRight.getChildren().setAll(pane);

    }

    public void onTotalProfitClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("TotalProfitInput.fxml"));
        anchorPaneRight.getChildren().setAll(pane);
    }

    public void onAllProductionCompanyClick(ActionEvent actionEvent) throws IOException {

    }

    public void onBackToMainClick(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load() , 680, 420 );
        stage.setTitle("Main Menu ");
        stage.setScene(scene);
        stage.show();
    }
    public void onPrintRecentMoviesClick(ActionEvent actionEvent) throws Exception {
        movielistOfLatestReleaseYear = MovieList.MoviesWithLatestReleaseYear(productionCompanyInput1.getText());
        //System.out.println(movielistOfLatestReleaseYear.get(0).getYearOfRelease());
        if(movielistOfLatestReleaseYear.size()==0){
            outputLabel2.setText("No such Movies with this Production Company!");
        }
        else {
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RecentMoviesPrintScreen.fxml"));
            scene = new Scene(fxmlLoader.load(), 680, 420);
            stage.setTitle("Movie Recent Movies by " + productionCompanyInput1.getText());
            stage.setScene(scene);
            //stage.setFullScreen(true);
            stage.show();
        }

    }
    public void onPrintMaxRevenueClick(ActionEvent actionEvent) throws Exception {
        movielistOfMaxRevenue = MovieList.MoviesWithMaxRenevue(productionCompanyInput2.getText());
        if(movielistOfMaxRevenue.size()==0){
            outputLabel3.setText("No such Movies with this Production Company!");
        }
        else {
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MaxRevenuePrintScreen.fxml"));
            scene = new Scene(fxmlLoader.load(), 680, 420);
            stage.setTitle("Max revenue Movies by " + productionCompanyInput2.getText());
            stage.setScene(scene);
            //stage.setFullScreen(true);
            stage.show();
        }
    }
    public void onPrintTotalProfitClick(ActionEvent actionEvent) throws Exception {
        long totalProfitInlong = MovieList.TotalProfitOfMovies(productionCompanyInput3.getText());
        if(totalProfitInlong==0){
            outputLabel.setText("No such Movies with this production company Name");
        }
        else {
            //totalProfit.setText(Long.toString(totalProfitInlong));
           // productionCompanyShow.setText(productionCompanyInput3.getText());
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TotalProfitPrintWindow.fxml"));
            scene = new Scene(fxmlLoader.load(), 680, 420);
            stage.setTitle("Total Profit : ");
            stage.setScene(scene);
            stage.show();
            totalProfit.setText(Long.toString(totalProfitInlong));
            productionCompanyShow.setText(productionCompanyInput3.getText());
        }
    }

}
