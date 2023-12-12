package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import rattlesnakeadventure.model.Coordinate;

public abstract class GameObject {
    protected Coordinate coord;
    protected BufferedImage icon;
    private Random rand;

    public GameObject(int cellSize, int rowCellsCount, int colCellsCount, ArrayList<Coordinate> snakeParts) {
        this.rand = new Random();
        this.coord = new Coordinate(0, 0);
        genRandCoord(cellSize, rowCellsCount, colCellsCount, snakeParts);
    }

    public void genRandCoord(int cellSize, int rowCellsCount, int colCellsCount, ArrayList<Coordinate> snakeParts) {
        do {
            this.coord.setX(this.rand.nextInt(rowCellsCount) * cellSize);
            this.coord.setY(this.rand.nextInt(colCellsCount) * cellSize);
        } while (checkSnakeOverlap(snakeParts));
    }

    private Boolean checkSnakeOverlap(ArrayList<Coordinate> snakeParts) {
        for (Coordinate snakePart : snakeParts) {
            if (this.coord.getX() == snakePart.getX() && this.coord.getY() == snakePart.getY()) {
                return true;
            }
        }

        return false;
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    public abstract BufferedImage getIcon();
}
