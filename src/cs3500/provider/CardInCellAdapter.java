package cs3500.provider;

import cs3500.model.PlayerColor;
import cs3500.model.ThreeTriosCard;
import cs3500.provider.model.CardColor;

/**
 * this is an adapter for the providers cardInCell interface. it uses an instance of our own implenentation of card to allow it to work with the rest of our code.
 */
public class CardInCellAdapter implements CardInCell{

  ThreeTriosCard card;

  public CardInCellAdapter(String name, String north, String south, String east, String west){
    card = new ThreeTriosCard(name, north, south, east, west);
  }
  @Override
  public CardColor getColor() {
    return CardColor.valueOf(card.getOwner().toString());
  }

  @Override
  public void setColor(CardColor color) {
    card.setOwner(PlayerColor.valueOf(color.toString()));
  }

  @Override
  public String getNumber(int idx) {
    if (idx == 1){
      return card.getNorth().stringValue;
    }
    else if (idx == 2){
      return card.getSouth().stringValue;
    }
    else if (idx == 3){
      return card.getEast().stringValue;
    }
    else if (idx == 4){
      return card.getWest().stringValue;
    }
    throw new IndexOutOfBoundsException("must specify index between 1-4");
  }

  @Override
  public int getValue(int idx) {
    if (idx == 1){
      return Integer.parseInt(card.getNorth().stringValue);
    }
    else if (idx == 2){
      return Integer.parseInt(card.getSouth().stringValue);
    }
    else if (idx == 3){
      return Integer.parseInt(card.getEast().stringValue);
    }
    else if (idx == 4){
      return Integer.parseInt(card.getWest().stringValue);
    }
    throw new IndexOutOfBoundsException("must specify index between 1-4");
  }
}
