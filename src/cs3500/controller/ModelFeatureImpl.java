package cs3500.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;

/**
 * Implementation for the ModelFeatures class which
 * outputs to a TriosController whenever one of its relevant methods
 * is called within the model that holds an instance of it.
 */
public class ModelFeatureImpl implements ModelFeatures {
  private final List<TriosController> listener;
  TriosModel model;

  /**
   * A constructor for ModelFeaturesImpl which takes in a TriosModel.
   * @param model the inputted TriosModel.
   */
  public ModelFeatureImpl(TriosModel model) {
    this.model = model;
    this.listener = new ArrayList<>();
  }

  @Override
  public void playerTurnChanged() {
    for (TriosController controller : listener) {
      controller.playerChanged(model.getCurrentPlayer());
    }
  }

  public void setThisAsListener() {
    this.model.addFeatures(this);
  }

  @Override
  public void addListener(TriosController listener) {
    this.listener.add(listener);
  }

  @Override
  public void gameOver(PlayerColor winner) {
    for (TriosController controller : listener) {
      controller.gameOver(winner);
    }
  }

}
