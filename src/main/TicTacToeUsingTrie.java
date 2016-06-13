package main;

import data.TicTacToeObj;
import data.trie.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by akshaymanathkar on 13/06/16.
 */
public class TicTacToeUsingTrie {
    public static final char EMPTY = ' ';
    public static final char X = 'X';
    public static final char O = 'O';
    public static final int CLMS = 3;
    public static final int ROWS = 3;
    public static boolean X_WON = false;
    public static boolean O_WON = false;
    private Trie xInputs = new Trie(new String[0]);
    private Trie oInputs = new Trie(new String[0]);
    private TicTacToeObj[][] board = new TicTacToeObj[ROWS][CLMS];
    private List<String> winingPatterns = new ArrayList<>();

    public TicTacToeUsingTrie(){
        initializeTicTacToeBoard();
        initializeWiningPatterns();
    }

    private void initializeTicTacToeBoard(){
        for(int i=0; i<ROWS; i++){
            for(int j = 0; j< CLMS; j++){
                board[i][j] = new TicTacToeObj();
            }
        }
    }

    private void initializeWiningPatterns(){
        winingPatterns.add("001122");
        winingPatterns.add("002211");
        winingPatterns.add("110022");
        winingPatterns.add("112200");
        winingPatterns.add("220011");
        winingPatterns.add("221100");

        winingPatterns.add("201102");
        winingPatterns.add("200211");
        winingPatterns.add("110220");
        winingPatterns.add("112002");
        winingPatterns.add("022011");
        winingPatterns.add("021120");

        winingPatterns.add("001020");
        winingPatterns.add("002010");
        winingPatterns.add("100020");
        winingPatterns.add("102000");
        winingPatterns.add("200010");
        winingPatterns.add("201000");

        winingPatterns.add("000102");
        winingPatterns.add("000201");
        winingPatterns.add("010002");
        winingPatterns.add("010200");
        winingPatterns.add("020001");
        winingPatterns.add("020100");

        winingPatterns.add("011121");
        winingPatterns.add("012111");
        winingPatterns.add("112101");
        winingPatterns.add("110121");
        winingPatterns.add("210111");
        winingPatterns.add("211101");

        winingPatterns.add("101112");
        winingPatterns.add("101211");
        winingPatterns.add("111210");
        winingPatterns.add("111012");
        winingPatterns.add("121011");
        winingPatterns.add("121110");

        winingPatterns.add("021222");
        winingPatterns.add("022212");
        winingPatterns.add("120222");
        winingPatterns.add("122202");
        winingPatterns.add("220212");
        winingPatterns.add("221202");

        winingPatterns.add("202122");
        winingPatterns.add("202221");
        winingPatterns.add("212220");
        winingPatterns.add("212022");
        winingPatterns.add("222021");
        winingPatterns.add("222120");
    }

    public Trie getxInputs() {
        return xInputs;
    }

    public Trie getoInputs() {
        return oInputs;
    }

    public TicTacToeObj[][] getBoard() {
        return board;
    }

    public List<String> getWiningPatterns() {
        return winingPatterns;
    }

    public void printBoard(){
        for(int i=0; i<ROWS; i++) {
            for (int j = 0; j < CLMS; j++) {
                System.out.print(" " + board[i][j].getaChar() + " ");
                if (j < CLMS -1) System.out.print("|");
            }
            System.out.println();
            if (i < ROWS-1) {
                System.out.println("-----------");
            }
        }
    }

    public void setInput(Scanner scr, StringBuilder stringBuilder, TicTacToeObj[][] board, Trie inputs, char player){
        System.out.print("\nPlayer " + player + ", enter your move (row and column from 1 to 3): ");
        int num1 = scr.nextInt()-1;
        int num2 = scr.nextInt()-1;

        if(num1 < 0 || num2 < 0 || num1 > 3 || num2 > 3){
            System.out.println("Invalid input. Try again...");
            return;
        }

        if(board[num1][num2].getaChar() == EMPTY){
            board[num1][num2].setaChar(player);
            stringBuilder.append(num1);
            stringBuilder.append(num2);
            inputs.getRoot().addWord(stringBuilder.toString());
        } else{
            System.out.println("This move is not valid. Try again...");
        }
    }

    public boolean hasWon(List<String> winingPatterns, Trie inputs){
        boolean isWon = false;
        for(String str : winingPatterns){
            if(inputs.contains(str)){
                isWon = true;
            }
        }
        return isWon;
    }

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        TicTacToeUsingTrie ticTacToe = new TicTacToeUsingTrie();
        int numOfInputs = 9;
        boolean isPlayerXTurn = true;
        StringBuilder xStringBuilder = new StringBuilder();
        StringBuilder oStringBuilder = new StringBuilder();

        ticTacToe.printBoard();

        while(numOfInputs > 0){
            if (isPlayerXTurn) {
                ticTacToe.setInput(scr, xStringBuilder, ticTacToe.getBoard(), ticTacToe.getxInputs(), X);
                X_WON = ticTacToe.hasWon(ticTacToe.getWiningPatterns(), ticTacToe.getxInputs());
                isPlayerXTurn = false;
            } else {
                ticTacToe.setInput(scr, oStringBuilder, ticTacToe.getBoard(), ticTacToe.getoInputs(), O);
                O_WON = ticTacToe.hasWon(ticTacToe.getWiningPatterns(), ticTacToe.getoInputs());
                isPlayerXTurn = true;
            }
            ticTacToe.printBoard();
            numOfInputs--;
            if(X_WON){
                System.out.println("Player " + X + " has won");
                return;
            } else if(O_WON){
                System.out.println("Player " + O + " has won");
                return;
            }
            if(numOfInputs == 0){
                System.out.println("Draw nobody won");
            }
        }
    }
}

