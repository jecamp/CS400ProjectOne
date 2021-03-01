// import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class MovieDataReader implements MovieDataReaderInterface {

  List<MovieInterface> answer;
  int numOfLine;
  int propertyLimit;
  int titleIndex;
  int yearIndex;
  int genreIndex;
  int directorIndex;
  int descriptionIndex;
  int avgVoteIndex;

  public MovieDataReader() {
    answer = new ArrayList<MovieInterface>();
    numOfLine = 1;
  }

  @Override
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws IOException, DataFormatException {
    // Scanner sc = new Scanner(inputFileReader);
    BufferedReader br = new BufferedReader(inputFileReader);
    String line;
    int titleIndex = 0;
    int yearIndex = 0;
    int genreIndex = 0;
    int directorIndex = 0;
    int descriptionIndex = 0;
    int avgVoteIndex = 0;
    // checks if scanner can read the FileReader, throws IOException if it cannot
    try {
      String l = br.readLine(); // reads the header line
      String[] heading = l.split(",");
      propertyLimit = heading.length;
      // for loop that loops through the header line and finds the index number of each property
      for (int i = 0; i < heading.length; i++) {
        String cur = heading[i];
        if (cur.equals("title")) {
          titleIndex = i;
        }
        if (cur.equals("year")) {
          yearIndex = i;
        }
        if (cur.equals("genre")) {
          genreIndex = i;
        }
        if (cur.equals("director")) {
          directorIndex = i;
        }
        if (cur.equals("description")) {
          descriptionIndex = i;
        }
        if (cur.equals("avg_vote")) {
          avgVoteIndex = i;
        }
      }
    } catch (IOException e1) {
      throw new IOException("Error: file cannot be opened or read!");
    }
    while ((line = br.readLine()) != null) {
      numOfLine++;
      // regex used to properly split properties with multiple names like directors and genres
      String[] prop = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      // throws dataformatexception if there is more or less properties of 6 which is property limit
      if (prop.length != propertyLimit) {
        throw new DataFormatException(
            "ERROR: Line number " + numOfLine + " has incorrect number of movie properties.");
      }
      String title = prop[titleIndex];
      Integer year = Integer.valueOf(prop[yearIndex]);
      // this removes the quotes remaining after splitting and splits each genre by comma to put
      // into the list
      List<String> genres = transformGenre(prop[genreIndex]);
      String director = transformDirectors(prop[directorIndex]);
      String description = prop[descriptionIndex];
      Float avgVote = Float.valueOf(prop[avgVoteIndex]);
      // creates new movie based off the properties collected in properties string array
      Movie newMovie = new Movie(title, year, genres, director, description, avgVote);
      answer.add(newMovie); // adds new Movie to the list
    }
    return answer; // returns the movie list
  }

  /**
   * This method reformats the string of genres into a List of genres
   * 
   * @param genres
   * @return reformted list of genre
   */
  private List<String> transformGenre(String genres) {
    List<String> answer = new ArrayList<String>();
    genres = genres.replace("\"", ""); // removes the extra quotes around multiple genres
    // splits the genres based off commas + space so it prevents double spacing in the final output
    String[] splitGenres = genres.split(", ");
    for (int i = 0; i < splitGenres.length; i++) {
      answer.add(splitGenres[i]); // adds each genre split by commas into the arrayList
    }
    return answer;
  }

  /**
   * This method reformats the string of directors by removing double quotes from multiple directors
   * 
   * @param directors
   * @return reformatted string of directors
   */
  private String transformDirectors(String directors) {
    directors = directors.replace("\"", ""); // removes extra quotes around multiple directors
    return directors;
  }

}
