import cs3500.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TestReadOnlyModel {
  ReadOnlyTriosModel<ThreeTriosCard> model;
  CardConfigReader cardReader;
  BoardConfigReader boardReader;

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

}
