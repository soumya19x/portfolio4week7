package app;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

    private static final String DATABASE = "jdbc:sqlite:database/Movies.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT * FROM movie";
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                Movie movie = new Movie();
                movie.id = results.getInt("mvnumb");
                movie.name = results.getString("mvtitle");
                movie.year = results.getInt("yrmde");
                movie.genre = results.getString("mvtype");
                movies.add(movie);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return movies;
    }

    public ArrayList<Movie> getMoviesByType(String movieType) {
        ArrayList<Movie> movies = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT * FROM movie WHERE mvtype = '" + movieType + "'";
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                Movie movie = new Movie();
                movie.id = results.getInt("mvnumb");
                movie.name = results.getString("mvtitle");
                movie.year = results.getInt("yrmde");
                movie.genre = results.getString("mvtype");
                movies.add(movie);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return movies;
    }
}
