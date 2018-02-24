package com.example.android.countcounter;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
/////////////////////////////////////////////////////////Artificial Intelligence
import static com.example.android.countcounter.Board.*;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
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
    // containing the buttons
    Button[][] btn;
    // number of winning times for both x and o
    public static int xCount, oCount;
    ///// these variables are related to moving and draggable layout function
    float dX;
    float dY;
    int lastAction;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        // ...but notify us that it happened.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        ///initializing buttons array
        btn = new Button[][]{
                {findViewById(R.id.b1), findViewById(R.id.b2), findViewById(R.id.b3)},
                {findViewById(R.id.b4), findViewById(R.id.b5), findViewById(R.id.b6)},
                {findViewById(R.id.b7), findViewById(R.id.b8), findViewById(R.id.b9)}};
        //////restoring saved state
        if (savedInstanceState != null) {
            for (int i = 0; i < 3; i++) {
                mBoard[i] = savedInstanceState.getStringArray("textsArray" + i);
            }
            xCount = savedInstanceState.getInt("xCount");
            oCount = savedInstanceState.getInt("oCount");
            playerID = savedInstanceState.getInt("playerID");
            gameOver = savedInstanceState.getBoolean("gameOver");
            toastMessage = savedInstanceState.getString("toastMessage");
            toastDrawable = savedInstanceState.getInt("toastDrawable");
        }
        /////////////////////////////////////////////////////a new whole section for artificial Intelligence
        if (singlePlayer) {
            createBoard();
            //initialize a new AI objects
        } else if (multiPlayer) {
            createBoard();
        }
    }

    ////////////////////////////////////////related to AI expressing the player's movement

    @Override
    protected void onResume() {
        /////updating buttons and texts with the final result
        TextView textX = findViewById(R.id.playerXcount);
        TextView textO = findViewById(R.id.playerOcount);
        textX.setText("" + xCount);
        textO.setText("" + oCount);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mBoard[i][j] != null) {
                    btn[i][j].setText(mBoard[i][j]);
                    if (!(btn[i][j].getText().toString().equals(DOT_EMPTY))) {
                        btn[i][j].setEnabled(false);
                        if (btn[i][j].getText().toString().equals(DOT_X)) {
                            btn[i][j].setBackgroundResource(R.drawable.xbuttondrawable);
                        } else if (btn[i][j].getText().toString().equals(DOT_O)) {
                            btn[i][j].setBackgroundResource(R.drawable.obuttondrawable);
                        }
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
                btn.setText(DOT_X);
                playerID = 1;
                btn.setEnabled(false);
                btn.setBackgroundResource(R.drawable.xbuttondrawable);
                check();
            } else if (playerID == 1 && btn.isEnabled()) {
                playerID = 0;
                btn.setText(DOT_O);
                btn.setEnabled(false);
                btn.setBackgroundResource(R.drawable.obuttondrawable);
                check();
            }
        } else if (singlePlayer) {/////////////////////////////////////////////////////////////// Artificial Intelligence
            if (btn.isEnabled()) {
                ///
            }
        }
    }

    private void check() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mBoard[i][j] = btn[i][j].getText().toString();
            }
        }

        if (mBoard[0][0].equals(mBoard[0][1]) && mBoard[0][1].equals(mBoard[0][2]) && !mBoard[0][2].equals("")) {
            if (mBoard[0][0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (mBoard[0][0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j].setEnabled(false);
                }
            }
        } else if (mBoard[1][0].equals(mBoard[1][1]) && mBoard[1][1].equals(mBoard[1][2]) && !mBoard[1][2].equals("")) {
            if (mBoard[1][0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (mBoard[1][0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j].setEnabled(false);
                }
            }
        } else if (mBoard[2][0].equals(mBoard[2][1]) && mBoard[2][1].equals(mBoard[2][2]) && !mBoard[2][2].equals("")) {
            if (mBoard[2][0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (mBoard[2][0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j].setEnabled(false);
                }
            }
        } else if (mBoard[0][0].equals(mBoard[1][0]) && mBoard[1][0].equals(mBoard[2][0]) && !mBoard[2][0].equals("")) {
            if (mBoard[0][0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (mBoard[0][0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j].setEnabled(false);
                }
            }
        } else if (mBoard[0][1].equals(mBoard[1][1]) && mBoard[1][1].equals(mBoard[2][1]) && !mBoard[2][1].equals("")) {
            if (mBoard[0][1].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (mBoard[0][1].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j].setEnabled(false);
                }
            }
        } else if (mBoard[0][2].equals(mBoard[1][2]) && mBoard[1][2].equals(mBoard[2][2]) && !mBoard[2][2].equals("")) {
            if (mBoard[0][2].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (mBoard[0][2].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j].setEnabled(false);
                }
            }
        } else if (mBoard[0][0].equals(mBoard[1][1]) && mBoard[1][1].equals(mBoard[2][2]) && !mBoard[2][2].equals("")) {
            if (mBoard[0][0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (mBoard[0][0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j].setEnabled(false);
                }
            }
        } else if (mBoard[0][2].equals(mBoard[1][1]) && mBoard[1][1].equals(mBoard[2][0]) && !mBoard[2][0].equals("")) {
            if (mBoard[0][2].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (mBoard[0][2].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j].setEnabled(false);
                }
            }
        } else if (!mBoard[0][0].equals("") && !mBoard[0][1].equals("") && !mBoard[0][2].equals("") && !mBoard[1][0].equals("") && !mBoard[1][1].equals("") && !mBoard[1][2].equals("") && !mBoard[2][0].equals("") && !mBoard[2][1].equals("") && !mBoard[2][2].equals("")) {
            showToast("it's a tie !", R.drawable.stalemate);
        }
    }

    public void mainMenu(View view){
        Intent intent=new Intent(this,MainMenu.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < 3; i++) {
            outState.putStringArray("textsArray" + i, mBoard[i]);
        }
        outState.putInt("oCount", oCount);
        outState.putInt("xCount", xCount);
        outState.putInt("playerID", playerID);
        outState.putBoolean("gameOver", gameOver);
        outState.putString("toastMessage", toastMessage);
        outState.putInt("toastDrawable", toastDrawable);
        super.onSaveInstanceState(outState);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void showToast(String winner, int background) {
      /* CardView cvlayout = findViewById(R.id.menu);
       FrameLayout flayout = findViewById(R.id.winningbackground);
       TextView text = findViewById(R.id.Winnerplayer);
       cvlayout.setOnTouchListener(this);
       cvlayout.setVisibility(View.VISIBLE);
       flayout.setBackgroundResource(background);
       cvlayout.setBackgroundResource(background); */
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE|Window.FEATURE_ACTIVITY_TRANSITIONS|Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(10f);
        }
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        // this to set the dialog background color
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView textView = dialog.findViewById(R.id.winnerPlayer);
        CardView cvLayout = dialog.findViewById(R.id.menu);
        FrameLayout frameLayout = dialog.findViewById(R.id.winningbackground);
        Button btnRematch = dialog.findViewById(R.id.rematch);
        Button btnMainMenu = dialog.findViewById(R.id.main_menu);
        btnRematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainMenu.class);
                startActivity(intent);
            }
        });
        frameLayout.setBackgroundResource(background);
        cvLayout.setBackgroundResource(background);
        textView.setText(winner);
        gameOver = true;
        toastMessage = winner;
        toastDrawable = background;
    }

    @Override
    public void onBackPressed() {
        //don't go back
    }

    /*@Override
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
    }*/

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            return true;
        }
        return super.onTouchEvent(event);
    }*/

    /* public int minimax(int depth, int turn) {
        if (hasXWon()) return +1;
        if (hasOWon()) return -1;

        List<Point> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);
            if (turn == 1) {
                placeAMove(point, 1);
                int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);

                if (depth == 0)
                    System.out.println("Score for position " + (i + 1) + " = " + currentScore);
                if (currentScore >= 0) {
                    if (depth == 0) computersMove = point;
                }
                if (currentScore == 1) {
                    board[point.x][point.y] = 0;
                    break;
                }
                if (i == pointsAvailable.size() - 1 && max < 0) {
                    if (depth == 0) computersMove = point;
                }
            } else if (turn == 0) {
                placeAMove(point, 2);
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min);
                if (min == -1) {
                    board[point.x][point.y] = 0;
                    break;
                }
            }

            board[point.x][point.y] = 0; //Reset this point
        }
        return turn == 1 ? max : min;
    }*/
}