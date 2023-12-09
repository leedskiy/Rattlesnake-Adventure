package rattlesnakeadventure.model;

public class GameManager {
    private int rowCellsCount;
    private int colCellsCount;
    private int totalCellsCount;
    private int cellSize;

    public GameManager() {
        this.rowCellsCount = 100 / cellSize;
        this.colCellsCount = 100 / cellSize;
        this.totalCellsCount = rowCellsCount * colCellsCount;
        this.cellSize = 1;
    }

    public GameManager(int width, int height, int cellSize) {
        this.rowCellsCount = width / cellSize;
        this.colCellsCount = height / cellSize;
        this.totalCellsCount = rowCellsCount * colCellsCount;
        this.cellSize = cellSize;
    }
}
