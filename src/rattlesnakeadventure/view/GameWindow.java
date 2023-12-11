package rattlesnakeadventure.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameWindow extends Window {
    private MenuBar menuBar;
    private Timer timer;
    private int elapsedTimeInSeconds;
    private ArrayList<Window> windows;

    public GameWindow(ArrayList<Window> windows) {
        setTitle("Rattlesnake Adventure / game");
        this.windows = windows;

        this.menuBar = new MenuBar(windows);
        setJMenuBar(this.menuBar);
        handleRestart();

        JPanel topPanel = new JPanel();

        addTimerToTopPanel(topPanel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);

        this.setVisible(true);
    }

    private void handleRestart() {
        JMenuItem res = this.menuBar.getMenuRestart();

        res.setEnabled(true);

        res.addActionListener(e -> {
            Window newWindow = new GameWindow(this.windows);
            newWindow.setVisible(true);
            this.dispose();
            windows.remove(this);
        });
    }

    private void addTimerToTopPanel(JPanel topPanel) {
        this.elapsedTimeInSeconds = 0;
        JLabel elapsedTimeLabel = new JLabel();

        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTimeInSeconds++;
                elapsedTimeLabel.setText("Elapsed time: " + elapsedTimeInSeconds + "s");
            }
        });

        this.timer.start();
        topPanel.add(elapsedTimeLabel);
    }

    @Override
    protected void doOnExit() {
        super.doOnExit();
        this.windows.remove(this);
    }
}
