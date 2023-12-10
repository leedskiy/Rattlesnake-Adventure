package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.util.Random;

import rattlesnakeadventure.model.Coordinate;

public abstract class GameObject {
    protected Coordinate coord;
    protected BufferedImage icon;
    private Random rand;

    public GameObject(int rowCellsCount, int colCellsCount, int cellSize) {
        rand = new Random();
        coord = new Coordinate(rand.nextInt(rowCellsCount) * cellSize,
                rand.nextInt(colCellsCount) * cellSize);
    }

    public void genRandCoord(int rowCellsCount, int colCellsCount, int cellSize) {
        coord.setX(rand.nextInt(rowCellsCount) * cellSize);
        coord.setY(rand.nextInt(colCellsCount) * cellSize);
    }

    public Coordinate getCoord() {
        return coord;
    }

    protected abstract BufferedImage getIcon();
}
