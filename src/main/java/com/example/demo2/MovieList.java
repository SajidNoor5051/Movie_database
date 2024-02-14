package com.example.demo2;
//import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MovieList {
        static List<Movie> movieList = new ArrayList();
        static int maxrevenue;
        static int latestReleaseYear;
    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "out.txt";
    public static void fileOperations() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] out=line.split(",");
            movieList.add(new Movie(out[0],Integer.parseInt(out[1]),out[2],out[3],out[4],Integer.parseInt(out[5]),out[6],Integer.parseInt(out[7]),Integer.parseInt(out[8])));
            bw.write(line);
            bw.write(System.lineSeparator());
        }
        br.close();
        bw.close();
    }
    public static List<Movie> searchByYear(int year){
        List<Movie> movieListForYear = new ArrayList();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getYearOfRelease()==year) {
                movieListForYear.add(movieList.get(i));
            }
        }
        return movieListForYear;
    }
    public static List<Movie> searchByMovieName(String movieName){
        List<Movie> movieListForMovieName = new ArrayList();
        for (int i = 0; i < movieList.size(); i++) {
            Movie t = movieList.get(i);
            if (movieList.get(i).getTitle().equalsIgnoreCase(movieName)) {
                movieListForMovieName.add(movieList.get(i));
            }
        }
        return movieListForMovieName;
    }
    public static List<Movie> searchByGenre(String genre){
        List<Movie> movieListForGenre = new ArrayList();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getGenre1().equalsIgnoreCase(genre) || movieList.get(i).getGenre2().equalsIgnoreCase(genre) || movieList.get(i).getGenre3().equalsIgnoreCase(genre)) {
                movieListForGenre.add(movieList.get(i));
            }
        }
        return movieListForGenre;
    }
    public static List<Movie> searchByProductionCompany(String productionCompany){
        List<Movie> movieListForProductioncomapy = new ArrayList();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getProductionCompany().equalsIgnoreCase(productionCompany)) {
                movieListForProductioncomapy.add(movieList.get(i));
            }
        }
        return  movieListForProductioncomapy;
    }
    public static List<Movie> searchByRunningTime(int range1,int range2) {
        List<Movie> movieListForRunningTime = new ArrayList();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getRunningTime() >= range1 && movieList.get(i).getRunningTime() <= range2) {
                movieListForRunningTime.add(movieList.get(i));
            }
        }
        return movieListForRunningTime;
    }
    public static List<Movie> topTenMovies(){
        List<Movie> topTenMoviesList = new ArrayList();
        Movie []t=new Movie[movieList.size()];
        for (int i = 0; i < movieList.size(); i++) {
            t[i]=movieList.get(i);
        }
        for(int i=0 ; i<movieList.size();i++){
            for (int j=i+1 ; j<movieList.size() ;j++){
                if(t[i].getProfit()<t[j].getProfit()){
                    Movie tempM=t[i];
                    t[i]=t[j];
                    t[j]=tempM;
                }
            }
        }
        for(int i=0 ; i<10;i++){
            topTenMoviesList.add(t[i]);
        }
        return topTenMoviesList;
    }
    public static List<Movie> MoviesWithMaxRenevue(String productionCompany) {
        int count = 0;
        int index1=0;
        int index2=0;
        List<Movie> movielistOfMaxRevenue = new ArrayList();
        Movie[] t = new Movie[movieList.size()];
        for(int i=0 ; i<movieList.size() ; i++ ){
            t[i]=movieList.get(i);
        }
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getProductionCompany().equalsIgnoreCase(productionCompany)) {
                t[count++] = movieList.get(i);
            }
        }
        maxrevenue = t[index2].getRevenue();
        for (int i = 0; i < movieList.size(); i++) {
            for (int j = 1; j < count; j++) {
                if (t[j].getRevenue() > maxrevenue) {
                    maxrevenue = t[j].getRevenue();
                    index2 = j;
                }
            }
        }
            for (int k = 0; k < count; k++) {
                if (t[k].getRevenue() == maxrevenue) {
                    movielistOfMaxRevenue.add(t[k]);
                }
            }
            return movielistOfMaxRevenue;
        }
        public static List<Movie> MoviesWithMaxRenevue(List<Movie> newList){
            List<Movie> movielistOfMaxRevenue = new ArrayList();
            int index=0;
            maxrevenue = newList.get(0).getRevenue();
            for(int i=1 ; i<newList.size() ; i++){
                if(newList.get(i).getRevenue()> maxrevenue){
                    maxrevenue=newList.get(i).getRevenue();
                    index=i;
                }
            }
            for(int k=0 ; k<newList.size() ; k++){
                if(newList.get(k).getRevenue()==maxrevenue){
                    movielistOfMaxRevenue.add(newList.get(k));
                }
            }
            return movielistOfMaxRevenue;
        }
    public static List<Movie> MoviesWithLatestReleaseYear(List<Movie> newList){
        List<Movie> movielistOfLatestReleaseYEar = new ArrayList();
        int index=0;
        latestReleaseYear = newList.get(0).getYearOfRelease();
        for(int i=1 ; i<newList.size() ; i++){
            if(newList.get(i).getYearOfRelease()> latestReleaseYear){
                latestReleaseYear=newList.get(i).getYearOfRelease();
                index=i;
            }
        }
        for(int k=0 ; k<newList.size() ; k++){
            if(newList.get(k).getYearOfRelease()==latestReleaseYear){
                movielistOfLatestReleaseYEar.add(newList.get(k));
            }
        }
        return movielistOfLatestReleaseYEar;
    }
    public static List<Movie> MoviesWithLatestReleaseYear(String productionCompany){
        int count=0;
        int index1=0 ,index2=0;
        List<Movie> movielistOfLatestReleaseYEar = new ArrayList();
        Movie []t=new Movie[movieList.size()];
        for(int i=0 ; i<movieList.size() ; i++ ){
            t[i]=movieList.get(i);
        }
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getProductionCompany().equalsIgnoreCase(productionCompany)) {
                t[count++] = movieList.get(i);
            }
        }
        latestReleaseYear=t[index2].getYearOfRelease();
        for (int i = 0; i < movieList.size(); i++) {
            for (int j = 1; j < count; j++) {
                if(t[j].getYearOfRelease()>latestReleaseYear){
                    latestReleaseYear=t[j].getYearOfRelease();
                    index2=j;
                }
            }
        }
            for(int k=0 ; k<count ; k++){
                if(t[k].getYearOfRelease()==latestReleaseYear){
                    movielistOfLatestReleaseYEar.add(t[k]);
                }
            }
            return movielistOfLatestReleaseYEar;
        }
    public static long TotalProfitOfMovies(String productionCompany){
        int count=0;
        long totalProfit=0;
        for (int i = 0; i < movieList.size(); i++) {
            Movie t = movieList.get(i);
            if (t.getProductionCompany().equalsIgnoreCase(productionCompany)) {
                    totalProfit=totalProfit+t.getProfit();
            }
        }
        return totalProfit;
    }
    public static long TotalProfitOfMovies(List<Movie> newList){
        long totalProfit=0;
        for (int i = 0; i < newList.size(); i++) {
                totalProfit=totalProfit+newList.get(i).getProfit();
        }
        return totalProfit;
    }
    public static void AllProductionCompanies(){
        String []array=new String[movieList.size()];
        int moviecount=0;
        int arrayIndex=0;
        boolean inarray=false;
      for(int i=0 ; i<movieList.size(); i++){
          for(int j=0 ; j<(arrayIndex+1); j++) {
                  if(movieList.get(i).getProductionCompany().equalsIgnoreCase(array[j])){
                      inarray=true;
                  }
              }
          if(inarray){
              inarray=false;
              continue;
          }
          else {
              for (int k = 0; k < movieList.size(); k++) {
                  if (movieList.get(i).getProductionCompany().equalsIgnoreCase(movieList.get(k).getProductionCompany())) {
                      moviecount++;
                  }
              }
          }
            array[arrayIndex]=movieList.get(i).getProductionCompany();
            arrayIndex++;
            System.out.println("Production Company : "+ movieList.get(i).getProductionCompany()+ " , Number of movies : "+moviecount);
            moviecount=0;
      }
    }
    public static void addMovies() throws Exception{
        String movieName,genres,productionCompany,yearOfRelease , runningTime , budget , revenue;
        FileWriter fileWritter = new FileWriter("movies.txt",true);
        BufferedWriter bw = new BufferedWriter(fileWritter);
        Scanner string= new Scanner(System.in);
        int count=0;
        //while(true) {
            System.out.println("Enter Name of the Movie : ");
            movieName = string.nextLine();
            for (int i = 0; i < movieList.size(); i++) {
                if (movieList.get(i).getTitle().equalsIgnoreCase(movieName)) {
                    System.out.println("A movie exists already With this Name !");
                    count=0;
                    break;
                }
                else{
                    count++;
                }
            }
        if(count==0){
            return;
        }
        System.out.println("Enter Year of Release : ");
        yearOfRelease=string.nextLine();
        System.out.println("Enter genres(PLease Enter At least one genre. You can add maximum 3 genres. Input format Example: Genre1,Genre2,Genre3): ");
        genres=string.nextLine();
        System.out.println("Enter Running time (By minutes) : ");
        runningTime=string.nextLine();
        System.out.println("Enter Production Company Name: ");
        productionCompany=string.nextLine();
        System.out.println("Enter Budget : ");
        budget=string.nextLine();
        System.out.println("Enter Revenue: ");
        revenue=string.nextLine();
        String MovieInfo=movieName+","+yearOfRelease+","+genres+","+runningTime+","+productionCompany+","+budget+","+revenue;
        String [] out=MovieInfo.split(",");
        movieList.add(new Movie(out[0],Integer.parseInt(out[1]),out[2],out[3],out[4],Integer.parseInt(out[5]),out[6],Integer.parseInt(out[7]),Integer.parseInt(out[8])));
        bw.write(MovieInfo);
        bw.write(System.lineSeparator());
        bw.close();
    }
}
