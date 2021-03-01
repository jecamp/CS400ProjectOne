import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class Backend implements BackendInterface {

  private HashTableMap<String, List<MovieInterface>> genresHashed;
  private HashTableMap<Integer, List<MovieInterface>> avgRatingsHashed;
  private List<String> genresAdded;
  private List<String> avgRatingsAdded;
  private List<MovieInterface> allMovies;
  
  
  public Backend(Reader inputReader) {
    MovieDataReaderDummy k = new MovieDataReaderDummy();
    try {
      allMovies = k.readDataSet(inputReader);
    } catch (DataFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    hashGenre();
    hashAvgRatings();
    genresAdded = new ArrayList<String>();
    avgRatingsAdded = new ArrayList<String>();
  }
  
  public Backend(String[] args) throws FileNotFoundException {
    this(new FileReader(args[0]));
  }
  
  private void hashGenre() {
    genresHashed = new HashTableMap<String, List<MovieInterface>>();
    for (int i = 0; i < allMovies.size(); i++) {
      for (int j = 0; j < allMovies.get(i).getGenres().size(); j++) {
        if (!genresHashed.containsKey(allMovies.get(i).getGenres().get(j))) {
          ArrayList<MovieInterface> genre = new ArrayList<MovieInterface>();
          genre.add(allMovies.get(i));
          genresHashed.put(allMovies.get(i).getGenres().get(j), genre);
        } else {
          genresHashed.get(allMovies.get(i).getGenres().get(j)).add(allMovies.get(i));
        }
      }
  }
  }
  
  private void hashAvgRatings() {
    avgRatingsHashed = new HashTableMap<Integer, List<MovieInterface>>();
    for (int i = 0; i < allMovies.size(); i++) {
        if (!avgRatingsHashed.containsKey(allMovies.get(i).getAvgVote().intValue())) {
          ArrayList<MovieInterface> avgRatings = new ArrayList<MovieInterface>();
          avgRatings.add(allMovies.get(i));
          avgRatingsHashed.put(allMovies.get(i).getAvgVote().intValue(), avgRatings);
        } else {
          avgRatingsHashed.get(allMovies.get(i).getAvgVote().intValue()).add(allMovies.get(i));
        }
      }
  }
 
  
  @Override
  public void addGenre(String genre) {
    genresAdded.add(genre);
    
  }

  @Override
  public void addAvgRating(String rating) {
    avgRatingsAdded.add(rating);
  }

  @Override
  public void removeGenre(String genre) {
    genresAdded.remove(genre);
    
  }

  @Override
  public void removeAvgRating(String rating) {
    avgRatingsAdded.remove(rating);
  }

  @Override
  public List<String> getGenres() {
    return genresAdded;
  }

  @Override
  public List<String> getAvgRatings() {
    return avgRatingsAdded;
  }

  
  
  public List<MovieInterface> getAvgRatingsMovies() {
    List<MovieInterface> avgRatingsList = new ArrayList<MovieInterface>();
    for (int i = 0; i < avgRatingsAdded.size(); i++) {
      for (int j = 0; j < avgRatingsHashed.get(Integer.parseInt(avgRatingsAdded.get(i))).size(); j++) {
        avgRatingsList.add(avgRatingsHashed.get(Integer.parseInt(avgRatingsAdded.get(i))).get(j));
      }
    }
    return avgRatingsList;
  }
  
  public List<MovieInterface> getGenresMovies() {
    List<MovieInterface> genresList = new ArrayList<MovieInterface>();
    if (!genresAdded.isEmpty()) {
      for (int l = 0; l < genresHashed.get(genresAdded.get(0)).size(); l++) {
        genresList.add(genresHashed.get(genresAdded.get(0)).get(l));
      }
    }
    for (int i = 1; i < genresAdded.size(); i++) {
      for (int j = 0; j < genresList.size(); j++) {
        if (!genresList.get(j).getGenres().contains(genresAdded.get(i))) {
          genresList.remove(j);
          j--;
        }
      }
    }
    return genresList;
  }
  
  public List<MovieInterface> resultingSet() {
    List<MovieInterface> duplicateList = new ArrayList<>();
    List<MovieInterface> genres = getGenresMovies();
    List<MovieInterface> avgRatings = getAvgRatingsMovies();
    for (int i = 0; i < genres.size(); i++) {
      if (avgRatings.contains(genres.get(i))) {
        duplicateList.add(genres.get(i));
      }
    }
    return duplicateList;
  }
  
  
  
  @Override
  public int getNumberOfMovies() {
    return resultingSet().size();
  }
  

  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    List<MovieInterface> list = resultingSet();
    List<MovieInterface> threeMovies = new ArrayList<MovieInterface>();
    int count = 0;
    while (list.size() > 0 && count < 3) {
      MovieInterface greatest = getGreatest(list);
      list.remove(greatest);
      threeMovies.add(greatest);
      count++;
    }
    return threeMovies;
  }
  
  private MovieInterface getGreatest(List<MovieInterface> list) {
    float greatest = list.get(0).getAvgVote();
    int index = 0;
    for (int i = 1; i < list.size(); i++) {
      if (list.get(i).getAvgVote() > greatest) {
        greatest = list.get(i).getAvgVote();
        index = i;
      }
    }
    return list.get(index);
  }
  
  @Override
  public List<String> getAllGenres() {
    List<String> genresChecked = new ArrayList<String>();
    for (int i = 0; i < allMovies.size(); i++) {
      for (int j = 0; j < allMovies.get(i).getGenres().size(); j++) {
        if (!genresChecked.contains(allMovies.get(i).getGenres().get(j))) {
          genresChecked.add(allMovies.get(i).getGenres().get(j));
        }
      }
    }
    return genresChecked;
  }
  
}
