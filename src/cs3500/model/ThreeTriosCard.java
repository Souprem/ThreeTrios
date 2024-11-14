package cs3500.model;

import java.util.Objects;

/**
 * A class meant to represent a single card for a three trios game.
 */
public class ThreeTriosCard implements Card {

  private final String CARD_NAME;

  private AttackValue north;
  private AttackValue south;
  private AttackValue west;
  private AttackValue east;

  Player owner;

  /**
   * A constructor for the ThreeTriosCard that takes in its name and its attack values as strings.
   * @param name the name of the card
   * @param north northern attack value
   * @param south southern attack value
   * @param east eastern attack value
   * @param west western attack value
   */
  public ThreeTriosCard(String name, String north, String south, String east, String west) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    this.CARD_NAME = name;
    if (north == null || south == null || east == null || west == null) {
      throw new IllegalArgumentException("Attack values cannot be empty");
    }
    if (north.isEmpty() || south.isEmpty() || east.isEmpty() || west.isEmpty()) {
      throw new IllegalArgumentException("Attack values cannot be empty");
    }

    this.north = setAttackValue(north, this.north);
    this.south = setAttackValue(south, this.south);
    this.east = setAttackValue(east, this.east);
    this.west = setAttackValue(west, this.west);
  }

  /**
   * Javadoc.
   */
  public ThreeTriosCard(ThreeTriosCard other) {
    // Copy immutable field directly (String is immutable)
    this.CARD_NAME = other.CARD_NAME;

    // Copy enum values directly (enums are immutable)
    this.north = other.north;
    this.south = other.south;
    this.east = other.east;
    this.west = other.west;

    // Deep copy Player if necessary (depending on whether Player is mutable)
    // If Player is immutable or a simple enum, direct assignment is fine
    this.owner = other.owner; // If Player needs deep copying, implement it in Player class
  }

  @Override
  public void setOwner(Player owner) {
    this.owner = owner;
  }

  /**
   * Javadoc.
   */
  @Override
  public Player getOwner() {
    return this.owner;
  }

  /**
   * Javadoc.
   */
  public String toString() {
    return this.CARD_NAME + " " + this.north.numericValue + " " + this.south.numericValue
            + " " + this.east.numericValue + " " + this.west.numericValue;
  }

  //Sets the attack value given the current attack value and a string representing the new value
  private AttackValue setAttackValue(String newValue, AttackValue val) {
    switch (newValue) {
      case "1":
        val = AttackValue.ONE;
        break;
      case "2":
        val = AttackValue.TWO;
        break;
      case "3":
        val = AttackValue.THREE;
        break;
      case "4":
        val = AttackValue.FOUR;
        break;
      case "5":
        val = AttackValue.FIVE;
        break;
      case "6":
        val = AttackValue.SIX;
        break;
      case "7":
        val = AttackValue.SEVEN;
        break;
      case "8":
        val = AttackValue.EIGHT;
        break;
      case "9":
        val = AttackValue.NINE;
        break;
      case "A":
        val = AttackValue.TEN;
        break;
      default:
        throw new IllegalArgumentException("Attack values must be 1-9 or A");
    }
    return val;
  }


  @Override
  public AttackValue getNorth() {
    return this.north;
  }

  @Override
  public AttackValue getSouth() {
    return this.south;
  }

  @Override
  public AttackValue getWest() {
    return this.west;
  }

  @Override
  public AttackValue getEast() {
    return this.east;
  }


  @Override
  public boolean equals(Object other) {
    boolean output = true;
    if (!(other instanceof ThreeTriosCard)) {
      output = false;
    }
    if (!Objects.equals(this.CARD_NAME, ((ThreeTriosCard) other).CARD_NAME)) {
      output = false;
    }
    if (this.east != ((ThreeTriosCard) other).east) {
      output = false;
    }
    if (this.west != ((ThreeTriosCard) other).west) {
      output = false;
    }
    if (this.south != ((ThreeTriosCard) other).south) {
      output = false;
    }
    if (this.north != ((ThreeTriosCard) other).north) {
      output = false;
    }
    return output;
  }

  @Override
  public int hashCode() {
    return Objects.hash(CARD_NAME, north, south, west, east);
  }

}
