package rattlesnakeadventure.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuBar extends JMenuBar {
    private JMenuItem menuRestart;
    private ArrayList<Window> windows;

    public MenuBar(ArrayList<Window> windows) {
        this.windows = windows;

        JMenu menu = new JMenu("Menu");

        JMenuItem menuTop10 = new JMenuItem("Top10 scoreboard");
        menuTop10.addActionListener(showTop10List());

        this.menuRestart = new JMenuItem("Restart");
        this.menuRestart.setEnabled(false);

        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(exit());

        menu.add(menuTop10);
        menu.add(menuRestart);
        menu.addSeparator();
        menu.add(menuExit);
        add(menu);
    }

    private ActionListener exit() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
    }

    private ActionListener showTop10List() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window top10Window = new Top10Window(windows);
                windows.add(top10Window);
            }
        };
    }

    public JMenuItem getMenuRestart() {
        return this.menuRestart;
    }
}