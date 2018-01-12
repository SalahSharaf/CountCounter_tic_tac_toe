package com.example.android.countcounter;

import java.util.ArrayList;

/**
 * Board is the TicTacToe's board. Is it static class
 */

public final class Board {

    public static final String DOT_X = "X";
    public static final String DOT_O = "O";
    public static final String DOT_EMPTY = "";
    public static final int DOT_TO_WIN = 3;
    public static final int SIZE = 3;
    public static String[][] mBoard;

    // We don't need to create Board class.
    private Board(){}

    // Create board and fill it
    public static void createBoard(){
        mBoard = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                mBoard[i][j] = DOT_EMPTY;
            }
        }
    }

    // Check board after player's step
    ///////////////////////////////////////////////////////////
    public static boolean checkWin(String playerSymbol) {
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < SIZE; i++) {
            int row = 0;
            int column = 0;
            for (int j = 0; j < SIZE; j++) {
                if (mBoard[i][j].equals( playerSymbol)) row++;
                if (mBoard[j][i].equals(playerSymbol)) column++;
                if (i == j && mBoard[i][j].equals(playerSymbol)) diag1++;
                if (j == SIZE-1-i && mBoard[i][j].equals(playerSymbol)) diag2++;
                if (row == DOT_TO_WIN || column == DOT_TO_WIN || diag1 == DOT_TO_WIN || diag2 == DOT_TO_WIN) return true;
            }
        }
        return false;
    }

    // If board full we get draw
    /////////////////////////////////////////////////////////
    public static boolean boardIsFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mBoard[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    // Return available steps for AI player
    ////////////////////////////////////////////////////////////////////////
    public static ArrayList<Integer> availableCell(String[][] board) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.clear();
        int count = 0;
        for (String[] x: board) {
            for (String y: x){
                if (y.equals(DOT_EMPTY)) {
                    arrayList.add(count);
                }
                count++;
            }
        }
        return arrayList;
    }

    // Turn of player
    public static void turnPlayer(String playerSymbol, int index) {
        if (index < 3) {
            mBoard[0][index] = playerSymbol;
        }
        else if (index > 2 && index < 6) {
            mBoard[1][index-3] = playerSymbol;
        }
        else if (index > 5 && index < 9) {
            mBoard[2][index-6] = playerSymbol;
        }
    }

    // We check our board. The index should be empty
    public static boolean isEmpty(int index) {
        if (index < 3 && mBoard[0][index].equals(DOT_EMPTY)) {
            return true;
        }
        else if (index > 2 && index < 6 && mBoard[1][index-3].equals(DOT_EMPTY)) {
            return true;
        }
        else if (index > 5 && index < 9 && mBoard[2][index-6].equals(DOT_EMPTY)) {
            return true;
        }
        return false;
    }

}
