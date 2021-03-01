// import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class MovieDataReader implements MovieDataReaderInterface {

  List<MovieInterface> answer;
  int numOfLine;
  int propertyLimit;

  public MovieDataReader() {
    answer = new ArrayList<MovieInterface>();
    numOfLine = 1;
    propertyLimit = 6;
  }

  @Override
  public List<MovieInterface> readDataSet(FileReader inputFileReader)
      throws IOException, DataFormatException {
    Scanner sc = new Scanner(inputFileReader);
    // checks if scanner can read the FileReader, throws IOException if it cannot
    try {
      sc.hasNextLine();
    } catch (IOException e1) {
      throw new IOException("Error: file cannot be opened or read!");
    }
    while (sc.hasNextLine()) {
      String line = sc.nextLine(); // obtains the line of single movie, skips the header line
      numOfLine++;
      // regex used to properly split properties with multiple names like directors and genres
      String[] prop = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      // throws dataformatexception if there is more or less properties of 6 which is property limit
      if (prop.length != propertyLimit) {
        throw new DataFormatException(
            "ERROR: Line number " + numOfLine + " has incorrect number of movie properties.");
      }
      String title = prop[0];
      Integer year = Integer.valueOf(prop[1]);
      List<String> genres = reformat(prop[2].replace("\"", "").split(","));
      String director = prop[3];
      String description = prop[4];
      Float avgVote = Float.valueOf(prop[5]);
      // creates new movie based off the properties collected in properties string array
      Movie newMovie = new Movie(title, year, genres, director, description, avgVote);
      answer.add(newMovie); // adds new Movie to the list
    }
    return answer; // returns the movie list
  }

}
