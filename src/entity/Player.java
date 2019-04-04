package entity;

import entity.interf.PlayerIntf;

public class Player implements PlayerIntf {
    private int score;
    private String name;

    public Player(){
        score = 0;
        name = "Player";
        return;
    }

    public Player(String name){
        score = 0;
        this.name = (name == null) ? "Player" : name;
        return;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
        return;
    }

    @Override
    public void addToScore(int num) {
        score = Math.addExact(score,num);
        return;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = (name == null) ? "Player" : name;
        return;
    }
}
