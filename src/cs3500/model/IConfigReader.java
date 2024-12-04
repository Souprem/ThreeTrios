package cs3500.model;

/**
 * An interface representing the reading of configuration files.
 */
public interface IConfigReader {

  /**
   * Determines whether the path to a file exists.
   * @param path the path to the file.
   * @return a boolean representing whether the given path exists.
   */
  boolean doesFileExist(String path);

}