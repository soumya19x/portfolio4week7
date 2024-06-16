package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageMoviesList implements Handler {

    public static final String URL = "/movies.html";

    private static final String TEMPLATE = "movies.html";

    @Override
    public void handle(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "All Movies in the Database");

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Movie> movies = jdbc.getMovies();
        ArrayList<String> titles = new ArrayList<>();
        for (Movie movie : movies) {
            titles.add(movie.name);
        }
        model.put("movies", titles);

        context.render(TEMPLATE, model);
    }
}
