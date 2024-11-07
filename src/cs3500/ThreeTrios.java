package cs3500;

import cs3500.model.ThreeTriosModel;
import cs3500.view.ThreeTriosGUIView;

public class ThreeTrios {
  public static void main(String[] args) {
    ThreeTriosModel model = new ThreeTriosModel();
    ThreeTriosGUIView view = new ThreeTriosGUIView(model);
    view.setVisible(true);
  }
}
