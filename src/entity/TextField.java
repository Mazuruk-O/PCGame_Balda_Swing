package entity;

import javax.swing.*;

public class TextField extends JTextField {
    private int i;
    private int j;

    public TextField(int i, int j){
        super();
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}