package rattlesnakeadventure.model;

public class GameManager {
    private int width;
    private int height;
    private int cellSize;
    private int cellsCount;
    private int delay;

    public GameManager() {

    }

    public GameManager(int width, int height, int cellSize, int cellsCount, int delay) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.cellsCount = cellsCount;
        this.delay = delay;
    }
}
