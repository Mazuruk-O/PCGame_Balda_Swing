import GUI.*;
import entity.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            Player player1 = new Player("SimpleName");
            Player player2 = new Player();
            GameField gameField = new GameField();
            GameFieldFrame gameFieldFrame = new GameFieldFrame();
            GameController gameController = new GameController(gameField,player1,player2,gameFieldFrame);
            mainFrame.add(gameFieldFrame);
            mainFrame.setVisible(true);
        });
    }
}
