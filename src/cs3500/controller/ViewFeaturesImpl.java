package cs3500.controller;

import cs3500.model.PlayerColor;
import cs3500.view.TriosGUIView;

/**
 * Implementation for the ViewFeatures class which
 * outputs to a TriosController whenever one of its relevant methods
 * is called within the view that holds an instance of it.
 */
public class ViewFeaturesImpl implements ViewFeatures {
  TriosGUIView view;
  TriosController listener;

  /**
   * Constructor for the ViewFeaturesImpl which takes in a TriosGUIView.
   * @param view the inputted view.
   */
  public ViewFeaturesImpl(TriosGUIView view) {
    this.view = view;
  }

  @Override
  public void addListener(TriosController listener) {
    this.listener = listener;
  }

  @Override
  public void selectHandCard(int handIndex, PlayerColor hand) {
    listener.selectHandCard(handIndex, hand);
  }

  @Override
  public void selectGridCard(int row, int col) {
    listener.selectGridCard(row, col);
  }

  @Override
  public void setVisible() {
    //not sure yet
  }
}
