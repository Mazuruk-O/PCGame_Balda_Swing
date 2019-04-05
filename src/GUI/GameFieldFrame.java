package GUI;

import entity.ConstSIZE;
import entity.interf.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class GameFieldFrame extends JPanel {
    private PlayerIntf player_1;
    private PlayerIntf player_2;
    private GameFieldIntf gameField;
    private JButton backToMainFrame;
    private JButton nextStep;
    private JButton replayStep;
    private TextField textField[][];
    private char stepChar;
    private int rowsChengeGameField,columChengeGameField;
    private Set<Pair<Integer,Integer>> wordStep;

    public GameFieldFrame(GameFieldIntf field,PlayerIntf pl1, PlayerIntf pl2){
        super();
        gameField = field;
        player_1 = pl1;
        player_2 = pl2;
        textField = new TextField[ConstSIZE.SIZE_FIELD][ConstSIZE.SIZE_FIELD];
        setLayout(new GridLayout(ConstSIZE.SIZE_FIELD+4,ConstSIZE.SIZE_FIELD));
        addButtonBackToMain();
        addScore();
        addTile();
        addButtonCheckStep();
        rowsChengeGameField=-1;
        columChengeGameField=-1;
        wordStep = new HashSet<>();
        return;
    }

    private void addButtonCheckStep() {
        replayStep = new JButton("Hе існує");
        replayStep.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(!((rowsChengeGameField == -1) && (columChengeGameField == -1) && (stepChar == '\u0000'))){
                    unblockTextField();
                    textField[rowsChengeGameField][columChengeGameField].setText("");
                }

                stepChar = '\u0000';
                rowsChengeGameField=-1;
                columChengeGameField=-1;
                wordStep.clear();

                return;
            }
        });
        this.add(replayStep);
        addJLabel(ConstSIZE.SIZE_FIELD-2);
        nextStep = new JButton("Bірнo");
        nextStep.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(!((rowsChengeGameField == -1) && (columChengeGameField == -1) && (stepChar == '\u0000')))
                    gameField.setSection(rowsChengeGameField,columChengeGameField,stepChar);

                stepChar = '\u0000';
                rowsChengeGameField=-1;
                columChengeGameField=-1;
                unblockTextField();
            }
        });
        this.add(nextStep);
    }

    private void addTile(){
        //textField = new TextField[Const_value.SIZE][Const_value.SIZE];
        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++) {
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++) {
                textField[i][j] = new TextField(i,j);
                this.add(textField[i][j]);
            }
        }
    }

    private void addScore(){
        this.add(new JLabel(player_1.getName()));
        this.add(new JLabel(Integer.toString(player_1.getScore())));
        this.add(new JLabel(":"));
        this.add(new JLabel(Integer.toString(player_2.getScore())));
        this.add(new JLabel(player_2.getName()));
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

    private void blockTextField(){
        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++)
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++)
                this.textField[i][j].setEnabled(false);
    }

    private void unblockTextField(){
        for (int i = 0; i < ConstSIZE.SIZE_FIELD; i++)
            for (int j = 0; j < ConstSIZE.SIZE_FIELD; j++){
                if(gameField.checkSection(i,j))
                    this.textField[i][j].setEnabled(true);

            }
    }

    private void controllGame(){

    }

    class TextField extends JTextField{
        private int i;
        private int j;

        public TextField(int i, int j){
            super();
            this.i = i;
            this.j = j;
            //this.setDocument(new LengthRestrictedDocument(1));
            this.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    //GameFieldFrame.this.gameField.getField()[i][j]
                    GameFieldFrame.this.blockTextField();
                    GameFieldFrame.this.stepChar = TextField.this.getText().charAt(0);
                    rowsChengeGameField = i;
                    columChengeGameField = j;
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
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    wordStep.add(new Pair<>(i,j));

                }
            });
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public class Pair<L,R> {

        private final L left;
        private final R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() { return left; }
        public R getRight() { return right; }

        @Override
        public int hashCode() { return left.hashCode() ^ right.hashCode(); }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair pairo = (Pair) o;
            return this.left.equals(pairo.getLeft()) &&
                    this.right.equals(pairo.getRight());
        }

    }
}

