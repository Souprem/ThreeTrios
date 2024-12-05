package cs3500.provider.model;

import cs3500.provider.CardInCell;

/**
 * we don't have anything in our implementation that would make sense for this to adapt to.
 * Therefore, we decided not to implement it.
 */

public class CellsAdapter implements Cells {
  @Override
  public boolean isPlayable() {
    return false;
  }

  @Override
  public boolean cardExists() {
    return false;
  }

  @Override
  public void placeCard(CardInCell card) {
    //do nothing
  }

  @Override
  public CardInCell getCard() {
    return null;
  }
}
