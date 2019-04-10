package entity.interf;

import GUI.GameFieldFrame;
import entity.ConstSIZE;
import entity.TextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class GameController {
    private PlayerIntf player_1;
    private PlayerIntf player_2;
    private GameFieldIntf gameFieldIntf;
    private GameFieldFrame gameFieldFrame;
    private char stepChar;
    private int rowsChengeGameField,columChengeGameField;
    private int count_all_step = 0;

    public GameController(GameFieldIntf field,PlayerIntf pl1, PlayerIntf pl2, GameFieldFrame gameFieldFrame){
        player_1 = pl1;
        player_2 = pl2;
        gameFieldIntf = field;
        this.gameFieldFrame = gameFieldFrame;
        setActionButton();
        rowsChengeGameField=-1;
        columChengeGameField=-1;
    }

    private void setActionButton() {
        gameFieldFrame.getNextStep().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(!((rowsChengeGameField == -1) && (columChengeGameField == -1) && (stepChar == '\u0000')))
                    gameFieldIntf.setSection(rowsChengeGameField,columChengeGameField,stepChar);

                stepChar = '\u0000';
                rowsChengeGameField=-1;
                columChengeGameField=-1;

                /*if(controllGame()){
                    player_1.addToScore(wordStep.size());
                }

                wordStep.clear();*/

                unblockTextField();
            }
        });

        gameFieldFrame.getReplayStep().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(!((rowsChengeGameField == -1) && (columChengeGameField == -1) && (stepChar == '\u0000'))){
                    unblockTextField();
                    gameFieldFrame.getTextField()[rowsChengeGameField][columChengeGameField].setText("");
                }

                stepChar = '\u0000';
                rowsChengeGameField=-1;
                columChengeGameField=-1;
                //wordStep.clear();

                return;
            }
        });

        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++) {
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++) {
                TextField tmp = gameFieldFrame.getTextField(i,j);
                tmp.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        //GameFieldFrame.this.gameField.getField()[i][j]
                        GameController.this.blockTextField();
                        GameController.this.stepChar = tmp.getText().charAt(0);

                        rowsChengeGameField = tmp.getI();
                        columChengeGameField = tmp.getJ();
                        //TextField.this.setDocument(lengthRestrictedDocument_0);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        //GameFieldGui.this.textField[i][j].setText(""+GameFieldGui.this.gamefield.getField()[i][j]);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        // тут сам поставишь Syste.out и проверишь что будет=)
                    }
                });
            }
        }
    }

    /*private boolean controllGame(){
        int sizeSet = wordStep.size();
        Pair first = wordStep.get(0), second = wordStep.get(1);
        for (int i = 2; i < sizeSet; i++) {
            if(first.equals(second))
                continue;
            else if(Math.abs(((Integer) first.left-(Integer)second.left)+((Integer)first.right-(Integer)second.right)) == 1)
            {
                first = second;
                second = wordStep.get(i);
            }
            else
                return false;
        }
        return true;
    }*/

    private void blockTextField(){
        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++)
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++)
                this.gameFieldFrame.getTextField(i,j).setEnabled(false);
    }

    private void unblockTextField(){
        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++)
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++){
                if(gameFieldIntf.checkSection(i,j))
                    this.gameFieldFrame.getTextField(i,j).setEnabled(true);

            }
    }
}
