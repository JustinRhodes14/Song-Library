/*
*
* This class implements a library of songs
*
* Justin Rhodes CS111 - Rutgers University
*
*/
public class SongApp {

  private Song[] items;      // keep Songs in an unsorted array
  private int numberOfItems; // number of Songs in the array

  /*
  * Default constructor creates array with capacity for 5 Songs
  */
  SongApp () {
      this.items = new Song[5];
      numberOfItems = 0;
  }

  /*
  * One argument constructor creates array with user defines capacity
  *
  * @param capacity defines the capacity of the Song library
  */
  SongApp (int capacity) {
      this.items = new Song[capacity];
      numberOfItems = 0;
  }

  /*
  * Add a Song into the library (unsorted array)
  *
  * If the library is full (there is not enough space to add another Song)
  *   - create a new array with double the size of the current array.
  *   - copy all current Songs into new array
  *   - add new Song
  *
  * @param m The Song to be added to the libary
  */
  public void addSong (Song m) {
      if (numberOfItems == items.length) {
        Song[] dubItems = new Song[(items.length)*2];
        for (int g = 0; g <items.length;g++) {
          dubItems[g] = items[g];
        }
        dubItems[items.length + 1] = m;
        numberOfItems++;
        items = dubItems;
      } else {
        for (int i = 0; i < items.length; i++) {
          if (items[i] == null) {
            items[i] = m;
            numberOfItems++;
            break;
          }
        }
      }
  }

  /*
  * Removes a Song from the library. Returns true if Song is removed, false
  * otherwise.
  * The array should not become sparse after a remove, that is, all Songs
  * should be in consecutive indexes in the array.
  *
  * @param m The Song to be removed
  *
  */
  public boolean removeSong (Song m) {
      for(int i = 0; i < numberOfItems; i++){
      if(items[i] != null && items[i].equals(m)){

        if(i != numberOfItems - 1 && items[i+1] != null){

          for(int j = i+1; j < numberOfItems; j++){
            items[j-1] = items[j];
          }

          items[numberOfItems-1] = null;

        }else{

          items[i] = null;

        }

        numberOfItems--;
        return true;
      }
    }
    return false;
  }


  /*
  * Returns the library of songs
  *
  * @return The array of Songs
  */
  public Song[] getSongs () {
      return items;
  }

  /*
  * Returns the current number of Songs in the library
  *
  * @return The number of items in the array
  */
  public int getNumberOfItems () {
      return numberOfItems;
  }

  /*
  * Update the rating of Song @m to @rating
  *
  * @param @m The Song to have its ratings updated
  * @param @rating The new rating of @m
  * @return tue if update is successfull, false otherwise
  *
  */
  public boolean updateRating (Song m, int rating) {
      for (int i = 0; i < numberOfItems; i++) {
        if (items[i] != null && items[i].equals(m)) {
          items[i].setRating(rating);
          return true;
        }
      }
      return false;
  }

  /*
  * Prints all Songs
  */
  public void print () {
      for (int i = 0; i < numberOfItems; i++) {
        System.out.println(items[i].toString());
      }
  }

  /*
  * Return all the Songs by @songwriter. The array size should be the
  * number of Songs by @songwriter.
  *
  * @param songwriter The songwriter's name
  * @param An array of all the Songs by @songwriter
  *
  */

  public Song[] getSongsBySongwriter (String songwriter) {
        Song[] sameWrite = new Song[0];
        int numSong = 0;
        for (int i = 0; i < numberOfItems; i++) {
          for (int j = 0; j < items[i].getWriters().length; j++) {
            if (items[i].getWriters()[j] != null && items[i].getWriters()[j].equals(songwriter)) {
              Song[] temp = new Song[sameWrite.length + 1];
              for (int k = 0; k < sameWrite.length; k++) {
                temp[k] = sameWrite[k];
              }

              sameWrite = temp;
              sameWrite[numSong] = items[i];
              numSong++;
              break;
            }
          }
        }
        return sameWrite;
          }

  /*
  * Return all the Songs released in @year. The array size should be the
  * number of Songs made in @year.
  *
  * @param year The year the Songs were made
  * @return An array of all the Songs made in @year
  *
  */
  public Song[] getSongsByYear (int year) {
      int numYear = 0;
      for (int i = 0; i < numberOfItems; i++) {
        if (items[i].getYear() == year) {
          numYear++;
        }
      }
      Song[] sameYear = new Song[numYear];
      for (int g = 0; g < numberOfItems; g++) {
        if (items[g].getYear() == year) {
          for (int k = 0; k < sameYear.length;k++) {
            if (sameYear[k] == null) {
              sameYear[k] = items[g];
              break;
            }
          }
        }
      }
      return sameYear;
  }

  /*
  * Return all the Songs with ratings greater then @rating
  *
  * @param rating
  * @return An array of all Songs with rating greater than @rating
  *
  */
  public Song[] getSongsWithRatingsGreaterThan (int rating) {
      int numRate = 0;
      for (int i = 0; i < numberOfItems; i++) {
        if (items[i].getRating() >= rating) {
          numRate++;
        }
      }
      Song[] sameRate = new Song[numRate];
      for (int g = 0; g < numberOfItems; g++) {
        if (items[g].getRating() >= rating) {
          for (int k = 0; k < sameRate.length;k++) {
            if (sameRate[k] == null) {
              sameRate[k] = items[g];
              break;
            }
          }
        }
      }
      return sameRate;
  }

  /*
  * Search for Song name @name using binary search algorithm.
  * Assumes items is sorted
  */
  public Song searchSongByName (String name) {
    int left = 0;
    int right = items.length - 1;
    while (left <= right) {
      int mid = (left + right)/2;
      if (items[mid].getName().equals(name)) {
        return items[mid];
      }
      if ((items[mid].getName()).compareTo(name) > 0) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return null;
  }

 /*
  * Sorts Songs by year using insertion sort
  */
  public void sortByYear () {
      for (int itemsSorted = 1; itemsSorted < numberOfItems; itemsSorted++) {
        Song temp = items[itemsSorted];
        int loc = itemsSorted - 1;

        while (loc >= 0 && items[loc].getYear() > temp.getYear()) {
          items[loc+1] = items[loc];
          loc = loc - 1;
        }
        items[loc+1] = temp;
        }
      }

 /*
  * Sorts array of Songs by name using selection sort
  */
  public void sortByName () {
      for (int l = numberOfItems - 1; l > 0; l--) {
        int maxLoc = 0;
        for (int j = 1; j <= l; j++) {
          if (items[j].compareTo(items[maxLoc]) > 0) {
            maxLoc = j;
          }
        }
        Song temp = items[maxLoc];
        items[maxLoc] = items[l];
        items[l] = temp;
      }
  }

 /*
  * Search for Song name using recursive linear search
  * @param name The name of the song to search
  * @param Songs The array containing all songs
  * @param l The left index
  * @param r The right index
  * @return The song with name @name or null if song is not found
  */
  public static Song searchSongByName (String name, Song[] songs, int l, int r) {
      if (songs[r] == null) {
        return searchSongByName(name,songs,l,r - 1);
      }
      if (songs[l].getName().equals(name)) {
        return songs[l];
      }
      if (songs[r].getName().equals(name)) {
        return songs[r];
      }
      if (l > r) {
        return null;
      }
      return searchSongByName(name,songs,l + 1, r - 1);
  }
}
