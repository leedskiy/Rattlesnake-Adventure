package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Banana extends Fruit {
    public Banana(int width, int height, int cellSize) {
        super(width, height, cellSize);

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
