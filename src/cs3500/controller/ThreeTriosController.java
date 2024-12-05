package cs3500.controller;

import cs3500.model.BoardConfigReader;
import cs3500.model.Card;
import cs3500.model.CardConfigReader;
import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;
import cs3500.view.TriosGUIView;

/**
 * Implementation of a controller for the three trios game.
 * Utilizes the model, view, view features, and model features to communicate between
 * aspects of the game according to user input.
 */
public class ThreeTriosController implements TriosController {
  private TriosGUIView view;
  private PlayerActions player;
  private TriosModel model;
  private int numCardCells;
  private ModelFeatures modelFeatures;
  private ViewFeatures viewFeatures;

  /**
   * Constructor for the ThreeTriosController which takes in the model, view, player whose
   * using this controller to play the game, and the number of card cells to
   * initialize the game with.
   * @param model the given model.
   * @param view the given view.
   * @param player the given player to play with this controller.
   * @param numCardCells the number of card cells to initialize the game with.
   */
  public ThreeTriosController(TriosModel<Card> model, TriosGUIView view,
                              PlayerActions player, int numCardCells) {
    this.view = view;
    this.player = player;
    this.model = model;
    this.numCardCells = numCardCells;
    this.modelFeatures = new ModelFeatureImpl(model);
    this.addModelFeatures();
    this.viewFeatures = new ViewFeaturesImpl(view);
    this.addViewFeatures();
  }

  @Override
  public void addModelFeatures() {
    modelFeatures.addListener(this);
  }

  @Override
  public void addViewFeatures() {
    viewFeatures.addListener(this);
  }

  @Override
  public void selectHandCard(int handIndex, PlayerColor hand) {
    if (hand == this.player.getColor()) {
      view.onSelectedHandCard(handIndex, hand);
      this.player.selectHandCard(handIndex);
    }
  }

  @Override
  public void selectGridCard(int row, int col) {
    if (checkTurn()) {
      player.placeCardOnGrid(row, col);
    }
  }

  /**
   * Checks if the model's current turn is the same as the player associated with this controller.
   */
  private boolean checkTurn() {
    return this.model.getCurrentPlayer() == this.player.getColor();
  }

  @Override
  public void playGame(String cardPath, String boardPath, TriosModel model) {
    if (cardPath == null || cardPath.isEmpty()) {
      throw new IllegalArgumentException("the card file cannot be empty");
    }
    if (boardPath == null || boardPath.isEmpty()) {
      throw new IllegalArgumentException("the card file cannot be empty");
    }
    CardConfigReader cardReader = new CardConfigReader(cardPath);
    BoardConfigReader boardReader = new BoardConfigReader(boardPath);
    model.addFeatures(modelFeatures);
    model.startGame(numCardCells, cardReader, boardReader);
    view.addFeatures(viewFeatures);
    view.startGame();
  }

  @Override
  public void playerChanged() {
    if (checkTurn() && player instanceof AIPlayer) {
      player.placeCardOnGrid(0, 0);
    }
    this.view.refresh();
  }

  @Override
  public void gameOver(PlayerColor winner) {
    this.view.endGame(winner);
  }

}
