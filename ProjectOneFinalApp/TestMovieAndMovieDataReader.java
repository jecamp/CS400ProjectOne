// --== CS400 File Header Information ==--
// Name: Kyle Sung
// Email: kesung2@wisc.edu
// Team: Red
// Role: Data Wrangler
// TA: Xinyi Liu
// Lecturer: Florian Heimerl
// Notes to Grader: Have a great day :)

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMovieAndMovieDataReader {
  public static void main(String[] args) {
    (new TestMovieAndMovieDataReader()).runTests();
    // System.out.println("Hello World");
  }

  /**
   * This method calls all of the test methods in the class and ouputs pass / fail for each test.
   * RUNNER METHOD
   */
  public void runTests() {
    // instantiate reader to test once it is implemented
    MovieDataReader readerToTest = new MovieDataReader(); // new MovieDataReader();

    // add all tests to this method
    if (this.testReaderNumberOfMovies()) {
      System.out.println("Test number of movies: PASSED");
    } else {
      System.out.println("Test number of movies: FAILED");
    }
    if (this.testReaderMovieTitles()) {
      System.out.println("Test movie titles: PASSED");
    } else {
      System.out.println("Test movie titles: FAILED");
    }
    if (this.testMovieOrder()) {
      System.out.println("Test movie order: PASSED");
    } else {
      System.out.println("Test movie order: FAILED");
    }
    if (this.testReaderMovieDirector()) {
      System.out.println("Test movie directors: PASSED");
    } else {
      System.out.println("Test movie directors: FAILED");
    }
    if (this.testReaderMovieGenre()) {
      System.out.println("Test movie genres: PASSED");
    } else {
      System.out.println("Test movie genres: FAILED");
    }
  }

  /**
   * This test reads in 3 movies and tests whether the list of movies returned is of size 3. It
   * fails if the size is not 3 or if an exception occurs while reading in the movies.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testReaderNumberOfMovies() {
    List<MovieInterface> movieList;
    try {
      MovieDataReader readerToTest = new MovieDataReader(); // new MovieDataReader();
      movieList = readerToTest.readDataSet(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    if (movieList.size() == 3) {
      // test passed
      return true;
    } else {
      // test failed
      return false;
    }
  }

  /**
   * This test reads in 3 movies and tests whether the list of movies contains all 3 titles of the
   * source data in any order. It fails if the list returned is missing one or more titles or if an
   * exception occurs while reading in the data.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testReaderMovieTitles() {
    List<MovieInterface> movieList;
    try {
      MovieDataReader readerToTest = new MovieDataReader(); // new MovieDataReader();
      movieList = readerToTest.readDataSet(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    String title1 = "The Source of Shadows";
    String title2 = "The Insurrection";
    String title3 = "Valley Girl";
    boolean equalOne = true;
    // check if first movie is has of the above titles
    equalOne = equalOne
        && (title1.equals(movieList.get(0).getTitle()) || title2.equals(movieList.get(0).getTitle())
            || title3.equals(movieList.get(0).getTitle()));
    // check if second movie is has of the above titles
    equalOne = equalOne
        && (title1.equals(movieList.get(1).getTitle()) || title2.equals(movieList.get(1).getTitle())
            || title3.equals(movieList.get(1).getTitle()));
    // check if third movie is has of the above titles
    equalOne = equalOne
        && (title1.equals(movieList.get(2).getTitle()) || title2.equals(movieList.get(2).getTitle())
            || title3.equals(movieList.get(2).getTitle()));
    // true if the three movies have the right titles
    return equalOne;
  }

  /**
   * This test reads in 3 movies, then sorts them. It then checks whether the default sorting order
   * is descending based on the average ratings.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testMovieOrder() {
    List<MovieInterface> movieList;
    try {
      MovieDataReader readerToTest = new MovieDataReader(); // new MovieDataReader();
      movieList = readerToTest.readDataSet(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    Collections.sort(movieList);
    double lastRating = 11.0;
    for (MovieInterface movie : movieList) {
      if (movie.getAvgVote() > lastRating) {
        // test fails
        return false;
      }
      lastRating = movie.getAvgVote();
    }
    // test passes
    return true;
  }

  // TODO: Data Wrangler, add at least 2 more tests

  /**
   * This test reads in 3 movies and tests whether the list of movies contains all 3 directors of
   * movies in any order. It fails if the list returned is missing one or more director names or if
   * an exception occurs while reading in the data.
   * 
   * @return true if the test passed, fails if it failed
   */
  public boolean testReaderMovieDirector() {
    List<MovieInterface> movieList;
    try {
      MovieDataReader readerToTest = new MovieDataReader(); // new MovieDataReader();
      movieList = readerToTest.readDataSet(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));

    } catch (Exception e1) {
      e1.printStackTrace();
      return false;
    }
    /**
     * CHANGE MADE TO ORIGINAL TEST CASE
     * 
     * change was made so getting directors just outputs directors without extra quotes and slashes
     * removes double quotes from test case so mathcing output is in the correct form we want
     */
    String director1 = "Ryan Bury, Jennifer Bonior";
    String director2 = "Rene Perez";
    String director3 = "Rachel Lee Goldenberg";

    boolean a = false;
    boolean b = false;
    boolean c = false;

    // checks if the first movie in the list contains one of the listed directors
    if (director1.equals(movieList.get(0).getDirector())
        || director2.equals(movieList.get(0).getDirector())
        || director3.equals(movieList.get(0).getDirector())) {
      a = true;
    }
    // checks if the second movie in the list contains one of the listed directors
    if (director1.equals(movieList.get(1).getDirector())
        || director2.equals(movieList.get(1).getDirector())
        || director3.equals(movieList.get(1).getDirector())) {
      b = true;
    }
    // checks if the third movie in the list contains one of the listed directors
    if (director1.equals(movieList.get(2).getDirector())
        || director2.equals(movieList.get(2).getDirector())
        || director3.equals(movieList.get(2).getDirector())) {
      c = true;
    }
    // if all boolean variables were changed to true, passed all tests and return true
    if (!(a && b && c)) {
      return false;
    }
    return true;
  }

  /**
   * This test reads 3 movies and tests whether the list of movies contains the correct genres of
   * the movies in any order. It fails if the list returned is missing one or more genres or if an
   * exception occurs while reading the data.
   * 
   * @return true if the test passed, fails if it failed
   */
  public boolean testReaderMovieGenre() {
    List<MovieInterface> movieList;
    try {
      MovieDataReader readerToTest = new MovieDataReader(); // new MovieDataReader();
      movieList = readerToTest.readDataSet(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e1) {
      e1.printStackTrace();
      return false;
    }
    /**
     * CHANGE MADE TO ORIGINAL TEST CASE
     * 
     * change was made so that the output is a List of string genres instead of simply string and
     * double quotes was also removed from the test case so the correct form is maintained
     */
    List<String> genre1 = new ArrayList<String>();
    List<String> genre2 = new ArrayList<String>();
    List<String> genre3 = new ArrayList<String>();

    genre1.add("Horror");
    genre2.add("Action");
    genre3.add("Comedy");
    genre3.add("Musical");
    genre3.add("Romance");

    boolean a = false;
    boolean b = false;
    boolean c = false;

    // checks if the first movie in the list contains one of the above genres
    if (genre1.equals(movieList.get(0).getGenres())) {
      a = true;
    }
    // checks if the second movie in the list contains one of the above genres
    if (genre2.equals(movieList.get(1).getGenres())) {
      b = true;
    }
    // checks if the third movie in the list contains one of the above genres
    if (genre3.equals(movieList.get(2).getGenres())) {
      c = true;
    }
    if (!(a && b && c)) {
      return false;
    }
    return true;
  }
}
