package rattlesnakeadventure.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import rattlesnakeadventure.model.object.Apple;
import rattlesnakeadventure.model.object.Banana;
import rattlesnakeadventure.model.object.Cherry;
import rattlesnakeadventure.model.object.GameObject;
import rattlesnakeadventure.model.object.Rock;

public class GameManager {
    private int cellSize;
    private int width;
    private int height;
    private int rocksCount;
    private Snake snake;
    private ArrayList<GameObject> fruits;
    private int currFruitInd;
    private ArrayList<Rock> rocks;
    private Boolean gameEnd;
    private Random rand;

    public GameManager() {
        this(1, 100, 100, 5);
    }

    public GameManager(int cellSize, int width, int height, int rocksCount) {
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        this.rocksCount = rocksCount;
        this.rand = new Random();
        snake = new Snake(this.cellSize, this.width, this.height);
        initRocks();
        initFruits();
        this.gameEnd = false;
    }

    private void initRocks() {
        ArrayList<Coordinate> snakeParts = snake.getParts();
        this.rocks = new ArrayList<Rock>(this.rocksCount);
        Rock newRock;

        for (int i = 0; i < this.rocksCount; ++i) {
            do {
                newRock = new Rock(this.cellSize, this.width, this.height, snakeParts, 3);
            } while (checkRockOverlap(newRock.getCoord()));
            this.rocks.add(newRock);
        }
    }

    private void initFruits() {
        ArrayList<Coordinate> snakeParts = snake.getParts();
        this.fruits = new ArrayList<GameObject>(3);
        GameObject fruit;

        do {
            fruit = new Apple(this.cellSize, this.width, this.height, snakeParts, 3);
        } while (checkRockOverlap(fruit.getCoord()));
        fruits.add(fruit);
        do {
            fruit = new Banana(this.cellSize, this.width, this.height, snakeParts, 3);
        } while (checkRockOverlap(fruit.getCoord()));
        fruits.add(fruit);
        do {
            fruit = new Cherry(this.cellSize, this.width, this.height, snakeParts, 3);
        } while (checkRockOverlap(fruit.getCoord()));
        fruits.add(fruit);

        int randNum = this.rand.nextInt(3);

        switch (randNum) {
            case (0):
                this.currFruitInd = 0;
                break;
            case (1):
                this.currFruitInd = 1;
                break;
            case (2):
                this.currFruitInd = 2;
                break;
        }
    }

    private Boolean checkRockOverlap(Coordinate gameObjCoord) {
        for (Rock rock : this.rocks) {
            if (gameObjCoord.getX() == rock.getCoord().getX() && gameObjCoord.getY() == rock.getCoord().getY()) {
                return true;
            }
        }
        return false;
    }

    public int getCellSize() {
        return this.cellSize;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRocksCount() {
        return this.rocksCount;
    }

    public GameObject getCurrFruit() {
        return this.fruits.get(this.currFruitInd);
    }

    public ArrayList<Rock> getRocks() {
        return this.rocks;
    }

    public ArrayList<Coordinate> getSnakeParts() {
        return this.snake.getParts();
    }

    public int getSnakePartsCount() {
        return this.snake.getPartsCount();
    }

    public int getSnakeEaFrCount() {
        return this.snake.getEatenFruitsCount();
    }

    public BufferedImage getSnakeIconPart(int partInd) {
        return this.snake.getIconForPart(partInd);
    }

    public char getSnakeDirection() {
        return this.snake.getDirection();
    }

    public void setSnakeDirection(char direction) {
        this.snake.setDirection(direction);
    }

    public Boolean checkGameEnd() {
        return this.gameEnd;
    }

    public void setGameEnd(Boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public void genNextRandomFruit() {
        int randNum = this.rand.nextInt(3);

        switch (randNum) {
            case (0):
                this.currFruitInd = 0;
                break;
            case (1):
                this.currFruitInd = 1;
                break;
            case (2):
                this.currFruitInd = 2;
                break;
        }

        do {
            this.fruits.get(this.currFruitInd).genRandCoord(this.cellSize, this.width,
                    this.height, this.snake.getParts(), 3);
        } while (checkRockOverlap(this.fruits.get(this.currFruitInd).getCoord()));
    }

    public void moveSnake() {
        this.snake.move(this.cellSize);
    }

    public Boolean checkEatingFruit() {
        if (this.snake.getParts().get(0).getX() == this.fruits.get(this.currFruitInd).getCoord().getX()
                && this.snake.getParts().get(0).getY() == this.fruits.get(this.currFruitInd).getCoord().getY()) {
            this.snake.incPartsCount();
            this.snake.incEatenFruitsCount();
            this.snake.addNewPart();
            genNextRandomFruit();
            return true;
        }

        return false;
    }

    public Boolean checkCollision() {
        ArrayList<Coordinate> snakeParts = snake.getParts();
        Coordinate snakeHead = snakeParts.get(0);

        // board collision
        if (snakeHead.getX() < 0 || snakeHead.getY() < 0 ||
                snakeHead.getX() >= this.width || snakeHead.getY() >= this.height) {
            this.gameEnd = true;
        }

        // rock collision
        for (Rock rock : rocks) {
            if (snakeHead.getX() == rock.getCoord().getX() &&
                    snakeHead.getY() == rock.getCoord().getY()) {
                this.gameEnd = true;
                return this.gameEnd;
            }
        }

        // snake collision
        for (int i = this.snake.getPartsCount() - 1; i > 0; i--) {
            if (snakeHead.getX() == snakeParts.get(i).getX() &&
                    snakeHead.getY() == snakeParts.get(i).getY()) {
                this.gameEnd = true;
                return this.gameEnd;
            }
        }

        return this.gameEnd;
    }
}