package model.cards;

import java.util.List;

/**
 * The DealCards interface provides a method for dealing a set of cards
 * for different game scenarios.
 */
public interface DealCards {

  /**
   * Deals a predefined list of TripleTriosCards for the game.
   *
   * @return a list of TripleTriosCard objects dealt for the game
   */
  List<CardInCell> dealCards();
}
