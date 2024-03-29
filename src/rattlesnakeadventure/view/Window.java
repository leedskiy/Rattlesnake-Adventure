package rattlesnakeadventure.view;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.JFrame;

public class Window extends JFrame {
    public Window() {
        setTitle("Rattlesnake Adventure");
        setSize(700, 400);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                doOnExit();
            }
        });

        URL url = Window.class.getResource("/rattlesnakeadventure/img/appicon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
    }

    protected void doOnExit() {
        this.dispose();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}