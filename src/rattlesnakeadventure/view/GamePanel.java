package rattlesnakeadventure.view;

import javax.swing.*;

import rattlesnakeadventure.model.GameManager;
import rattlesnakeadventure.model.fruits.GameObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

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
        Color color = Color.decode("#C59664");
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
            g.drawImage(currFruitIcon, currFruit.getCoord().getX(), currFruit.getCoord().getY(),
                    cellSize, cellSize, null);
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
