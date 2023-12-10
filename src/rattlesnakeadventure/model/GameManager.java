package rattlesnakeadventure.model;

public class GameManager {
    private int cellSize;
    private int rowCellsCount;
    private int colCellsCount;
    private int totalCellsCount;

    public GameManager() {
        this.cellSize = 1;
        this.rowCellsCount = 100 / cellSize;
        this.colCellsCount = 100 / cellSize;
        this.totalCellsCount = rowCellsCount * colCellsCount;
    }

    public GameManager(int cellSize, int width, int height) {
        this.cellSize = cellSize;
        this.rowCellsCount = width / cellSize;
        this.colCellsCount = height / cellSize;
        this.totalCellsCount = rowCellsCount * colCellsCount;
    }
}
