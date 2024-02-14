package com.example.demo2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class mainClass {
    public static void main(String argvs[]) throws Exception {
        MovieList.fileOperations();
        Scanner scanInt = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
            while (true) {
                System.out.println("Main menu");
                System.out.println("1.Search Movies ");
                System.out.println("2.Search production Companies ");
                System.out.println("3.Add Movies");
                System.out.println("4.Exit");
                System.out.println("Your Choice : ");
                String choice = scanString.nextLine();
                int stringToInt= Integer.parseInt(choice);
                if (stringToInt == 4) {
                    break;
                }
                else {
                    switch (choice) {
                        case "1":
                            while (true) {
                                System.out.println("Movie searching option ");
                                System.out.println("1.By Movie Title");
                                System.out.println("2.By Release Year");
                                System.out.println("3.By Genre");
                                System.out.println("4.By Production Company");
                                System.out.println("5.By Running time");
                                System.out.println("6.Top 10 Movies");
                                System.out.println("7.Back to main menu");
                                System.out.println("Enter Your choice : ");
                                String moviesChoice = scanString.nextLine();
                                if (Integer.parseInt(moviesChoice) == 7) {
                                    break;
                                } else {
                                    switch (moviesChoice) {
                                        case "1":
                                            System.out.println("Enter a movie name : ");
                                            String movieName = scanString.nextLine();
                                            List<Movie> movielistforMovieName = new ArrayList();
                                            movielistforMovieName = MovieList.searchByMovieName(movieName);
                                            //Movie.displayInformation(movielistforMovieName, "movie name");
                                            break;
                                        case "2":
                                            int year;
                                            System.out.println("Enter a year : ");
                                            year = scanInt.nextInt();
                                            List<Movie> movielistForYear = new ArrayList();
                                            movielistForYear = MovieList.searchByYear(year);
                                            //Movie.displayInformation(movielistForYear, "year");
                                            break;
                                        case "3":
                                            System.out.println("Enter Your Genre : ");
                                            String genre = scanString.nextLine();
                                            List<Movie> movielistForGenre = new ArrayList();
                                            movielistForGenre = MovieList.searchByGenre(genre);
                                            //Movie.displayInformation(movielistForGenre, "genre");
                                            break;
                                        case "4":
                                            System.out.println("Enter Your production Company : ");
                                            String productionCompany = scanString.nextLine();
                                            List<Movie> movielistForProductionCompany = new ArrayList();
                                            movielistForProductionCompany = MovieList.searchByProductionCompany(productionCompany);
                                            //Movie.displayInformation(movielistForProductionCompany, "production company");
                                            break;
                                        case "5":
                                            int range1, range2;
                                            System.out.println("Enter two ranges of running time (in minutes) : ");
                                            range1 = scanInt.nextInt();
                                            range2 = scanInt.nextInt();
                                            List<Movie> movieListForRunningTime = new ArrayList();
                                            movieListForRunningTime = MovieList.searchByRunningTime(range1, range2);
                                            //Movie.displayInformation(movieListForRunningTime, "range");
                                            break;
                                        case "6":
                                            List<Movie> topTenMoviesList = new ArrayList();
                                            topTenMoviesList = MovieList.topTenMovies();
                                            //Movie.displayInformation(topTenMoviesList, "");
                                            break;
                                        default:
                                            System.out.println("Not in the options!");
                                            break;
                                    }
                                }
                            }
                            break;
                        case "2":
                            while (true) {
                                System.out.println("Production Company searching options : ");
                                System.out.println("1.Most recent Movies");
                                System.out.println("2.Movies With Maximum revenues");
                                System.out.println("3.Total Profit");
                                System.out.println("4.List of production Companies And Count of their Produced Movies");
                                System.out.println("5.Back To Main Menu");
                                System.out.println("Enter Your choice : ");
                                String productionCompanyChoice = scanString.nextLine();
                                if (Integer.parseInt(productionCompanyChoice) == 5) {
                                    break;
                                } else {
                                    String productionCompanyName;
                                    switch (productionCompanyChoice) {
                                        case "1":
                                            System.out.println("Enter Your production Company : ");
                                            productionCompanyName = scanString.nextLine();
                                            List<Movie> movielistOfLatestReleaseYear = new ArrayList();
                                            System.out.println("Most Recent Movie(s) By " + productionCompanyName + " : ");
                                            movielistOfLatestReleaseYear = MovieList.MoviesWithLatestReleaseYear(productionCompanyName);
                                            //Movie.disPlayInformationForReleaseYear(movielistOfLatestReleaseYear);
                                            break;
                                        case "2":
                                            System.out.println("Enter Your production Company : ");
                                            productionCompanyName = scanString.nextLine();
                                            System.out.println("Movie(s) with Most Revenue By " + productionCompanyName + " : ");
                                            List<Movie> movielistOfMaxRevenue = new ArrayList();
                                            movielistOfMaxRevenue = MovieList.MoviesWithMaxRenevue(productionCompanyName);
                                            //Movie.disPlayInformationForMaxRevenue(movielistOfMaxRevenue);
                                            break;
                                        case "3":
                                            System.out.println("Enter Your production Company : ");
                                            productionCompanyName = scanString.nextLine();
                                            long totalProfit = MovieList.TotalProfitOfMovies(productionCompanyName);
                                            if (totalProfit == 0) {
                                                System.out.println("No such Production Company found");
                                            } else {
                                                System.out.println("Total profit by " + productionCompanyName + " is " + totalProfit + " Usd ");
                                            }
                                            break;
                                        case "4":
                                            MovieList.AllProductionCompanies();
                                            break;
                                        default:
                                            System.out.println("Not in the options!");
                                            break;
                                    }
                                }
                            }
                            break;
                        case "3":
                            MovieList.addMovies();
                            break;
                        default: {
                            System.out.println("Error! Not in the Options.");
                        }
                        break;
                    }

                }

            }

        }
        /*catch (NumberFormatException e){
            System.out.println("Exception caught!");
        }*/

}
