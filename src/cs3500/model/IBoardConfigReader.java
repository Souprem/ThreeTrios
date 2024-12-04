package cs3500.model;

/**
 * Board configuration reader.
 */
public interface IBoardConfigReader extends IConfigReader {

  /**
   * Converts a file, using the filepath field to find the path to the file, into a
   * 2D array of Status representing the board's statuses.
   * @return a 2D array representing a board of statuses.
   */
  public Status[][] convertFile();

}