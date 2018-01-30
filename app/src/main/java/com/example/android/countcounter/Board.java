package com.example.android.countcounter;

import java.util.ArrayList;

/**
 * Board is the TicTacToe's board. Is it static class
 */

public final class Board {

    public static final String DOT_X = "X";
    public static final String DOT_O = "O";
    public static final String DOT_EMPTY = "";
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

    // Return available steps for AI player
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

}
