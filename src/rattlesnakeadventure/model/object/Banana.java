package rattlesnakeadventure.model.object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import rattlesnakeadventure.model.Coordinate;

public class Banana extends GameObject {
    public Banana(int cellSize, int width, int height, ArrayList<Coordinate> snakeParts, int areaSize) {
        super(cellSize, width, height, snakeParts, areaSize);

        try {
            String filePath = "/rattlesnakeadventure/img/banana.png";
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            if (inputStream != null) {
                this.icon = ImageIO.read(inputStream);
            } else {
                System.err.println("Can not find image: " + filePath);
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
