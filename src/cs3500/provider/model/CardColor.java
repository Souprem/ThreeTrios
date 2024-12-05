package cs3500.provider.model;

/**
 * Represents the color of a card.
 */
public enum CardColor {
  RED("Red"),
  BLUE("Blue");

  private String color;

  public String toString() {
    return this.color;
  }

  private CardColor(String color) {
    this.color = color;
  }
}