package cs3500.controller;

import cs3500.view.TriosGUIView;

public class ViewFeaturesImpl implements ViewFeatures {
  TriosGUIView view;
  TriosController listener;

  public ViewFeaturesImpl(TriosGUIView view) {
    this.view = view;
  }

  @Override
  public void addListener(TriosController listener) {
    this.listener = listener;
  }

  @Override
  public void selectHandCard(int handIndex) {

  }

  @Override
  public void selectGridCard(int row, int col) {

  }

  @Override
  public void setVisible() {

  }
}
