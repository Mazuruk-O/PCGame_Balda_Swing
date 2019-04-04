package entity;

import entity.interf.GameFieldIntf;

public class GameField implements GameFieldIntf {
    private char[][] gameField;
    private int fullness;

    public GameField(){
        gameField = new char[ConstSIZE.SIZE_FIELD][ConstSIZE.SIZE_FIELD];
        fullness = 0;
        return;
    }

    public GameField(String startWord){
        gameField = new char[ConstSIZE.SIZE_FIELD][ConstSIZE.SIZE_FIELD];

        if(startWord.length() == 5)
        {
            for (int i = 0; i < startWord.length(); i++)
                gameField[ConstSIZE.SIZE_FIELD/2][i] = startWord.charAt(i);
            fullness = startWord.length();
        }
        else
            gameField = new char[ConstSIZE.SIZE_FIELD][ConstSIZE.SIZE_FIELD];

        return;
    }

    @Override
    public char[][] getField() { return gameField; }

    @Override
    public boolean checkSection(int i, int j) {
        if(i <0 || j < 0 || i > ConstSIZE.SIZE_FIELD || j > ConstSIZE.SIZE_FIELD)
            return false;

        return (gameField[i][j] == '\u0000') ? true : false;
    }

    @Override
    public void setSection(int i, int j, char c) {
        if(i <0 || j < 0 || i > ConstSIZE.SIZE_FIELD || j > ConstSIZE.SIZE_FIELD)
            return;

        gameField[i][j] = c;
        return;
    }

    @Override
    public boolean isFullField() { return (fullness == ConstSIZE.SIZE_FIELD*ConstSIZE.SIZE_FIELD) ? true : false; }


    /// '\u0000'
}
