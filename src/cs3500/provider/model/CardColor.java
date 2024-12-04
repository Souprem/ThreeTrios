package cs3500.provider.model;

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