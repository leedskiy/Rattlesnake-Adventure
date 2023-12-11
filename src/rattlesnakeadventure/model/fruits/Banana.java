package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import rattlesnakeadventure.model.Coordinate;

public class Banana extends GameObject {
    public Banana(int cellSize, int rowCellsCount, int colCellsCount, ArrayList<Coordinate> snakeParts) {
        super(rowCellsCount, colCellsCount, cellSize, snakeParts);

        try {
            InputStream inputStream = getClass().getResourceAsStream("banana.png");
            if (inputStream != null) {
                this.icon = ImageIO.read(inputStream);
            } else {
                System.err.println("Can not find image: banana.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getIcon() {
        return this.icon;
    }
}
