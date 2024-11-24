package cs3500.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class meant to read in card configuration files.
 */
public class CardConfigReader extends ConfigReader {

  /**
   * A constructor for the CardConfigReader class that takes in a String representing the path
   * to a file to be read.
   * @param filepath a string representing the path to a particular file.
   */
  public CardConfigReader(String filepath) {
    super(filepath);
  }

  /**
   * Converts a file, using the filepath field to find the path to the file, into a
   * list of three trios cards.
   * @return a list of three trio cards.
   */
  public List<ThreeTriosCard> convertFile() {
    try {
      if (!super.doesFileExist(this.filepath)) {
        throw new IllegalArgumentException("must input valid filepath");
      }
      BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
      String line;
      List<ThreeTriosCard> outputList = new ArrayList<>();
      while ((line = reader.readLine()) != null) {
        String[] tempList = line.split("\\s+");
        outputList.add(new ThreeTriosCard(
                tempList[0], tempList[1], tempList[2], tempList[3], tempList[4]));
      }
      reader.close();
      return outputList;
    } catch (IOException | InvalidPathException e) {
      throw new IllegalArgumentException("filepath cannot be found");
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("must input correctly formatted card file");
    }
  }

}
