package cs3500.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class meant to read in board configuration files.
 */
public class BoardConfigReader extends ConfigReader implements IBoardConfigReader {

  /**
   * A constructor for the BoardConfigReader class that takes in a String representing the path
   * to a file to be read.
   * @param filepath a string representing the path to a particular file.
   */
  public BoardConfigReader(String filepath) {
    super(filepath);
  }

  @Override
  public Status[][] convertFile() {
    try {
      if (!super.doesFileExist(this.filepath)) {
        throw new IllegalArgumentException("must input valid filepath");
      }
      BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
      String line = reader.readLine();
      String[] rowscols = line.split("\\s+");
      int rows = Integer.parseInt(rowscols[0]);
      int cols = Integer.parseInt(rowscols[1]);
      Status[][] statusBoard = new Status[rows][cols];
      while ((line = reader.readLine()) != null) {
        for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
          for (int colIndex = 0; colIndex < cols; colIndex++) {
            Character value = line.charAt(colIndex);
            if (value.equals('X')) {
              statusBoard[rowIdx][colIndex] = Status.HOLE;
            }
            if (value.equals('C')) {
              statusBoard[rowIdx][colIndex] = Status.EMPTY;
            }
          }
          line = reader.readLine();
        }
      }
      reader.close();
      return statusBoard;
    } catch (IOException e) {
      throw new IllegalArgumentException("filepath cannot be found");
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("must input correctly formatted board file");
    }
  }

}
