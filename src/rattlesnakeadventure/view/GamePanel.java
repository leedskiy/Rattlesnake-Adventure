package rattlesnakeadventure.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import rattlesnakeadventure.model.GameManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public class PanelKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
        }
    }

}
