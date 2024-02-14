package com.example.demo2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import serverPackage.Server;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

public class HomePageLogin extends Application {
    public TextField productionCompanyInput;
    public static String productionCompany;
    public static Long totalProfit = Long.valueOf(0);
    static List<Movie> listFromServer = new ArrayList<>();
    static List<Movie> basedOnProductionCompanyRevenue = new ArrayList<>();

    static List<Movie> basedOnProductionCompanyReleaseYear = new ArrayList<>();
    public TextField addMovieName, addReleaseYear, addRunningTime, addBudget, addRevenue;
    public static SocketWrapper server;
    public Label addTitleLabel1, addTitleLabel2;
    public Label successfulAddMessage, successfulLoginMessage;
    public TextField transferToPC, movieNameToTransfer;
    public Label successfulTransferMessage, unSuccessfulTransferMessage;
    public AnchorPane anchorPaneLeft;
    public BorderPane myBorderPane;
    public Label movieToShow;
    public PasswordField passwordField;
    public static HashMap<String, String> passwordMap;
    public TextField genreTextField1, genreTextField2, genreTextField3;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = null;
        scene = new Scene(fxmlLoader.load(), 680, 420);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            Application.launch();
        }, "FxmlStartThread").start();

    }

    public void hashmapGenerate() throws IOException {
        server = new SocketWrapper("127.0.0.1", 3000);
        server.write("GetHashmap");
        try {
            Object data = server.read();
            passwordMap = (HashMap<String, String>) data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                server.closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onLoginClick(ActionEvent actionEvent) throws IOException {
        hashmapGenerate();
        productionCompany = productionCompanyInput.getText();
        SocketWrapper server = new SocketWrapper("127.0.0.1", 3000);
        if (passwordField.getText().equals(passwordMap.get(productionCompany))) {
            try {
                server.write(productionCompany);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Object data = server.read();
                listFromServer = (List<Movie>) data;
            } catch (IOException e) {
                System.out.println("Interrupted");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    server.closeConnection();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (productionCompany.equalsIgnoreCase("Save") || listFromServer.size() == 0) {
                successfulLoginMessage.setText("Please enter valid Production Company!");
            } else {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoggedInProductionCompany.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("You are logged in as " + productionCompany);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            successfulLoginMessage.setText("Invalid password ! Please enter again :");
        }
    }

    public void onMaximumRevenueClick(ActionEvent actionEvent) throws IOException {
        autoReload();

        if (listFromServer.size() == 0) {
            movieToShow.setText("No Movie to show!");
        } else {
            basedOnProductionCompanyRevenue = MovieList.MoviesWithMaxRenevue(listFromServer);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MaxRevenuePrintScreen2.fxml"));
            Scene scene = null;

            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Maximum Revenue Movies By " + productionCompany);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onMostRecentMoviesClick(ActionEvent actionEvent) throws IOException {
        autoReload();
        if (listFromServer.size() == 0) {
            movieToShow.setText("No Movie to show !");
        } else {
            basedOnProductionCompanyReleaseYear = MovieList.MoviesWithLatestReleaseYear(listFromServer);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RecentMoviesPrintScreen2.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            stage.setTitle("Most Recent Movies by " + productionCompany);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onTotalProfitClick(ActionEvent actionEvent) throws IOException {
        SocketWrapper server = new SocketWrapper("127.0.0.1", 3000);
        try {
            server.write(productionCompany);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Object data = server.read();
            listFromServer = (List<Movie>) data;
        } catch (IOException e) {
            System.out.println("Interrupted");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                server.closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        totalProfit = MovieList.TotalProfitOfMovies(listFromServer);
        System.out.println(totalProfit);
        Stage profitStage = (Stage) myBorderPane.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(profitStage);
        alert.getDialogPane().setHeaderText("Total Profit By " + productionCompany);
        alert.getDialogPane().setContentText("" + totalProfit);
        alert.showAndWait();
    }

    public void onAddMovieClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddMovies2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 420);
        stage.setTitle("Add Movie");
        stage.setScene(scene);
        stage.show();
    }

    public void OnTransferMovieClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TransferMovies.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 420);
        stage.setTitle("Transfer Movie");
        stage.setScene(scene);
        stage.show();
    }

    public void onLogoutClick(ActionEvent actionEvent) throws IOException {
        SocketWrapper server = new SocketWrapper("127.0.0.1", 3000);
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setTitle("Logging Out!");
        alertBox.setHeaderText("You are about to logout");
        alertBox.setContentText("Do you want to save before logging out?");
        if (alertBox.showAndWait().get() == ButtonType.OK) {
            server.write("Save");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
            stage.setTitle("Login page ");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoggedInProductionCompany.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 420);
        stage.setTitle("You are logged in as " + productionCompany);
        stage.setScene(scene);
        stage.show();
    }

    public void onCheckMovieClick(ActionEvent actionEvent) throws Exception {
        for (int i = 0; i < listFromServer.size(); i++) {
            if (addMovieName.getText().equalsIgnoreCase(listFromServer.get(i).getTitle())) {
                addTitleLabel1.setText("A movie already exists with this title!");
                addTitleLabel2.setText("Please Enter Again ");
                break;
            } else {
                addTitleLabel1.setText("Valid Input");
            }
        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        server = new SocketWrapper("127.0.0.1", 3000);
        String title = null, genre1 = null, genre2 = null, genre3 = null, PC = null;
        int year = 0, runTime = 0, budget = 0, revenue = 0;
        for (int i = 0; i < 1; i++) {
            try {
                title = addMovieName.getText();
                year = Integer.parseInt(addReleaseYear.getText());
                genre1 = genreTextField1.getText();
                genre2 = genreTextField2.getText();
                genre3 = genreTextField3.getText();
                runTime = Integer.parseInt(addRunningTime.getText());
                PC = productionCompany;
                budget = Integer.parseInt(addBudget.getText());
                revenue = Integer.parseInt(addRevenue.getText());
            } catch (Exception e) {
                successfulAddMessage.setText("Please enter valid information in all fields!");
                break;
            }
            Movie movie = new Movie(title, year, genre1, genre2, genre3, runTime, PC, budget, revenue);
            successfulAddMessage.setText("Successfully Added");
            new Thread(() -> {
                try {
                    server.write(movie);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }, "AddMovieWriteToServer").start();
            new Thread(() -> {
                try {
                    Object data = server.read();
                    listFromServer = (List<Movie>) data;
                } catch (IOException e) {
                    System.out.println("Interrupted");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        server.closeConnection();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "AddMovieReadFromServer").start();
        }
    }

    public void onTransferClick(ActionEvent actionEvent) throws IOException {
        server = new SocketWrapper("127.0.0.1", 3000);
        for (int i = 0; i < listFromServer.size(); i++) {
            if ((movieNameToTransfer.getText().equalsIgnoreCase(listFromServer.get(i).getTitle()))) {
                successfulTransferMessage.setText("Successfully Transferred!");
                unSuccessfulTransferMessage.setText("");
                break;
            } else {
                unSuccessfulTransferMessage.setText("No Such Movies in this production Company! Please Enter Again ");
            }
        }
        new Thread(() -> {
            try {
                server.write(new DataWrapper(productionCompany, movieNameToTransfer.getText(), transferToPC.getText()));
            } catch (Exception e) {
                System.out.println(e);
            }
        }, "TransferMovieWriteToServer").start();
        new Thread(() -> {
            try {
                Object data = server.read();
                listFromServer = (List<Movie>) data;
            } catch (IOException e) {
                System.out.println("Interrupted");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    server.closeConnection();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "TransferMovieReadFromServer").start();

    }


    public void onShowAllMoviesClick(ActionEvent actionEvent) throws IOException {
        autoReload();
        if (listFromServer.size() == 0) {
            movieToShow.setText("No Movie to show !");
        } else {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrintOptionForMoviesByProductionCompany.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            stage.setTitle("All movies by " + productionCompany);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void autoReload() throws IOException {
        SocketWrapper server = new SocketWrapper("127.0.0.1", 3000);
        try {
            server.write(productionCompany);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Object data = server.read();
            listFromServer = (List<Movie>) data;
        } catch (IOException e) {
            System.out.println("Interrupted");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                server.closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

