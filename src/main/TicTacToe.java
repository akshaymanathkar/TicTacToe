package main;

import data.TicTacToeObj;

import java.util.Scanner;

/**
 * Created by akshaymanathkar on 13/06/16.
 */
public class TicTacToe {
    public static final char EMPTY = ' ';
    public static final char X = 'X';
    public static final char O = 'O';
    public static final int CLMS = 3;
    public static final int ROWS = 3;
    public static boolean X_WON = false;
    public static boolean O_WON = false;
    private TicTacToeObj[][] board = new TicTacToeObj[ROWS][CLMS];

    public TicTacToe(){
        for(int i=0; i<ROWS; i++){
            for(int j = 0; j< CLMS; j++){
                board[i][j] = new TicTacToeObj();
            }
        }
    }

    public TicTacToeObj[][] getBoard() {
        return board;
    }

    public  boolean hasWon(char player, int currentRow, int currentCol) {
        return (board[currentRow][0].getaChar() == player         // 3-in-the-row
                && board[currentRow][1].getaChar() == player
                && board[currentRow][2].getaChar() == player
                || board[0][currentCol].getaChar() == player      // 3-in-the-column
                && board[1][currentCol].getaChar() == player
                && board[2][currentCol].getaChar() == player
                || currentRow == currentCol            // 3-in-the-diagonal
                && board[0][0].getaChar() == player
                && board[1][1].getaChar() == player
                && board[2][2].getaChar() == player
                || currentRow + currentCol == 2  // 3-in-the-opposite-diagonal
                && board[0][2].getaChar() == player
                && board[1][1].getaChar() == player
                && board[2][0].getaChar() == player);
    }

    public void printBoard(){
        System.out.println();
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

    public void setInput(Scanner scr, TicTacToe ticTacToe, char player){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nPlayer " + player + ", enter your move (row and column from 1 to 3): ");
        System.out.print(stringBuilder.toString());
        int num1 = scr.nextInt()-1;
        int num2 = scr.nextInt()-1;

        if(num1 < 0 || num2 < 0 || num1 > 3 || num2 > 3){
            System.out.println("Invalid input. Try again...");
            return;
        }

        if(ticTacToe.getBoard()[num1][num2].getaChar() == EMPTY){
            ticTacToe.getBoard()[num1][num2].setaChar(player);
            if(ticTacToe.hasWon(player, num1, num2)){
                if(player == X){
                    X_WON = true;
                } else {
                    O_WON = true;
                }
            }

        } else{
            System.out.println("This move is not valid. Try again...");
        }

    }

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe();
        int numOfInputs = 9;
        boolean isPlayerXTurn = true;

        ticTacToe.printBoard();

        while(numOfInputs > 0){
            if (isPlayerXTurn) {
                ticTacToe.setInput(scr, ticTacToe, X);
                isPlayerXTurn = false;
            } else {
                ticTacToe.setInput(scr, ticTacToe, O);
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
