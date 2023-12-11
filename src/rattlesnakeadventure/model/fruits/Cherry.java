package rattlesnakeadventure.model.fruits;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Cherry extends GameObject {
    public Cherry(int cellSize, int rowCellsCount, int colCellsCount) {
        super(rowCellsCount, colCellsCount, cellSize);

        try {
            InputStream inputStream = getClass().getResourceAsStream("cherry.png");
            if (inputStream != null) {
                this.icon = ImageIO.read(inputStream);
            } else {
                System.err.println("Can not find image: cherry.png");
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
