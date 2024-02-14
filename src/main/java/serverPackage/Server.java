package serverPackage;

import com.example.demo2.DataWrapper;
import com.example.demo2.HomePageLogin;
import com.example.demo2.Movie;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Server {
    static List<Movie> movieList = new ArrayList<>();
    public static HashSet<String> hashListOfProductionCompany;
    public static HashMap<String, String> passwordHashmap;

    public static void main(String[] args) throws IOException {
        final String INPUT_FILE_NAME = "movies.txt";
        final String OUTPUT_FILE_NAME = "out.txt";
        ServerSocket server = new ServerSocket(3000);
        System.out.println("Server Started!");
        new Thread(() -> {
            passwordHashmap = new HashMap<String, String>();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (true) {
                String line = null;
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    System.out.println("File operations error!");
                }
                if (line == null) break;
                String[] out = line.split(",");
                movieList.add(new Movie(out[0], Integer.parseInt(out[1]), out[2], out[3], out[4], Integer.parseInt(out[5]), out[6], Integer.parseInt(out[7]), Integer.parseInt(out[8])));
                passwordHashmap.put(out[6], "abcd");
            }
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "Moviereadthread").start();

        while (true) {
            Socket clientSocket = server.accept();
            SocketWrapper client = new SocketWrapper(clientSocket);
            System.out.println("Client connected");
            System.out.println(movieList.size());
            new Thread(() -> {
                try {
                    while (true) {
                        Object data = client.read();
                        if (data instanceof String) {
                            if (((String) data).equalsIgnoreCase("Save")) {
                                FileWriter fileWriter = null;
                                try {
                                    fileWriter = new FileWriter("movies.txt");
                                } catch (IOException e) {
                                    System.out.println("IO operation Error!");
                                    throw new RuntimeException(e);
                                }
                                BufferedWriter bw = new BufferedWriter(fileWriter);
                                for (int i = 0; i < Server.movieList.size(); i++) {
                                    Movie m = movieList.get(i);
                                    String movieInfo = m.getTitle() + "," + m.getYearOfRelease() + "," + m.getGenre1() + "," + m.getGenre2() + "," +
                                            m.getGenre3() + "," + m.getRunningTime() + "," + m.getProductionCompany() + "," +
                                            m.getBudget() + "," + m.getRevenue();
                                    try {
                                        bw.append(movieInfo);
                                    } catch (IOException e) {
                                        System.out.println("IO operation error!");
                                        throw new RuntimeException(e);
                                    }
                                    try {
                                        bw.write(System.lineSeparator());
                                    } catch (IOException e) {
                                        System.out.println("IO operation error!");
                                        throw new RuntimeException(e);
                                    }
                                }
                                try {
                                    bw.close();
                                } catch (IOException e) {
                                    System.out.println("IO operation Error!");
                                    throw new RuntimeException(e);
                                }
                            } else if (((String) data).equalsIgnoreCase("GetHashmap")) {
                                client.write(passwordHashmap);
                            } else {
                                String productionCompany = (String) data;
                                System.out.println("Received : " + productionCompany);
                                List<Movie> searchedList = new ArrayList<>();
                                for (int i = 0; i < movieList.size(); i++) {
                                    if (movieList.get(i).getProductionCompany().equalsIgnoreCase(productionCompany)) {
                                        searchedList.add(movieList.get(i));
                                    }
                                }
                                client.write(searchedList);
                            }
                        } else if (data instanceof Movie) {
                            Movie m = (Movie) data;
                            m.setProductionCompany(m.getProductionCompany());
                            movieList.add(m);
                            List<Movie> searchedList = new ArrayList<>();
                            for (int i = 0; i < movieList.size(); i++) {
                                if (movieList.get(i).getProductionCompany().equalsIgnoreCase(m.getProductionCompany())) {
                                    searchedList.add(movieList.get(i));
                                }
                            }
                            client.write(searchedList);
                        } else if (data instanceof DataWrapper) {
                            List<Movie> searchedList = new ArrayList<>();
                            DataWrapper dataWrapper = (DataWrapper) data;
                            System.out.println("In if else of DaraWrapper " + dataWrapper.fromProductionCompany);
                            System.out.println(dataWrapper.movieToTransfer);
                            System.out.println(dataWrapper.toProductionCompany);
                            for (int i = 0; i < movieList.size(); i++) {
                                if (movieList.get(i).getTitle().equalsIgnoreCase(dataWrapper.movieToTransfer)) {
                                    movieList.get(i).setProductionCompany(dataWrapper.toProductionCompany);
                                }
                            }
                            for (int i = 0; i < movieList.size(); i++) {
                                if (movieList.get(i).getProductionCompany().equalsIgnoreCase(dataWrapper.fromProductionCompany)) {
                                    searchedList.add(movieList.get(i));
                                }
                            }
                            client.write(searchedList);
                        }

                    }


                } catch (IOException e) {
                    //System.out.println("Connection Ended!");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }, "SeverReadWriteThread").start();
        }
    }

}
