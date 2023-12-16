package rattlesnakeadventure.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Snake {
    private ArrayList<Coordinate> parts;
    private int partsCount;
    private int eatenFruitsCount;
    private char direction;
    private Random rand;
    private ArrayList<ArrayList<BufferedImage>> icons; // 3x4

    public Snake(int cellSize, int width, int height) {
        this.partsCount = 2;
        this.eatenFruitsCount = 0;
        this.icons = new ArrayList<ArrayList<BufferedImage>>();
        initRandomDirection();
        initParts(cellSize, width, height);
        initIcons();
    }

    private void initRandomDirection() {
        this.rand = new Random();
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

    private void initParts(int cellSize, int width, int height) {
        this.parts = new ArrayList<Coordinate>();

        int initialX = (width / 2) - ((width / 2) % cellSize);
        int initialY = (height / 2) - ((height / 2) % cellSize);

        parts.add(new Coordinate(initialX, initialY));
        switch (this.direction) {
            case 'U':
                this.parts.add(new Coordinate(initialX, initialY + cellSize));
                break;
            case 'R':
                this.parts.add(new Coordinate(initialX - cellSize, initialY));
                break;
            case 'D':
                this.parts.add(new Coordinate(initialX, initialY - cellSize));
                break;
            case 'L':
                this.parts.add(new Coordinate(initialX + cellSize, initialY));
                break;
        }

        parts.add(new Coordinate(0, 0));
    }

    private void initIcons() {
        initPartIcons("/rattlesnakeadventure/img/snake/snakeHeadUp.png",
                "/rattlesnakeadventure/img/snake/snakeHeadRight.png",
                "/rattlesnakeadventure/img/snake/snakeHeadDown.png",
                "/rattlesnakeadventure/img/snake/snakeHeadLeft.png");

        initPartIcons("/rattlesnakeadventure/img/snake/snakeBodyUp.png",
                "/rattlesnakeadventure/img/snake/snakeBodyRight.png",
                "/rattlesnakeadventure/img/snake/snakeBodyDown.png",
                "/rattlesnakeadventure/img/snake/snakeBodyLeft.png");

        initPartIcons("/rattlesnakeadventure/img/snake/snakeTailUp.png",
                "/rattlesnakeadventure/img/snake/snakeTailRight.png",
                "/rattlesnakeadventure/img/snake/snakeTailDown.png",
                "/rattlesnakeadventure/img/snake/snakeTailLeft.png");
    }

    private void initPartIcons(String upPath, String rightPath, String downPath, String leftPath) {
        ArrayList<BufferedImage> partIcons = new ArrayList<>();

        partIcons.add(loadIcon(upPath));
        partIcons.add(loadIcon(rightPath));
        partIcons.add(loadIcon(downPath));
        partIcons.add(loadIcon(leftPath));

        this.icons.add(partIcons);
    }

    private BufferedImage loadIcon(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);

            if (inputStream != null) {
                return ImageIO.read(inputStream);
            } else {
                System.err.println("Can not find image: " + filePath);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Coordinate> getParts() {
        return this.parts;
    }

    public int getPartsCount() {
        return this.partsCount;
    }

    public int getEatenFruitsCount() {
        return this.eatenFruitsCount;
    }

    public BufferedImage getIconForPart(int partInd) {
        if (partInd == 0) {
            switch (direction) {
                case 'U':
                    return icons.get(0).get(0);
                case 'R':
                    return icons.get(0).get(1);
                case 'D':
                    return icons.get(0).get(2);
                case 'L':
                    return icons.get(0).get(3);
            }
        } else if (partInd == partsCount - 1) {
            switch (direction) {
                case 'U':
                    return icons.get(2).get(0);
                case 'R':
                    return icons.get(2).get(1);
                case 'D':
                    return icons.get(2).get(2);
                case 'L':
                    return icons.get(2).get(3);
            }
        } else {
            switch (direction) {
                case 'U':
                    return icons.get(1).get(0);
                case 'R':
                    return icons.get(1).get(1);
                case 'D':
                    return icons.get(1).get(2);
                case 'L':
                    return icons.get(1).get(3);
            }
        }

        return null;
    }

    public void incPartsCount() {
        ++this.partsCount;
    }

    public char getDirection() {
        return this.direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void incEatenFruitsCount() {
        ++this.eatenFruitsCount;
    }

    public void move(int cellSize) {
        for (int i = this.partsCount - 1; i > 0; i--) {
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

    public void addNewPart() {
        parts.add(new Coordinate(0, 0));
    }
}