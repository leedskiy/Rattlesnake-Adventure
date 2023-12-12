package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import rattlesnakeadventure.model.Coordinate;

public abstract class GameObject {
    protected Coordinate coord;
    protected BufferedImage icon;
    private Random rand;

    public GameObject(int cellSize, int width, int height, ArrayList<Coordinate> snakeParts) {
        this.rand = new Random();
        this.coord = new Coordinate(0, 0);
        genRandCoord(cellSize, width, height, snakeParts);
    }

    public void genRandCoord(int cellSize, int width, int height, ArrayList<Coordinate> snakeParts) {
        do {
            int x = this.rand.nextInt(width / cellSize) * cellSize;
            System.out.println("x " + x);
            this.coord.setX(x);
            int y = this.rand.nextInt(height / cellSize) * cellSize;
            System.out.println("y " + y);
            this.coord.setY(y);
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
