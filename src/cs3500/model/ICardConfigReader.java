package cs3500.model;

import java.util.List;

/**
 * Card configuration reader.
 */
public interface ICardConfigReader extends IConfigReader {

  /**
   * Converts a file, using the filepath field to find the path to the file, into a
   * list of three trios cards.
   * @return a list of three trio cards.
   */
  public List<Card> convertFile();
  
}