public class Song {
  private String name; //Name of song
  private int year; //Year song was released
  private int numberOfWriters = 0; //Number of writers for the given song
  private String[] writers = new String[50]; //Array of the writer's names, max being 50
  private int rating; // Ranges from 1-5

  /*
    Constructor initializes the new Song Object
  */
  public Song(String name) {
    this.name = name;
  }

  /*
    Updates the song's name
  */
  public void setName(String name) {
    this.name = name;
  }

  /*
    Returns the song's name
  */
  public String getName() {
    return this.name;
  }

  /*
    Updates the song's year
  */
  public void setYear(int year) {
    this.year = year;
  }

  /*
    Returns the song's year
  */
  public int getYear() {
    return this.year;
  }

  /*
    Updates the song's rating
  */
  public void setRating(int rating) {
    this.rating = rating;
  }

  /*
    Returns the song's rating
  */
  public int getRating() {
    return this.rating;
  }

  /*
    Adds a writer to the list of writers on the song
  */
  public void addWriter(String name) {
    for (int i = 0; i < writers.length; i++) {
      if (writers[i] == null) {
        writers[i] = name;
        this.numberOfWriters++;
        break;
      }
    }
    //when comparing strings to null value, u have to use ==, not .equals()
  }

  /*
    Returns the song's writer list
  */
  public String[] getWriters() {
    return writers;
  }

  /*
    Returns the number of writers of a given song
  */
  public int getNumberOfWriters() {
    return numberOfWriters;
  }

  /*
    Returns the write name at a given index of the song's writer list
  */
  public String getWriterAtIndex(int index) {
    if (writers[index] == null) {
      return null;
    }
    return writers[index];
  }

  /*
    toString method
  */
  public String toString() {
    String result = name + ", " + year + ", " + rating;
    result += ", [";
    for (int i = 0; i < numberOfWriters; i++) {
      result += writers[i];
      if (i < numberOfWriters - 1) {
        result += ", ";
      }
    }
    result += "]";

    return result;
  }

  /*
    Checks if all writers of the song are on the other song
    Checks to make sure name and year match
  */
  public boolean equals(Object other) {
    if (other instanceof Song) {
   Song s = (Song)other;
   int same = 0;
   for (int i = 0; i < writers.length; i++) {
     if (same == -1) {
       break;
     }
     for (int j = 0; j < writers.length; j++) {
       if (this.writers[i] == s.writers[j]) {
         break;
       }
       if (j == writers.length - 1 && this.writers[i] != s.writers[j]) {
         same = -1;
       }
     }
   }
   if (this.numberOfWriters != s.numberOfWriters) {
     return false;
   } else {
   return (this.name == s.getName() && this.year == s.getYear() && same == 0);
 }
 } else {
   return false;
 }
 }

 /*
  Compares given song name to other song lexicographically
 */
  public int compareTo(Song other) {
    String s = other.getName();
    for (int i = 0; i < s.length(); i++) {
      if (!(this.name.charAt(i) == s.charAt(i))) {
        if ((this.name.charAt(i) - 0) < (s.charAt(i) - 0)) {
          return -1;
        } else {
          return 1;
        }
      }
    }
    return 0;
    //May need tolowercase methods
    //Might have ran into problems since i was returning -1 on the else part and 1 on the if part
    //Need to compare song names lexicographically and show which one is greater(1) lesser(-1) or equal(0)
  }
}
