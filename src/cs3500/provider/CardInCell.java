package cs3500.provider;

import cs3500.provider.model.Card;
import cs3500.provider.model.CardColor;

/**
 * Represents a card that is specifically designed to be placed in a cell.
 * Extends the {@link Card} interface and adds additional functionality
 * related to the card's color.
 */
public interface CardInCell extends Card {

  /**
   * Retrieves the color of the card.
   *
   * @return the {@link CardColor} of the card.
   */
  CardColor getColor();

  /**
   * Sets the color of the card.
   *
   * @param color the {@link CardColor} to assign to the card.
   * @throws IllegalArgumentException if the color is {@code null} or invalid.
   */
  void setColor(CardColor color);
}
