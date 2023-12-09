package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.util.Random;

import rattlesnakeadventure.model.Coordinate;

public abstract class Fruit {
    protected Coordinate coord;
    protected BufferedImage icon;
    private Random rand;

    public Fruit(int width, int height, int cellSize) {
        rand = new Random();
        coord = new Coordinate(rand.nextInt((int) (width / cellSize)) * cellSize,
                rand.nextInt((int) (height / cellSize)) * cellSize);
    }

    public void genRandCoord(int width, int height, int cellSize) {
        coord.setX(rand.nextInt((int) (width / cellSize)) * cellSize);
        coord.setY(rand.nextInt((int) (height / cellSize)) * cellSize);
    }

    public Coordinate getCoord() {
        return coord;
    }

    protected abstract BufferedImage getIcon();
}
