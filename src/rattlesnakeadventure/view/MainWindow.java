package rattlesnakeadventure.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import rattlesnakeadventure.model.GameManager;

import rattlesnakeadventure.model.GameManager;

public class MainWindow extends Window {
    private ArrayList<Window> windows;
    private GameManager gameManager;
    private int cellSize;
    private int width;
    private int height;
    private int rocksCount;

    public MainWindow(int cellSize, int width, int height, int rocksCount) {
        this.windows = new ArrayList<Window>();
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        this.rocksCount = rocksCount;

        MenuBar menuBar = new MenuBar(windows);
        setJMenuBar(menuBar);

        JLabel label = new JLabel();
        label.setBorder(new EmptyBorder(20, 0, 20, 0));
        label.setText("Welcome to \"Rattlesnake Adventure\"!");

        JButton startButton = createButton("Start a new game");
        startButton.setBorder(new EmptyBorder(13, 13, 13, 13));

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(label);
        getContentPane().add(startButton);

        this.setVisible(true);
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new GameWindow(windows, cellSize, width, height, rocksCount);
                windows.add(window);
            }
        };
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(getActionListener());
        return button;
    }

    public ArrayList<Window> getAllWindows() {
        return windows;
    }

    @Override
    protected void doOnExit() {
        super.doOnExit();
    }
}
