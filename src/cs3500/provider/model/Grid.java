package cs3500.provider.model;

/**
 * Interface for representing a grid structure in a game.
 * Provides methods for managing and querying a grid of cells.
 */
public interface Grid {

  /**
   * Counts the number of playable cells in the grid.
   *
   * @return the number of cells that are playable.
   */
  int numCardCells();

  /**
   * Counts the non-playable cells (holes) within the grid.
   *
   * @return the number of non-playable cells.
   */
  int holesInGrid();

  /**
   * Retrieves the grid as a 2D array of Cells.
   *
   * @return a 2D array representing the grid.
   */
  Cells[][] getGrid();

  void setGrid(Cells[][] grid);
}
