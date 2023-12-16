package rattlesnakeadventure.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TreeUI;

import rattlesnakeadventure.model.GameManager;

public class GameWindow extends Window {
    private int cellSize;
    private int width;
    private int height;
    private int rocksCount;
    private MenuBar menuBar;
    private Timer timer;
    private int elapsedTimeInSeconds;
    private JLabel elapsedTimeLabel;
    private JPanel gamePanel;
    private ArrayList<Window> windows;
    private GameManager gameManager;

    public GameWindow(ArrayList<Window> windows, int cellSize, int width, int height, int rocksCount) {
        setTitle("Rattlesnake Adventure / game");
        this.windows = windows;
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        this.rocksCount = rocksCount;
        this.gameManager = new GameManager(cellSize, width, height, rocksCount);
        this.gameManager.setGameEnd(false);

        // menuBar
        this.menuBar = new MenuBar(windows);
        setJMenuBar(this.menuBar);
        JMenuItem res = this.menuBar.getMenuRestart();
        res.setEnabled(true);
        res.addActionListener(e -> handleRestart());

        // topPanel
        JPanel topPanel = new JPanel();
        this.elapsedTimeLabel = new JLabel();
        this.elapsedTimeLabel.setText("Elapsed time: 0s");
        topPanel.add(elapsedTimeLabel);
        initTimer();

        // gamePanel
        this.gamePanel = new GamePanel(gameManager);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        // window size
        int windowWidth = getContentPane().getPreferredSize().width + getInsets().left + getInsets().right + 15;
        int windowHeight = getContentPane().getPreferredSize().height + topPanel.getPreferredSize().height +
                getInsets().top + getInsets().bottom + 15;
        setPreferredSize(new Dimension(windowWidth, windowHeight));

        // window placement
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() - windowWidth) / 2;
        int centerY = (int) (screenSize.getHeight() - windowHeight) / 2;
        setLocation(centerX, centerY);

        pack();

        this.setVisible(true);
    }

    private void handleRestart() {
        this.timer.stop();
        this.gameManager.setGameEnd(true);
        Window newWindow = new GameWindow(this.windows, this.cellSize, this.width, this.height, this.rocksCount);
        newWindow.setVisible(true);
        this.dispose();
        windows.remove(this);
    }

    private void initTimer() {
        this.elapsedTimeInSeconds = 0;

        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTimeInSeconds++;
                elapsedTimeLabel.setText("Elapsed time: " + elapsedTimeInSeconds + "s");
            }
        });

        this.timer.start();
    }

    @Override
    protected void doOnExit() {
        this.gameManager.setGameEnd(true);
        this.timer.stop();
        super.doOnExit();
        this.windows.remove(this);
    }
}
