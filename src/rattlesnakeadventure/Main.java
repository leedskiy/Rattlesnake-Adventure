package rattlesnakeadventure;

import rattlesnakeadventure.model.GameManager;
import rattlesnakeadventure.view.MainWindow;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager(40, 800, 500, 5);
        MainWindow mainWindow = new MainWindow(gameManager);
    }
}