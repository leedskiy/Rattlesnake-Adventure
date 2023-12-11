package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.util.Random;

import rattlesnakeadventure.model.Coordinate;

public abstract class GameObject {
    protected Coordinate coord;
    protected BufferedImage icon;
    private Random rand;

    public GameObject(int cellSize, int rowCellsCount, int colCellsCount) {
        this.rand = new Random();
        this.coord = new Coordinate(this.rand.nextInt(rowCellsCount) * cellSize,
                this.rand.nextInt(colCellsCount) * cellSize);
    }

    public void genRandCoord(int cellSize, int rowCellsCount, int colCellsCount) {
        coord.setX(this.rand.nextInt(rowCellsCount) * cellSize);
        coord.setY(this.rand.nextInt(colCellsCount) * cellSize);
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    protected abstract BufferedImage getIcon();
}
