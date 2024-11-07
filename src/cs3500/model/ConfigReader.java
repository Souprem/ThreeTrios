package cs3500.model;

import java.io.File;

/**
 * An abstract class representing the reading of configuration files.
 */
public abstract class ConfigReader {

  final String filepath;

  /**
   * Constructor for the ConfigReader class that takes in a filepath.
   * @param filepath the path to a file that's meant to be read.
   */
  public ConfigReader(String filepath) {
    this.filepath = filepath;
  }

  /**
   * Determines whether the path to a file exists.
   * @param path the path to the file.
   * @return a boolean representing whether the given path exists.
   */
  public static boolean doesFileExist(String path) {
    File file = new File(path);
    return file.exists();
  }

}
