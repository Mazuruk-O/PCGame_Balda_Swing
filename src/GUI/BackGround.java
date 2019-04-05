package GUI;

import img.Images;
import javax.swing.*;
import java.awt.*;

public class BackGround extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Images.background,0,0,null);
    }
}
