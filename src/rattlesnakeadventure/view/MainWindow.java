package rattlesnakeadventure.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class MainWindow extends Window {

    private ArrayList<Window> windows;

    public MainWindow() {
        windows = new ArrayList<Window>();
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
                // Window window = new GameWindow(MainWindow.this);
                // windows.add(window);
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
}
