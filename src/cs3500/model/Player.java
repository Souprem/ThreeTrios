package cs3500.model;

/**
 * An enum representing a player's team in a three trios game.
 */
public enum Player {
  RED, BLUE;

  public Player getOther() {
    return this== RED ? BLUE : BLUE;
  }


}
