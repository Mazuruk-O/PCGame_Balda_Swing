package entity;

import GUI.GameFieldFrame;
import entity.interf.GameFieldIntf;
import entity.interf.PlayerIntf;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameController {
    private final Color newBackgroundColor;
    private PlayerIntf player_1;
    private PlayerIntf player_2;
    private GameFieldIntf gameFieldIntf;
    private GameFieldFrame gameFieldFrame;
    private char stepChar;
    private int rowsChengeGameField,columChengeGameField;
    private int count_all_step = 0;
    private StringBuilder newWord;
    private boolean isInputWord = false;

    public GameController(GameFieldIntf field,PlayerIntf pl1, PlayerIntf pl2, GameFieldFrame gameFieldFrame){
        newBackgroundColor = new Color(48, 139, 102);
        player_1 = pl1;
        player_2 = pl2;
        gameFieldIntf = field;
        this.gameFieldFrame = gameFieldFrame;
        setActionButton();
        setJLabel();
        blockTextField();
        rowsChengeGameField=-1;
        columChengeGameField=-1;
        unblockTextField();
        gameFieldFrame.getPlayer1Name().setBackground(new Color(97, 72, 36, 231));
        gameFieldFrame.getPlayer2Name().setBackground(Color.WHITE);
        gameFieldFrame.getPlayer1Name().setOpaque(true);
        gameFieldFrame.getPlayer2Name().setOpaque(true);
    }

    private void setActionButton() {
        gameFieldFrame.getNextStep().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(!((rowsChengeGameField == -1) && (columChengeGameField == -1)
                        && (stepChar == '\u0000')) && (newWord != null)){
                    gameFieldIntf.setSection(rowsChengeGameField,columChengeGameField,stepChar);
                    setNewScore();
                    System.out.println("add: " + stepChar);
                }

                clearValue();
                unblockTextField();
            }
        });

        gameFieldFrame.getReplayStep().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(!((rowsChengeGameField == -1) && (columChengeGameField == -1) && (stepChar == '\u0000'))){
                    gameFieldFrame.getTextField()[rowsChengeGameField][columChengeGameField].setText("");
                    unblockTextField();
                }

                clearValue();

                return;
            }
        });

        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++) {
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++) {
                TextField tmp = gameFieldFrame.getTextField(i,j);

                if(!gameFieldIntf.checkSection(i,j))
                    tmp.setText(Character.toString(gameFieldIntf.getField()[i][j]));

                tmp.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        //GameFieldFrame.this.gameField.getField()[i][j]
                        GameController.this.blockTextField();
                        newWord = (newWord == null ) ? new StringBuilder() : newWord;
                        GameController.this.stepChar = tmp.getText().charAt(0);
                        isInputWord = true;

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

        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++) {
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++) {
                TextField tmp = gameFieldFrame.getTextField(i,j);
                tmp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        if(!tmp.getText().equals("") && isInputWord && tmp.getText() != null){
                            newWord.append(tmp.getText().charAt(0));
                            tmp.setBackground(newBackgroundColor);
                        }
                    }
                });
            }
        }
    }

    private void blockTextField(){
        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++)
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++)
                this.gameFieldFrame.getTextField(i,j).setEnabled(false);

        return;
    }

    private void unblockTextField(){
        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++) {
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++) {
                if (gameFieldIntf.checkSection(i, j))
                    this.gameFieldFrame.getTextField(i, j).setEnabled(true);

                this.gameFieldFrame.getTextField(i, j).setBackground(Color.WHITE);
            }
        }

        return;
    }

    private void clearValue(){
        stepChar = '\u0000';
        rowsChengeGameField=-1;
        columChengeGameField=-1;
        isInputWord = false;
        newWord = null;

        return;
    }

    private void setJLabel(){
        gameFieldFrame.getPlayer1Name().setText(player_1.getName());
        gameFieldFrame.getPlayer2Name().setText(player_2.getName());
        gameFieldFrame.getPlayer1Score().setText(Integer.toString(player_1.getScore()));
        gameFieldFrame.getPlayer2Score().setText(Integer.toString(player_2.getScore()));
        return;
    }

    private void setNewScore(){
        ++count_all_step;

        if(count_all_step % 2 == 0)
        {
            player_2.addToScore(newWord.length());
            gameFieldFrame.getPlayer2Score().setText(Integer.toString(player_2.getScore()));
            gameFieldFrame.getPlayer1Name().setBackground(new Color(97, 72, 36, 231));
            gameFieldFrame.getPlayer2Name().setBackground(Color.WHITE);
        }
        else
        {
            player_1.addToScore(newWord.length());
            gameFieldFrame.getPlayer1Score().setText(Integer.toString(player_1.getScore()));
            gameFieldFrame.getPlayer2Name().setBackground(new Color(97, 72, 36, 231));
            gameFieldFrame.getPlayer1Name().setBackground(Color.WHITE);
        }

        return;
    }
}
