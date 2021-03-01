import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

public class Movie implements MovieInterface {

  // instance fields
  String title;
  Integer year;
  List<String> genres;
  String director;
  String description;
  Float avgVote;

  // default constructor
  public Movie(String title, Integer year, List<String> genres, String director, String description,
      Float avgVote) {
    this.title = title;
    this.year = year;
    this.genres = genres;
    this.director = director;
    this.description = description;
    this.avgVote = avgVote;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public Integer getYear() {
    return year;
  }

  @Override
  public List<String> getGenres() {
    return genres;
  }

  @Override
  public String getDirector() {
    return director;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Float getAvgVote() {
    return avgVote;
  }

  @Override
  /**
   * toString method meant to provide structured version of the movie with all attributes for the
   * front end developer
   */
  public String toString() {
    return "Title: " + title + " Year: " + year + " Genre: " + genres + " Director: " + director
        + " Description: " + description + " Average Rating: " + avgVote;
  }

  @Override
  public int compareTo(MovieInterface otherMovie) {
    // case if same movies are being compared
    if (this.getTitle().contentEquals(otherMovie.getTitle())) {
      return 0;
      // case if otherMovie has a higher average vote
    } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
      return 1;
    }
    // case if this movie had a higher average vote
    return -1;
  }

}
