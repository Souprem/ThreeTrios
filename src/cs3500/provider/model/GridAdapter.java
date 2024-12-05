package cs3500.provider.model;

/**
 * In our implementation not only do we have two seperate grids but we also have them as instance variables in our model rather than having it as its own class.
 * Therefore we decided not to implement this adapter
 */
public class GridAdapter implements Grid{
  @Override
  public int numCardCells() {
    return 0;
  }

  @Override
  public int holesInGrid() {
    return 0;
  }

  @Override
  public Cells[][] getGrid() {
    return new Cells[0][];
  }

  @Override
  public void setGrid(Cells[][] grid) {

  }
}
