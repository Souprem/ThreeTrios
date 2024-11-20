package cs3500.controller;

public interface ViewFeatures {
  public void selectHandCard(int handIndex);

  public void selectGridCard(int row, int col);

  public void setVisible();

  void addListener(TriosController listener);

}

//Controller holds both features objects
//Features objects hold their respective references (view for viewFeatures, model for modelFeature)
//Controller acts as a listener for the two features objects it holds
//this class should do more of the work than PlayerActions