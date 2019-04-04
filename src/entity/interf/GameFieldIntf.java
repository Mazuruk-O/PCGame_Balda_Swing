package entity.interf;

public interface GameFieldIntf {
    char[][] getField();
    boolean checkSection(int i, int j);
    void setSection(int i, int j, char c);
    boolean isFullField();
}
