package cs3500.provider.model;

import cs3500.provider.CardInCell;

/**
 * Represents a cell that can hold a card in a game.
 * The cell may have specific rules for placing and retrieving cards.
 */
public interface Cells {

  /**
   * Determines whether the cell is in a playable state.
   *
   * @return {@code true} if the cell is playable, otherwise {@code false}.
   */
  boolean isPlayable();

  /**
   * Checks if there is a card currently present in the cell.
   *
   * @return {@code true} if a card exists in the cell, otherwise {@code false}.
   */
  boolean cardExists();

  /**
   * Places a card into the cell.
   * This operation may replace an existing card depending on the implementation.
   *
   * @param card the {@link CardInCell} to be placed in the cell
   * @throws IllegalArgumentException if the card cannot be placed due to specific rules or constraints.
   */
  void placeCard(CardInCell card);

  /**
   * Retrieves the card currently in the cell.
   *
   * @return the {@link CardInCell} currently in the cell, or {@code null} if no card exists.
   */
  CardInCell getCard();
}
