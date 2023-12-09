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
}
