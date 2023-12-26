package rattlesnakeadventure.view;

import javax.swing.*;

import rattlesnakeadventure.model.Coordinate;
import rattlesnakeadventure.model.GameManager;
import rattlesnakeadventure.model.object.GameObject;
import rattlesnakeadventure.model.object.Rock;

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
    private GameWindow gameWindow;

    public GamePanel(GameManager gameManager, GameWindow gameWindow) {
        this.gameManager = gameManager;
        this.gameWindow = gameWindow;
        this.delay = 177;
        this.setPreferredSize(new Dimension(gameManager.getWidth(),
                gameManager.getHeight()));
        this.setFocusable(true);
        Color color = Color.decode("#000000");
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

            // borders
            g.setColor(Color.decode("#262626"));
            for (int row = 0; row < gameManager.getHeight() / cellSize; row++) {
                for (int col = 0; col < gameManager.getWidth() / cellSize; col++) {
                    int x = col * cellSize;
                    int y = row * cellSize;

                    g.drawRect(x, y, cellSize, cellSize);
                }
            }

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
            if (!gameWindow.getRestart()) {
                gameWindow.displayGameEnd();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameManager.checkGameEnd()) {
            gameManager.moveSnake();
            if (gameManager.checkEatingFruit()) {
                gameWindow.updateEatenFrCnLabel();
            }
            gameManager.checkCollision();
        } else {
            timer.stop();
            if (!gameWindow.getRestart()) {
                gameWindow.displayGameEnd();
            }
        }
        repaint();
    }

    public class PanelKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    if (gameManager.getSnakeDirection() != 'D') {
                        gameManager.setSnakeDirection('U');
                    }
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if (gameManager.getSnakeDirection() != 'L') {
                        gameManager.setSnakeDirection('R');
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if (gameManager.getSnakeDirection() != 'U') {
                        gameManager.setSnakeDirection('D');
                    }
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if (gameManager.getSnakeDirection() != 'R') {
                        gameManager.setSnakeDirection('L');
                    }
                    break;
            }
        }
    }
}
