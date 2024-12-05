package cs3500.provider.model;

/**
 * Represents the color of a player.
 */
public enum PlayerTurnEnum {
  RED("RED"),
  BLUE("BLUE");

  private final String disp;

  private PlayerTurnEnum(String disp) {
    this.disp = disp;
  }

  public String toString() {
    return this.disp;
  }
}
