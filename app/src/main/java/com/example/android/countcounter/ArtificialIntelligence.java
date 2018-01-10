package com.example.android.countcounter;

import java.util.ArrayList;
import static com.example.android.countcounter.Board.*;
/**
 * Created by ALQasem on 10/01/2018.
 */

public class ArtificialIntelligence {
    private String mName;
    private int mDifficult;
    private char mAiSymbol;
    private char mHumanSymbol;
    private ArrayList<Move> mMoves = new ArrayList<>();

    public ArtificialIntelligence (String name, char aiPlayerSymbol, int difficult) {
        mName = name;
        mAiSymbol = aiPlayerSymbol;
        mDifficult = difficult;
        if (mAiSymbol == DOT_X)
            mHumanSymbol = DOT_O;
        else
            mHumanSymbol = DOT_X;
    }

    public void setLevel(int level){
        mDifficult = level;
    }

    /**
     * Minimax (sometimes MinMax or MM[1]) is a decision rule used in decision theory, game theory,
     * statistics and philosophy for minimizing the possible loss for a worst case (maximum loss) scenario
     * @param depth is the number how long Minimax sees game's steps. We change depth if we want to change
     *              difficult intelligence's aiplayer.
     * @param player is symbol of player, the first minimax steps insted a human, and then steps itself. If it ends
     *               (win, draw or lose) we return a score of step. The score writes into ArrayList Moves
     * @param newboard is a board. When depth is increase we fill board.
     */

    public int minimax(char [][] newboard, char player, int depth) {
        if (depth == mDifficult)
            return 0;
        if (checkWin(mHumanSymbol))
            return -10;
        if (checkWin(mAiSymbol))
            return 10;
        if (boardIsFull())
            return 5;
        ArrayList <Integer> mAvailSpots = availableCell(newboard);
        for (int i = 0; i < mAvailSpots.size(); i++) {
            Move move = new Move();
            move.setIndex(mAvailSpots.get(i));
            // Set our index of board into class Move and then turn
            turnPlayer(player, mAvailSpots.get(i));

            if (player == mAiSymbol) {
                int result = minimax(newboard, mHumanSymbol,depth+1);
                move.setScore(result);
                // Turn each players and set score
            } else {
                int result = minimax(newboard, mAiSymbol, depth+1);
                move.setScore(result);
            }
            turnPlayer(DOT_EMPTY, mAvailSpots.get(i));
            mMoves.add(move);
            // Add our move to list
        }
        int bestMove = 0;
        if (player == mAiSymbol) {
            int bestScore = -10000;
            for (Move move: mMoves) {
                if (move.getScore() > bestScore) {
                    bestScore = move.getScore();
                    if (bestScore >= 10) {
                        bestMove = move.getIndex();
                        return bestMove;
                        // Now we look for the bestScore of move's list
                    }
                    bestMove = move.getIndex();
                }
            }
        } else {
            int bestScore = 10000;
            for (Move move: mMoves) {
                if (move.getScore() < bestScore) {
                    bestScore = move.getScore();
                    if (bestScore <= -10) {
                        bestMove = move.getIndex();
                        return bestMove;
                    }
                    bestMove = move.getIndex();
                    // Now we look for the bestScore of move's list
                }
            }
        }
        return bestMove;
    }

    public int move (){
        mMoves.clear();
        int index = minimax(mBoard, mAiSymbol, 0);
        System.out.println(index);
        return index;
        // Clear our move's list and execute minimax function
    }

    public String getName (){
        return mName;
    }

    public char getAiSymbol(){
        return mAiSymbol;
    }
}

/**
 * Move is class that saved our steps and scores.
 */

class Move {
    private int index;
    private int score;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public int getScore() {
        return score;
    }
}
