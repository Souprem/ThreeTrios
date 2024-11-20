package cs3500.controller;

import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;

public interface TriosController {

  void playGame(String cardPath, String boardPath, TriosModel model);

  void playerChanged(PlayerColor color);

  void update();
}

//interactions between game logic and user input
//features are view features: things that the view should be able to do
//play interaction: 
