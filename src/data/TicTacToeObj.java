package data;

import main.TicTacToeUsingTrie;

/**
 * Created by akshaymanathkar on 13/06/16.
 */
public class TicTacToeObj {
    private char aChar;

    public TicTacToeObj(){
        aChar = TicTacToeUsingTrie.EMPTY;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }
}
