package com.example.demo2;
//import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Movie implements Serializable {
    private String title , genre1 , genre2 , genre3 , productionCompany;
    private int yearOfRelease , runningTime , budget , revenue;
    private long totalProfit;
    public static int movieCount=0;
    public Movie(String title,int yearOfRelease,String genre1,String genre2,String genre3,int runningTime,String productionCompany,int budget,int revenue) {
        this.title=title;
        this.yearOfRelease=yearOfRelease;
        this.genre1=genre1;
        this.genre2=genre2;
        this.genre3=genre3;
        this.runningTime=runningTime;
        this.productionCompany=productionCompany;
        this.budget=budget;
        this.revenue=revenue;
        this.totalProfit=this.revenue-this.budget;
    }
    public void setProductionCompany(String productionCompany){
        this.productionCompany=productionCompany;
    }
    public String getTitle(){
        return this.title;
    }
    public String getGenre1(){
        return this.genre1;
    }
    public String getGenre2(){
        return this.genre2;
    }
    public String getGenre3(){
        return this.genre3;
    }
    public String getProductionCompany(){
        return this.productionCompany;
    }
    public int getYearOfRelease(){
        return this.yearOfRelease;
    }
    public int getRunningTime(){
        return this.runningTime;
    }
    public int getBudget(){
        return this.budget;
    }
    public int getRevenue(){
        return this.revenue;
    }
    public static String displayInformation(Movie movie){
            String movieInfo  = "\n"+ "Movie name : " + movie.getTitle() + ", Date of Release : " + movie.getYearOfRelease()+
                        "Genres : " + movie.getGenre1() + " , " + movie.getGenre2() + " , " + movie.getGenre3() +
                        "Production Company : " + movie.productionCompany +
                        "Run time : " + movie.getRunningTime() + " Minutes , Total Budget : " + movie.getBudget() +
                        " , Total revenue : " + movie.getRevenue()+
                        "\n";

                return movieInfo;
            }

    public static String disPlayInformationForMaxRevenue(Movie movie){
        String infoForRevenue="Movie name : " + movie.getTitle() + ", Revenue : " + movie.getRevenue()+"\n";
        return infoForRevenue;
    }
    public static String disPlayInformationForReleaseYear(Movie movie){

            String infoForReleaseYear="Movie name : " + movie.getTitle() + ", Year of Release: " + movie.getYearOfRelease()+"\n";
            return infoForReleaseYear;
    }
    public long getProfit(){
        return this.totalProfit;
    }
}
