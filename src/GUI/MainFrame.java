package GUI;

import img.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private Toolkit toolkit;
    private Dimension dimension;
    private JLayeredPane jLayeredPane;

    public MainFrame(){
        // заголовок
        super("Balda");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // встановлення іконки
        this.setIconImage(Images.icon);
/**
        //LayeredPane для декількох шарів
        jLayeredPane = new JLayeredPane();
        // об*єкт для відмальовки фону
        // додання поточному вікну - об*єкта для відмальовки декількох шарів
        this.setLayeredPane(jLayeredPane);

        // встановлення фону
        BackGround backGround = new BackGround();
        backGround.setBounds(0,0,500,500);
        jLayeredPane.add(backGround, JLayeredPane.FRAME_CONTENT_LAYER);

        // додання кнопок головному вікнові
        addButton();
*/
        // отримання поточного розміру екрану
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        // встановлення розміру вікна і його стартової позиції
        this.setBounds(dimension.width/2-250,dimension.height/2-250,500,500);
        // заборона зміни розмірів вінка
        this.setResizable(false);
    }

    private void addButton() {//
        JButton buttonContinueGame = new JButton("Продовжити");
        JButton buttonTopPlayer = new JButton("Топ гравців");
        JButton buttonSettingGame = new JButton("Налаштування");
        JButton buttonExitGame = new JButton("Вихід");
        JButton buttonNewGame = new JButton("Нова гра");
        /** buttonNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                SwingUtilities.invokeLater(() -> {
                    buttonNewGame.setVisible(false);
                    buttonContinueGame.setVisible(false);
                    buttonTopPlayer.setVisible(false);
                    buttonSettingGame.setVisible(false);
                    buttonExitGame.setVisible(false);
                });
            }
        }); **/
        buttonNewGame.setBounds(180,50,125,25);
        buttonContinueGame.setBounds(180,100,125,25);
        buttonTopPlayer.setBounds(180,150,125,25);
        buttonSettingGame.setBounds(180,200,125,25);
        buttonExitGame.setBounds(180,250,125,25);
        jLayeredPane.add(buttonNewGame,JLayeredPane.PALETTE_LAYER);
        jLayeredPane.add(buttonContinueGame,JLayeredPane.PALETTE_LAYER);
        jLayeredPane.add(buttonTopPlayer,JLayeredPane.PALETTE_LAYER);
        jLayeredPane.add(buttonSettingGame,JLayeredPane.PALETTE_LAYER);
        jLayeredPane.add(buttonExitGame,JLayeredPane.PALETTE_LAYER);
        return;
    }
}
