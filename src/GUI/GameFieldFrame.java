package GUI;

import entity.ConstSIZE;
import entity.TextField;
import javax.swing.*;
import java.awt.*;

public class GameFieldFrame extends JPanel {
    private JLabel player1Name;
    private JLabel player2Name;
    private JLabel player1Score;
    private JLabel player2Score;
    private JButton backToMainFrame;
    private JButton nextStep;
    private JButton replayStep;
    private TextField textField[][];

    public GameFieldFrame(){
        super();
        textField = new TextField[ConstSIZE.SIZE_FIELD][ConstSIZE.SIZE_FIELD];
        setLayout(new GridLayout(ConstSIZE.SIZE_FIELD+4,ConstSIZE.SIZE_FIELD));
        addButtonBackToMain();
        addScore();
        addTile();
        addButtonCheckStep();
        return;
    }

    private void addButtonCheckStep() {
        replayStep = new JButton("Hе існує");
        this.add(replayStep);
        addJLabel(ConstSIZE.SIZE_FIELD-2);
        nextStep = new JButton("Bірнo");
        this.add(nextStep);
    }

    private void addTile(){
        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++) {
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++) {
                textField[i][j] = new TextField(i,j);
                this.add(textField[i][j]);
            }
        }
    }

    private void addScore(){
        player1Name = new JLabel("",SwingConstants.CENTER);
        player1Score = new JLabel("",SwingConstants.CENTER);
        player2Score = new JLabel("",SwingConstants.CENTER);
        player2Name = new JLabel("",SwingConstants.CENTER);
        this.add(player1Name);
        this.add(player1Score);
        this.add(new JLabel(":",SwingConstants.CENTER));
        this.add(player2Score);
        this.add(player2Name);
        return;
    }

    private void addButtonBackToMain(){
        backToMainFrame = new JButton("<- На головну");
        this.add(backToMainFrame);
        addJLabel(ConstSIZE.SIZE_FIELD-1);
    }

    private void addJLabel(int count){
        for (int i = 0; i < count; i++) {
            this.add(new JLabel());
        }
        return;
    }

    public JButton getBackToMainFrame() {
        return backToMainFrame;
    }

    public JButton getNextStep() {
        return nextStep;
    }

    public JButton getReplayStep() {
        return replayStep;
    }

    public TextField[][] getTextField() {
        return textField;
    }

    public TextField getTextField(int i, int j) {
        if(i < 0 || i > ConstSIZE.SIZE_FIELD || j < 0 || j > ConstSIZE.SIZE_FIELD)
            return new TextField(-1,-1);
        return textField[i][j];
    }

    public JLabel getPlayer1Name() {
        return player1Name;
    }

    public JLabel getPlayer2Name() {
        return player2Name;
    }

    public JLabel getPlayer1Score() {
        return player1Score;
    }

    public JLabel getPlayer2Score() {
        return player2Score;
    }
}

