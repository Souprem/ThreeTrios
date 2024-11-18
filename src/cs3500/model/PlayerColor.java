package cs3500.model;

/**
 * An enum representing a player's team in a three trios game.
 */
public enum PlayerColor {
  RED, BLUE;

  public PlayerColor getOther() {
    return this== RED ? BLUE : BLUE;
  }


}
