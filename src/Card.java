
/**
 * Behaviors for a Card in the Game of Three Trios.
 */
public interface Card {

  AttackValue getNorth();

  AttackValue getSouth();

  AttackValue getWest();

  AttackValue getEast();

  void setOwner(Player owner);

  Player getOwner();

  String toString();


}
