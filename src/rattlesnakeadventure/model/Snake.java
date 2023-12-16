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
    private BufferedImage headIcon;
    private BufferedImage bodyIcon;
    private BufferedImage tailIcon;

    public Snake(int cellSize, int width, int height) {
        this.partsCount = 2;
        this.eatenFruitsCount = 0;
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
        try {
            String filePath = "/rattlesnakeadventure/img/snakeHeadTemp.png";
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            if (inputStream != null) {
                this.headIcon = ImageIO.read(inputStream);
            } else {
                System.err.println("Can not find image: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String filePath = "/rattlesnakeadventure/img/snakeBodyTemp.png";
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            if (inputStream != null) {
                this.bodyIcon = ImageIO.read(inputStream);
            } else {
                System.err.println("Can not find image: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String filePath = "/rattlesnakeadventure/img/snakeTailTemp.png";
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            if (inputStream != null) {
                this.tailIcon = ImageIO.read(inputStream);
            } else {
                System.err.println("Can not find image: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            return this.headIcon;
        } else if (partInd == this.partsCount - 1) {
            return this.tailIcon;
        } else {
            return this.bodyIcon;
        }
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