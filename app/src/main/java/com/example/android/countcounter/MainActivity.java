package com.example.android.countcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import static com.example.android.countcounter.Board.*;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // switcher to change the player identity x,o
    private int playerID = 0;
    //checks whether the game is over and the winning layout is displayed
    boolean gameOver = false;
    //passes the toast's message
    String toastMessage;
    //passes the toast's drawable background
    int toastDrawable;
    //checking the game play mode
    public static boolean singlePlayer, multiPlayer;
    // containing the text in each button
    char[][] chars;
    // containing the buttons
    Button[][] btn;
    // number of winning times for both x and o
    public static int xCount, oCount;
    ///// these variables are related to moving and draggable layout function
    float dX;
    float dY;
    int lastAction;
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArtificialIntelligence ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///initializing buttons array
        btn = new Button[][]{
                {findViewById(R.id.b1), findViewById(R.id.b2), findViewById(R.id.b3)},
                {findViewById(R.id.b4), findViewById(R.id.b5), findViewById(R.id.b6)},
                {findViewById(R.id.b7), findViewById(R.id.b8), findViewById(R.id.b9)}};
        chars = new char[3][3];
        //////restoring saved state
        if (savedInstanceState != null) {
            for (int i = 0; i < 3; i++) {
                chars[i] = savedInstanceState.getCharArray("textsArray" + i);
                mBoard[i] = savedInstanceState.getCharArray("textsArray" + i);
            }
            xCount = savedInstanceState.getInt("xCount");
            oCount = savedInstanceState.getInt("oCount");
            playerID = savedInstanceState.getInt("playerID");
            gameOver = savedInstanceState.getBoolean("gameOver");
            toastMessage = savedInstanceState.getString("toastMessage");
            toastDrawable = savedInstanceState.getInt("toastDrawable");
        }
        /////updating chars with the final result
        TextView textX = findViewById(R.id.playerXcount);
        TextView textO = findViewById(R.id.playerOcount);
        textX.setText("" + xCount);
        textO.setText("" + oCount);
        /////////////////////////////////////////////////////a new whole section for artificial Intelligence
        if(singlePlayer) {
            createBoard();
            ss=new ArtificialIntelligence("pc",DOT_O,5);
        }

        for(int i=0;i<3;i++){
            for(int j=0;j<3;i++){
                chars[i][j]=DOT_EMPTY;
                mBoard[i][j]=DOT_EMPTY;
                btn[i][j].setText(String.valueOf(DOT_EMPTY));
            }
        }
    }

    ////////////////////////////////////////related to AI expressing the player's movement
    public int Hmove(char[][] board, int index) {
        if (index < 3 && board[0][index] == DOT_EMPTY) {
            return index;
        } else if (index > 2 && index < 6 && board[1][index - 3] == DOT_EMPTY) {
            return index;
        } else if (index > 5 && index < 9 && board[2][index - 6] == DOT_EMPTY) {
            return index;
        }
        return -1;
    }

    @Override
    protected void onResume() {
        /////updating buttons and texts with the final result
        TextView textX = findViewById(R.id.playerXcount);
        TextView textO = findViewById(R.id.playerOcount);
        textX.setText("" + xCount);
        textO.setText("" + oCount);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btn[i][j].setText(String.valueOf(chars[i][j]));
                if (!btn[i][j].getText().toString().equals(DOT_EMPTY)) {
                    btn[i][j].setEnabled(false);
                    if (btn[i][j].getText().toString().equals(DOT_X)) {
                        btn[i][j].setBackgroundResource(R.drawable.xbuttondrawable);
                    } else if(btn[i][j].getText().toString().equals(DOT_O)) {
                        btn[i][j].setBackgroundResource(R.drawable.obuttondrawable);
                    }
                }
            }
        }
        if (gameOver) {
            showToast(toastMessage, toastDrawable);
        }
        super.onResume();
    }

    public void Add(View view) {
        Button btn = (Button) view;
        btn.setTextSize(24f);
        if (multiPlayer) {
            if (playerID == 0 && btn.isEnabled()) {
                btn.setText("X");
                playerID = 1;
                btn.setEnabled(false);
                btn.setBackgroundResource(R.drawable.xbuttondrawable);
                check();
            } else if (playerID == 1 && btn.isEnabled()) {
                playerID = 0;
                btn.setText("O");
                btn.setEnabled(false);
                btn.setBackgroundResource(R.drawable.obuttondrawable);
                check();
            }
        } else if (singlePlayer) {
            if (btn.isEnabled()) {
                btn.setText(DOT_X);
                btn.setEnabled(false);
                btn.setBackgroundResource(R.drawable.xbuttondrawable);
                int index = Hmove(mBoard, Integer.parseInt(btn.getContentDescription().toString()));
                turnPlayer(DOT_X, index);
                int index2=ss.move();
                turnPlayer(DOT_O,index2);
                check();

            }
        }
    }

    private void check() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                chars[i][j] = btn[i][j].getText().toString().charAt(0);
                mBoard[i][j] = chars[i][j];
            }
        }
        int diagX1 = 0;
        int diagX2 = 0;
        int diagO1 = 0;
        int diagO2 = 0;
        int rowX = 0;
        int columnX = 0;
        int rowO = 0;
        int columnO = 0;
        int places = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mBoard[i][j] == DOT_X) rowX++;
                if (mBoard[i][j] == DOT_O) rowO++;
                if (mBoard[j][i] == DOT_X) columnX++;
                if (mBoard[j][i] == DOT_O) columnO++;
                if (i == j && mBoard[i][j] == DOT_X) diagX1++;
                if (i == j && mBoard[i][j] == DOT_O) diagO1++;
                if (j == SIZE - 1 - i && mBoard[i][j] == DOT_X) diagX2++;
                if (j == SIZE - 1 - i && mBoard[i][j] == DOT_X) diagO2++;
                if (mBoard[i][j] == DOT_EMPTY) places++;
            }
        }

        if (rowX == DOT_TO_WIN || columnX == DOT_TO_WIN || diagX1 == DOT_TO_WIN || diagX2 == DOT_TO_WIN) {
            showToast("Player X is the winner", R.drawable.winning);
            xCount++;
        } else if (rowO == DOT_TO_WIN || columnO == DOT_TO_WIN || diagO1 == DOT_TO_WIN || diagO2 == DOT_TO_WIN) {
            showToast("Player O is the winner", R.drawable.winning);
            oCount++;
        } else if (places == 9) {
            showToast("Stalemate !", R.drawable.stalemate);
        }
        /*
        if (chars[0].equals(chars[1]) && chars[1].equals(chars[2]) && !chars[0].equals("")) {
            if (chars[0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (chars[0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (chars[0].equals(chars[3]) && chars[3].equals(chars[6]) && !chars[0].equals("")) {
            if (chars[0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (chars[0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (chars[0].equals(chars[4]) && chars[4].equals(chars[8]) && !chars[0].equals("")) {
            if (chars[0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (chars[0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (chars[2].equals(chars[4]) && chars[4].equals(chars[6]) && !chars[2].equals("")) {
            if (chars[2].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (chars[2].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (chars[1].equals(chars[4]) && chars[4].equals(chars[7]) && !chars[1].equals("")) {
            if (chars[1].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (chars[1].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (chars[3].equals(chars[4]) && chars[4].equals(chars[5]) && !chars[3].equals("")) {
            if (chars[3].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (chars[3].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (chars[6].equals(chars[7]) && chars[7].equals(chars[8]) && !chars[6].equals("")) {
            if (chars[6].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (chars[6].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (chars[2].equals(chars[5]) && chars[5].equals(chars[8]) && !chars[2].equals("")) {
            if (chars[6].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (chars[6].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (!chars[0].equals("") && !chars[1].equals("") && !chars[2].equals("") && !chars[3].equals("") && !chars[4].equals("") && !chars[5].equals("") && !chars[6].equals("") && !chars[7].equals("") && !chars[8].equals("")) {
            showToast("Stalemate !", R.drawable.stalemate);
        }*/
    }

    private void showToast(String winner, int background) {
        CardView cvlayout = findViewById(R.id.menu);
        FrameLayout flayout = findViewById(R.id.winningbackground);
        TextView text = findViewById(R.id.Winnerplayer);
        cvlayout.setOnTouchListener(this);
        cvlayout.setVisibility(View.VISIBLE);
        flayout.setBackgroundResource(background);
        cvlayout.setBackgroundResource(background);
        gameOver = true;
        toastMessage = winner;
        toastDrawable = background;
        text.setText(winner);
    }

    public void rematch(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < 3; i++) {
            outState.putCharArray("textsArray" + i, chars[i]);
        }
        outState.putInt("oCount", oCount);
        outState.putInt("xCount", xCount);
        outState.putInt("playerID", playerID);
        outState.putBoolean("gameOver", gameOver);
        outState.putString("toastMessage", toastMessage);
        outState.putInt("toastDrawable", toastDrawable);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //don't go back
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                view.setY(event.getRawY() + dY);
                view.setX(event.getRawX() + dX);
                lastAction = MotionEvent.ACTION_MOVE;
                break;

            default:
                return false;
        }
        return true;
    }

}