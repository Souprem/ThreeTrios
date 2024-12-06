package cs3500.provider.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.model.IBoardConfigReader;
import cs3500.model.ICardConfigReader;
import cs3500.model.PlayerColor;
import cs3500.model.ThreeTriosCard;
import cs3500.model.TriosModel;
import cs3500.provider.CardInCell;
import cs3500.provider.CardInCellAdapter;

/**
 * this is an adapter for the providers model interface. it uses an instance of our own implenentation of model to allow it to work with the rest of our code.
 */
public class ModelAdapter implements ReadOnlyModel, ThreeTriosModel{

  cs3500.model.ThreeTriosModel model;
  int numCardCells;
  ICardConfigReader cardConfigReader;
  IBoardConfigReader boardConfigReader;

  public ModelAdapter(TriosModel model, int numCardCells, ICardConfigReader configReader, IBoardConfigReader boardConfigReader) {
    this.model = (cs3500.model.ThreeTriosModel) model;
    this.numCardCells = numCardCells;
    this.cardConfigReader = cardConfigReader;
    this.boardConfigReader = boardConfigReader;
  }

  @Override
  public void startGame() {
    model.startGame(this.numCardCells, this.cardConfigReader, this.boardConfigReader);
  }

  @Override
  public void placingCard(int row, int col, int cardIdx) {
    model.playCard(cardIdx, row, col);

  }

  @Override
  public List<CardInCell> allNumCards() {
   ArrayList<CardInCell> allCards = new ArrayList<CardInCell>();
   for (ThreeTriosCard card : model.getHand(PlayerColor.BLUE)){
     allCards.add(new CardInCellAdapter(card.getName(), card.getNorth().toString(), card.getSouth().toString(), card.getEast().toString(), card.getWest().toString()));
   }
   for (ThreeTriosCard card : model.getHand(PlayerColor.RED)){
     allCards.add(new CardInCellAdapter(card.getName(), card.getNorth().toString(), card.getSouth().toString(), card.getEast().toString(), card.getWest().toString()));
   }
   return allCards;
  }

  @Override
  public PlayerTurnEnum getPlayerTurn() {
    return PlayerTurnEnum.valueOf(model.getCurrentPlayer().toString());
  }

  @Override
  public List<CardInCell> getPlayersTurnHand() {
    ArrayList<CardInCell> currentPlayersCards = new ArrayList<CardInCell>();
    for (ThreeTriosCard card : model.getHand(model.getCurrentPlayer())){
      currentPlayersCards.add(new CardInCellAdapter(card.getName(), card.getNorth().toString(), card.getSouth().toString(), card.getEast().toString(), card.getWest().toString()));
    }
    return currentPlayersCards;
  }

  @Override
  public List<CardInCell> getPlayerHand(PlayerTurnEnum playerTurn) {
    ArrayList<CardInCell> playersCards = new ArrayList<CardInCell>();
    for (ThreeTriosCard card : model.getHand(PlayerColor.valueOf(playerTurn.toString()))){
      playersCards.add(new CardInCellAdapter(card.getName(), card.getNorth().toString(), card.getSouth().toString(), card.getEast().toString(), card.getWest().toString()));
    }
    return playersCards;
  }

  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  @Override
  public PlayerTurnEnum whoWon() {
    return PlayerTurnEnum.valueOf(model.getWinner().toString());
  }

  @Override
  public Cells[][] getBoard() {
    return new Cells[0][];
  }
}
