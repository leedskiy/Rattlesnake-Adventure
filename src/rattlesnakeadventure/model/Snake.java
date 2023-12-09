package rattlesnakeadventure.model;

import java.util.ArrayList;
import java.util.Random;

public class Snake {
    private ArrayList<Coordinate> parts;
    private int partsCount;
    private int eatenFruitsCount;
    private char direction;
    private Random rand;

    public Snake() {
        parts = new ArrayList<Coordinate>();
        this.partsCount = 2;
        this.eatenFruitsCount = 0;
        setRandomDirection();
    }

    private void setRandomDirection() {
        rand = new Random();
        int randNum = rand.nextInt(4) + 1;

        switch (randNum) {
            case (1):
                this.direction = 'U';
                break;
            case (2):
                this.direction = 'R';
                break;
            case (3):
                this.direction = 'D';
                break;
            case (4):
                this.direction = 'L';
                break;
        }
    }

    public void move(int cellSize) {
        for (int i = partsCount - 1; i > 0; i--) {
            Coordinate currentPart = parts.get(i);
            Coordinate previousPart = parts.get(i - 1);

            currentPart.setX(previousPart.getX());
            currentPart.setY(previousPart.getY());
        }

        Coordinate head = parts.get(0);

        switch (this.direction) {
            case 'U':
                head.setY(head.getY() - cellSize);
                break;
            case 'R':
                head.setX(head.getX() + cellSize);
                break;
            case 'D':
                head.setY(head.getY() + cellSize);
                break;
            case 'L':
                head.setX(head.getX() - cellSize);
                break;
        }
    }
}