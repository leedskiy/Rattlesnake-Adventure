package rattlesnakeadventure.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import rattlesnakeadventure.db.DatabaseManager;
import rattlesnakeadventure.model.GameManager;

public class GameWindow extends Window {
    private int cellSize;
    private int width;
    private int height;
    private int rocksCount;
    private boolean restart;
    private MenuBar menuBar;
    private Timer timer;
    private int elapsedTimeInSeconds;
    private JLabel elapsedTimeLabel;
    private JLabel eatenFrCnLabel;
    private JPanel gamePanel;
    private ArrayList<Window> windows;
    private GameManager gameManager;
    private DatabaseManager databaseManager;

    public GameWindow(ArrayList<Window> windows, DatabaseManager databaseManager,
            int cellSize, int width, int height, int rocksCount) {
        setTitle("Rattlesnake Adventure / game");
        this.windows = windows;
        this.databaseManager = databaseManager;
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        this.rocksCount = rocksCount;
        this.restart = false;
        this.gameManager = new GameManager(cellSize, width, height, rocksCount);
        this.gameManager.setGameEnd(false);

        // menuBar
        this.menuBar = new MenuBar(windows, databaseManager);
        setJMenuBar(this.menuBar);
        JMenuItem top10 = this.menuBar.getMenuTop10();
        JMenuItem res = this.menuBar.getMenuRestart();
        top10.setEnabled(false);
        res.setEnabled(true);
        res.addActionListener(e -> handleRestart());

        // topPanel
        JPanel topPanel = new JPanel();
        this.eatenFrCnLabel = new JLabel();
        this.eatenFrCnLabel.setText("Eaten fruits: 0");
        this.eatenFrCnLabel.setBorder(new EmptyBorder(0, 0, 0, 20));
        this.elapsedTimeLabel = new JLabel();
        this.elapsedTimeLabel.setText("Elapsed time: 0s");
        topPanel.add(this.eatenFrCnLabel);
        topPanel.add(this.elapsedTimeLabel);
        initTimerForTLabel();

        // gamePanel
        this.gamePanel = new GamePanel(this.gameManager, this);

        // contentPane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        // window size
        int windowWidth = getContentPane().getPreferredSize().width + getInsets().left + getInsets().right;
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

    public void handleRestart() {
        this.restart = true;
        this.timer.stop();
        this.gameManager.setGameEnd(true);
        Window newWindow = new GameWindow(this.windows, this.databaseManager, this.cellSize,
                this.width, this.height, this.rocksCount);
        newWindow.setVisible(true);
        this.dispose();
        windows.remove(this);
    }

    public boolean getRestart() {
        return restart;
    }

    public void updateEatenFrCnLabel() {
        eatenFrCnLabel.setText("Eaten fruits: " + gameManager.getSnakeEaFrCount());
    }

    private void initTimerForTLabel() {
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

    public void displayGameEnd() {
        this.timer.stop();
        String playerName = "";

        do {
            playerName = JOptionPane.showInputDialog(this, "Game Over! Enter your name:");

            if (playerName == null) { // cancel
                this.dispose();
                windows.remove(this);
                return;
            }

        } while (playerName.trim().isEmpty());

        databaseManager.saveScore(playerName, gameManager.getSnakeEaFrCount());
        this.dispose();
        windows.remove(this);
    }

    @Override
    protected void doOnExit() {
        this.gameManager.setGameEnd(true);
        this.timer.stop();
        super.doOnExit();
        this.windows.remove(this);
    }
}
