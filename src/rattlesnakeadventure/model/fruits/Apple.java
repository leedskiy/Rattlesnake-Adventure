package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Apple extends Fruit {
    public Apple(int width, int height, int cellSize) {
        super(width, height, cellSize);

        try {
            InputStream inputStream = getClass().getResourceAsStream("apple.png");
            if (inputStream != null) {
                this.icon = ImageIO.read(inputStream);
            } else {
                System.err.println("Can not find image: apple.png");
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
