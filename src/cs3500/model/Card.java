package cs3500.model;

/**
 * Behaviors for a Model.Card in the Game of Three Trios.
 */
public interface Card {

  /**
   * Returns the north-facing attack value of the card.
   *
   * @return an AttackValue representing the northern attack value of the card.
   */
  AttackValue getNorth();

  /**
   * Returns the south-facing attack value of the card.
   * @return an AttackValue representing the southern attack value of the card.
   */
  AttackValue getSouth();

  /**
   * Returns the west-facing attack value of the card.
   * @return an AttackValue representing the western attack value of the card.
   */
  AttackValue getWest();

  /**
   * Returns the east-facing attack value of the card.
   * @return an AttackValue representing the eastern attack value of the card.
   */
  AttackValue getEast();

  /**
   * Sets the owner associated with this card, given the card's new owner.
   * @param owner the card's new owner.
   */
  void setOwner(PlayerColor owner);

  /**
   * Returns the current owner of this card.
   * @return a Player representing this card's current owner.
   */
  PlayerColor getOwner();

  /**
   * Returns the card's name followed by each of its attack values.
   * @return a string representing the card.
   */
  String toString();
}
