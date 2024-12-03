package cs3500.provider.model;


/**
 * The Card interface defines essential methods that any card in the game
 * must implement, including retrieving and setting the card's color, accessing
 * specific numbers and values associated with the card, and generating a string representation.
 */
public interface Card {

  /**
   * Retrieves a specific number associated with the card by index.
   *
   * @param idx the index of the number to retrieve
   * @return a String representing the number at the specified index
   * @throws IndexOutOfBoundsException if idx is out of the valid range
   */
  String getNumber(int idx);

  /**
   * Retrieves a specific value associated with the card by index.
   *
   * @param idx the index of the value to retrieve
   * @return an int representing the value at the specified index
   * @throws IndexOutOfBoundsException if idx is out of the valid range
   */
  int getValue(int idx);

  /**
   * Generates a string representation of the card.
   *
   * @return a String representing the card
   */
  String toString();
}
