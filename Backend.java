import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Backend implements BackendInterface {

  private HashTableMap<String, List<MovieInterface>> genresHashed;
  private HashTableMap<Float, List<MovieInterface>> avgRatingsHashed;
  private List<String> genresAdded;
  private List<String> avgRatingsAdded;
  List<MovieInterface> allMovies;
  
  
  public Backend(StringReader reader) {
    //Work on getting the list of movies
    MovieDataReaderDummy k = new MovieDataReaderDummy();
    try {
      allMovies = k.readDataSet(reader);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    hashGenre();
    hashAvgRatings();
    genresAdded = new ArrayList<String>();
    avgRatingsAdded = new ArrayList<String>();
  }
  
  public Backend(String[] args) {
    
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
    avgRatingsHashed = new HashTableMap<Float, List<MovieInterface>>();
    for (int i = 0; i < allMovies.size(); i++) {
        if (!avgRatingsHashed.containsKey(allMovies.get(i).getAvgVote())) {
          ArrayList<MovieInterface> avgRatings = new ArrayList<MovieInterface>();
          avgRatings.add(allMovies.get(i));
          avgRatingsHashed.put(allMovies.get(i).getAvgVote(), avgRatings);
        } else {
          avgRatingsHashed.get(allMovies.get(i).getAvgVote()).add(allMovies.get(i));
        }
      }
  }
  
  private List<MovieInterface> resultingSet() {
    List<MovieInterface> list = new ArrayList<MovieInterface>();    
    for (int k = 0; k < avgRatingsAdded.size(); k++) {
       for (int l = 0; l < avgRatingsHashed.get(Float.parseFloat(avgRatingsAdded.get(k))).size(); l++) {
         list.add(avgRatingsHashed.get(Float.parseFloat(avgRatingsAdded.get(k))).get(l));
       }
    }
    int tracker = 0;
    if (list.isEmpty() && !genresAdded.isEmpty()) {
      list = genresHashed.get(genresAdded.get(0));
      tracker++;
    }
    
    for (int i = tracker; i < genresAdded.size(); i++) {
      for (int j = 0; j < list.size(); j++) {
        if (!list.get(j).getGenres().contains(genresAdded.get(i))) {
          list.remove(j);
          j--;
        }
      }    
    } 
    return list;   
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

  @Override
  public int getNumberOfMovies() {
    return resultingSet().size();
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
}
