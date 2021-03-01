// --== CS400 File Header Information ==--
// Name: Kyle Sung
// Email: kesung2@wisc.edu
// Team: Red
// Role: Data Wrangler
// TA: Xinyi Liu
// Lecturer: Florian Heimerl
// Notes to Grader: Have a great day :)

import java.util.List;

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
  /**
   * Getter method for title
   * 
   * @return String title
   */
  public String getTitle() {
    return title;
  }

  @Override
  /**
   * Getter method for year
   * 
   * @return Integer year
   */
  public Integer getYear() {
    return year;
  }

  @Override
  /**
   * Getter method for year
   * 
   * @return List<String> of genres
   */
  public List<String> getGenres() {
    return genres;
  }

  @Override
  /**
   * Getter method for director
   * 
   * @return String director
   */
  public String getDirector() {
    return director;
  }

  @Override
  /**
   * Getter method for description
   * 
   * @return String description
   */
  public String getDescription() {
    return description;
  }

  @Override
  /**
   * Getter method for average vote
   * 
   * @return Float average vote
   */
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
  /**
   * CompareTo method that compares 2 movie objects based on the average votes
   */
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
