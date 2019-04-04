package entity.interf;

import java.io.File;
import java.net.URL;

/**
 * ToDo this
 */

public interface AI extends PlayerIntf{
    boolean createVokabulary(File file);
    boolean createVokabulary(URL url);
    void addWord(String word);
    boolean isExistWord(String serchWord);
    boolean isExistVokabulary();
}
