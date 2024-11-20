package cs3500.controller;

import cs3500.model.BoardConfigReader;
import cs3500.model.Card;
import cs3500.model.CardConfigReader;
import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;
import cs3500.view.TriosGUIView;
import cs3500.view.TriosView;
import cs3500.model.Player;


public class ThreeTriosController implements TriosController {
  private TriosGUIView view;
  private PlayerActions player;
  private TriosModel model;
  private int numCardCells;
  private ModelFeatures modelFeatures;
  private ViewFeatures viewFeatures;
  //viewFeatures object + modelFeatures object

  public ThreeTriosController(TriosModel<Card> model, TriosGUIView view,
                              PlayerActions player, int numCardCells){
    this.view = view;
    this.player = player;
    this.model = model;
    this.numCardCells = numCardCells;
    model.addObserver(this);
    this.modelFeatures = new ModelFeatureImpl(model);
    this.addModelFeatures();
    this.viewFeatures = new ViewFeaturesImpl(view);
    this.addViewFeatures();
  }

  public void addModelFeatures() {
    modelFeatures.addListener(this);
  }

  public void addViewFeatures() {
    viewFeatures.addListener(this);
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
    model.startGame(numCardCells, cardReader, boardReader);
  }

  public void playerChanged(PlayerColor p) {

  }

  @Override
  public void update() {

  }
}
