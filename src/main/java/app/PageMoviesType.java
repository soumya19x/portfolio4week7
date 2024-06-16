package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageMoviesType implements Handler {

    public static final String URL = "/moviestype.html";

    private static final String TEMPLATE = "moviestype.html";

    @Override
    public void handle(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Movies by Type");

        ArrayList<String> types = new ArrayList<>();
        types.add("HORROR");
        model.put("types", types);

        JDBCConnection jdbc = new JDBCConnection();

        String movietype_drop = context.formParam("movietype_drop");
        if (movietype_drop == null) {
            model.put("title_drop", "No Results to show for dropdown");
            model.put("movies_drop", new ArrayList<String>());
        } else {
            model.put("title_drop", movietype_drop + " Movies");
            ArrayList<Movie> movies = jdbc.getMoviesByType(movietype_drop);
            model.put("movies_drop", extractMovieTitles(movies));
        }

        String movietype_textbox = context.formParam("movietype_textbox");
        if (movietype_textbox == null || movietype_textbox.isEmpty()) {
            model.put("title_text", "No Results to show for textbox");
            model.put("movies_text", new ArrayList<String>());
        } else {
            model.put("title_text", movietype_textbox + " Movies");
            ArrayList<Movie> movies = jdbc.getMoviesByType(movietype_textbox);
            model.put("movies_text", extractMovieTitles(movies));
        }

        context.render(TEMPLATE, model);
    }

    private ArrayList<String> extractMovieTitles(ArrayList<Movie> movies) {
        ArrayList<String> titles = new ArrayList<>();
        for (Movie movie : movies) {
            titles.add(movie.name);
        }
        return titles;
    }
}
