package cs3500.controller;

import cs3500.model.Card;
import cs3500.model.ThreeTriosModel;
import cs3500.model.TriosModel;
import cs3500.view.ThreeTriosGUIView;
import cs3500.view.TriosView;
import cs3500.model.Player;


public class ThreeTriosController implements Observer{
  private TriosView view;
  private Player player;
  private TriosModel model;

  public ThreeTriosController(TriosModel<Card> model, TriosView view, Player player){
    this.view = view;
    this.player = player;
    this.model = model;
    model.addObserver(this);
  }

  @Override
  public void moveMade(){

  }

  @Override
  public void update() {

  }

}
