package cs3500.provider.model;

import cs3500.model.ThreeTriosCard;

/**
 * this is an adapter for the providers Card interface.
 * it uses an instance of our own implenentation of card to
 * allow it to work with the rest of our code.
 */
public class CardAdapter implements Card {
  ThreeTriosCard card;

  /**
   * constructor for the adapter.
   */
  public CardAdapter(String name, String north, String south, String east, String west) {
    card = new ThreeTriosCard(name, north, south, east, west);
  }

  @Override
  public String getNumber(int idx) {
    if (idx == 1) {
      return card.getNorth().stringValue;
    }
    else if (idx == 2) {
      return card.getSouth().stringValue;
    }
    else if (idx == 3) {
      return card.getEast().stringValue;
    }
    else if (idx == 4) {
      return card.getWest().stringValue;
    }
    throw new IndexOutOfBoundsException("must specify index between 1-4");
  }

  @Override
  public int getValue(int idx) {
    if (idx == 1) {
      return Integer.parseInt(card.getNorth().stringValue);
    }
    else if (idx == 2) {
      return Integer.parseInt(card.getSouth().stringValue);
    }
    else if (idx == 3) {
      return Integer.parseInt(card.getEast().stringValue);
    }
    else if (idx == 4) {
      return Integer.parseInt(card.getWest().stringValue);
    }
    throw new IndexOutOfBoundsException("must specify index between 1-4");
  }

  @Override
  public String toString() {
    return card.toString();
  }
}
