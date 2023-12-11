package rattlesnakeadventure.model;

import java.util.ArrayList;
import java.util.Random;

import rattlesnakeadventure.model.fruits.GameObject;
import rattlesnakeadventure.model.fruits.Apple;
import rattlesnakeadventure.model.fruits.Banana;
import rattlesnakeadventure.model.fruits.Cherry;
import rattlesnakeadventure.model.fruits.Rock;

public class GameManager {
    private int cellSize;
    private int rowCellsCount;
    private int colCellsCount;
    private int totalCellsCount;
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
        this.rowCellsCount = width / cellSize;
        this.colCellsCount = height / cellSize;
        this.totalCellsCount = rowCellsCount * colCellsCount;
        this.rocksCount = rocksCount;
        snake = new Snake(this.cellSize, this.rowCellsCount, this.colCellsCount);
        initFruits();
        initRocks();
        this.gameEnd = false;
    }

    private void initFruits() {
        ArrayList<Coordinate> snakeParts = snake.getParts();
        this.fruits = new ArrayList<GameObject>(3);
        this.fruits.add(new Apple(this.cellSize, this.rowCellsCount, this.colCellsCount, snakeParts));
        this.fruits.add(new Banana(this.cellSize, this.rowCellsCount, this.colCellsCount, snakeParts));
        this.fruits.add(new Cherry(this.cellSize, this.rowCellsCount, this.colCellsCount, snakeParts));

        switch (this.rand.nextInt(3)) {
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

    private void initRocks() {
        ArrayList<Coordinate> snakeParts = snake.getParts();
        this.rocks = new ArrayList<Rock>(this.rocksCount);

        for (int i = 0; i < this.rocksCount; ++i) {
            this.rocks.add(new Rock(this.cellSize, this.rowCellsCount, this.colCellsCount, snakeParts));
        }
    }

    public int getCellSize() {
        return this.cellSize;
    }

    public int getRowCellsCount() {
        return this.rowCellsCount;
    }

    public int getColCellsCount() {
        return this.colCellsCount;
    }

    public int getTotalCellsCount() {
        return this.totalCellsCount;
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
        return snake.getParts();
    }

    public int getSnakePartsCount() {
        return snake.getPartsCount();
    }

    public int getSnakeEaFrCount() {
        return snake.getEatenFruitsCount();
    }

    public Boolean checkGameEnd() {
        return gameEnd;
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

        this.fruits.get(this.currFruitInd).genRandCoord(this.cellSize, this.rowCellsCount, this.colCellsCount,
                this.snake.getParts());
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

    public Boolean checkCollission() {
        ArrayList<Coordinate> snakeParts = snake.getParts();
        Coordinate snakeHead = snakeParts.get(0);

        int width = this.rowCellsCount * this.cellSize;
        int height = this.colCellsCount * this.cellSize;

        if (snakeHead.getX() < 0 || snakeHead.getY() < 0 ||
                snakeHead.getX() >= width || snakeHead.getY() >= height) {
            this.gameEnd = true;
        }

        for (Rock rock : rocks) {
            if (snakeHead.getX() == rock.getCoord().getX() &&
                    snakeHead.getY() == rock.getCoord().getY()) {
                this.gameEnd = true;
                return this.gameEnd;
            }
        }

        for (int i = this.snake.getPartsCount(); i > 0; i--) {
            if (snakeHead.getX() == snakeParts.get(i).getX() &&
                    snakeHead.getY() == snakeParts.get(i).getY()) {
                this.gameEnd = true;
                return this.gameEnd;
            }
        }

        return this.gameEnd;
    }
}