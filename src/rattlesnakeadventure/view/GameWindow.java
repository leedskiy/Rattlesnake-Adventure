package rattlesnakeadventure.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

import rattlesnakeadventure.model.GameManager;

public class GameWindow extends Window {
    private MenuBar menuBar;
    private Timer timer;
    private int elapsedTimeInSeconds;
    private JLabel elapsedTimeLabel;
    private ArrayList<Window> windows;
    private GameManager gameManager;

    public GameWindow(ArrayList<Window> windows, GameManager gameManager) {
        setTitle("Rattlesnake Adventure / game");
        this.windows = windows;
        this.gameManager = gameManager;

        // menuBar
        this.menuBar = new MenuBar(windows);
        setJMenuBar(this.menuBar);
        handleRestart();

        // topPanel
        JPanel topPanel = new JPanel();
        this.elapsedTimeLabel = new JLabel();
        this.elapsedTimeLabel.setText("Elapsed time: 0s");
        topPanel.add(elapsedTimeLabel);
        initTimer();

        // gamePanel
        JPanel gamePanel = new GamePanel(gameManager);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        // window size
        int panelWidth = gameManager.getRowCellsCount() * gameManager.getCellSize();
        int panelHeight = gameManager.getColCellsCount() * gameManager.getCellSize();
        int windowWidth = panelWidth + getInsets().left + getInsets().right;
        int windowHeight = panelHeight + topPanel.getPreferredSize().height + getInsets().top + getInsets().bottom;
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
        JMenuItem res = this.menuBar.getMenuRestart();

        res.setEnabled(true);

        res.addActionListener(e -> {
            Window newWindow = new GameWindow(this.windows, this.gameManager);
            newWindow.setVisible(true);
            this.dispose();
            windows.remove(this);
        });
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
        super.doOnExit();
        this.windows.remove(this);
    }
}
