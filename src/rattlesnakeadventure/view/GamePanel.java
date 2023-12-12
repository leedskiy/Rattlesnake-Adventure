package rattlesnakeadventure.view;

import javax.swing.*;

import rattlesnakeadventure.model.Coordinate;
import rattlesnakeadventure.model.GameManager;
import rattlesnakeadventure.model.Snake;
import rattlesnakeadventure.model.fruits.GameObject;
import rattlesnakeadventure.model.fruits.Rock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private int delay;
    private Timer timer;
    private GameManager gameManager;

    public GamePanel(GameManager gameManager) {
        this.gameManager = gameManager;

        int cellSize = gameManager.getCellSize();
        this.setPreferredSize(new Dimension(gameManager.getRowCellsCount() * cellSize,
                gameManager.getColCellsCount() * cellSize));
        this.setFocusable(true);
        Color color = Color.decode("#A5A265");
        this.setBackground(color);
        this.addKeyListener(new PanelKeyAdapter());
        start();
    }

    public void start() {
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    public void drawBoard(Graphics g) {
        if (!gameManager.checkGameEnd()) {
            int cellSize = gameManager.getCellSize();
            GameObject currFruit = gameManager.getCurrFruit();
            BufferedImage currFruitIcon = currFruit.getIcon();

            // fruit
            g.drawImage(currFruitIcon, currFruit.getCoord().getX(), currFruit.getCoord().getY(),
                    cellSize, cellSize, null);

            // rocks
            ArrayList<Rock> rocks = gameManager.getRocks();

            for (Rock rock : rocks) {
                g.drawImage(rock.getIcon(), rock.getCoord().getX(), rock.getCoord().getY(),
                        cellSize, cellSize, null);
            }

            // snake
            ArrayList<Coordinate> snakeParts = gameManager.getSnakeParts();

            for (int i = 0; i < gameManager.getSnakePartsCount(); i++) {
                g.drawImage(gameManager.getSnakeIconPart(i), snakeParts.get(i).getX(),
                        snakeParts.get(i).getY(), cellSize, cellSize, null);
            }
        } else {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameManager.checkGameEnd()) {

        } else {
            timer.stop();
        }
        repaint();
    }

    public class PanelKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
        }
    }
}
