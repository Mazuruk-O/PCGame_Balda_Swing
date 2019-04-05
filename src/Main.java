import GUI.GameFieldFrame;
import GUI.MainFrame;
import entity.GameField;
import entity.Player;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.add(new GameFieldFrame(new GameField(),new Player(),new Player()));
            mainFrame.setVisible(true);
        });
    }
}
