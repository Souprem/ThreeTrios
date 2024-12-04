package cs3500.model;

import java.io.File;

/**
 * An abstract class representing the reading of configuration files.
 */
public abstract class ConfigReader implements IConfigReader {

  final String filepath;

  /**
   * Constructor for the ConfigReader class that takes in a filepath.
   * @param filepath the path to a file that's meant to be read.
   */
  public ConfigReader(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public boolean doesFileExist(String path) {
    File file = new File(path);
    return file.exists();
  }

}
