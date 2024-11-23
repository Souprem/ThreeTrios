package cs3500.model;

/**
 * An enum representing a player's team in a three trios game.
 */
public enum PlayerColor {
  RED, BLUE;

  /**
   * Gets the opposite player color.
   * @return the opposite PlayerColor.
   */
  public PlayerColor getOther() {
    return this == RED ? BLUE : BLUE;
  }

  /**
   * Returns a string representation of this PlayerColor.
   * @return a string representation.
   */
  public String toString() {
    String re = "";
    if (this == RED) {
      re = "Red";
    } else if (this == BLUE) {
      re = "Blue";
    }
    return re;

  }


}
