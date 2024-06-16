package app;

public class Movie {
   public int id;
   public String name;
   public int year;
   public String genre;

   public Movie() {
   }

   public Movie(int id, String name, int year, String genre) {
      this.id = id;
      this.name = name;
      this.year = year;
      this.genre = genre;
   }
}
