package rattlesnakeadventure.model.object;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import rattlesnakeadventure.model.Coordinate;

public abstract class GameObject {
    protected Coordinate coord;
    protected BufferedImage icon;
    private Random rand;

    public GameObject(int cellSize, int width, int height, ArrayList<Coordinate> snakeParts, int areaSize) {
        this.rand = new Random();
        this.coord = new Coordinate(0, 0);
        genRandCoord(cellSize, width, height, snakeParts, areaSize);
    }

    public void genRandCoord(int cellSize, int width, int height, ArrayList<Coordinate> snakeParts, int areaSize) {
        do {
            int x = this.rand.nextInt(width / cellSize) * cellSize;
            this.coord.setX(x);
            int y = this.rand.nextInt(height / cellSize) * cellSize;
            this.coord.setY(y);
        } while (checkSnakeOverlap(cellSize, snakeParts, areaSize));
    }

    private Boolean checkSnakeOverlap(int cellSize, ArrayList<Coordinate> snakeParts, int areaSize) {
        for (Coordinate snakePart : snakeParts) {
            for (int i = -areaSize; i <= areaSize; i++) {
                for (int j = -areaSize; j <= areaSize; j++) {
                    int offsetX = i * cellSize;
                    int offsetY = j * cellSize;

                    if (this.coord.getX() + offsetX == snakePart.getX() &&
                            this.coord.getY() + offsetY == snakePart.getY()) {
                        return true; // true if there is overlap with snake or areaSize around snake
                    }
                }
            }
        }

        return false;
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    public abstract BufferedImage getIcon();
}
