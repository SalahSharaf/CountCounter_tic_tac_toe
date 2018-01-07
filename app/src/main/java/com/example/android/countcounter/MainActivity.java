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

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private int playerID = 0;
    boolean gameOver = false;
    String toastMessage;
    int toastDrawable;
    String[] texts;
    Button[] btn;
    public static int xCount, oCount;
    float dX;
    float dY;
    int lastAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///initializing buttons array
        btn = new Button[]{findViewById(R.id.b1), findViewById(R.id.b2), findViewById(R.id.b3), findViewById(R.id.b4), findViewById(R.id.b5), findViewById(R.id.b6)
                , findViewById(R.id.b7), findViewById(R.id.b8), findViewById(R.id.b9)};
        texts = new String[btn.length];
        //////restoring saved state
        if (savedInstanceState != null) {
            texts = savedInstanceState.getStringArray("textsArray");
            xCount = savedInstanceState.getInt("xCount");
            oCount = savedInstanceState.getInt("oCount");
            playerID = savedInstanceState.getInt("playerID");
            gameOver=savedInstanceState.getBoolean("gameOver");
            toastMessage=savedInstanceState.getString("toastMessage");
            toastDrawable=savedInstanceState.getInt("toastDrawable");
        }
        /////updating texts with the final result
        TextView textX = findViewById(R.id.playerXcount);
        TextView textO = findViewById(R.id.playerOcount);
        textX.setText("" + xCount);
        textO.setText("" + oCount);
    }

    @Override
    protected void onResume() {
        /////updating texts with the final result
        TextView textX = findViewById(R.id.playerXcount);
        TextView textO = findViewById(R.id.playerOcount);
        textX.setText("" + xCount);
        textO.setText("" + oCount);
        for (int i = 0; i < texts.length; i++) {
            btn[i].setText(texts[i]);
            if (!btn[i].getText().toString().equals("")) {
                btn[i].setEnabled(false);
                if (btn[i].getText().toString().equals("X")) {
                    btn[i].setBackgroundResource(R.drawable.xbuttondrawable);
                } else {
                    btn[i].setBackgroundResource(R.drawable.obuttondrawable);
                }
            }
        }
        if(gameOver){
            showToast(toastMessage,toastDrawable);
        }
        super.onResume();
    }

    public void Add(View view) {
        Button btn = (Button) view;
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
        btn.setTextSize(24f);
    }

    private void check() {

        for (int i = 0; i < 9; i++) {
            texts[i] = btn[i].getText().toString();
        }
        if (texts[0].equals(texts[1]) && texts[1].equals(texts[2]) && !texts[0].equals("")) {
            if (texts[0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (texts[0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[0].equals(texts[3]) && texts[3].equals(texts[6]) && !texts[0].equals("")) {
            if (texts[0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (texts[0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[0].equals(texts[4]) && texts[4].equals(texts[8]) && !texts[0].equals("")) {
            if (texts[0].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (texts[0].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[2].equals(texts[4]) && texts[4].equals(texts[6]) && !texts[2].equals("")) {
            if (texts[2].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (texts[2].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[1].equals(texts[4]) && texts[4].equals(texts[7]) && !texts[1].equals("")) {
            if (texts[1].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (texts[1].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[3].equals(texts[4]) && texts[4].equals(texts[5]) && !texts[3].equals("")) {
            if (texts[3].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (texts[3].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[6].equals(texts[7]) && texts[7].equals(texts[8]) && !texts[6].equals("")) {
            if (texts[6].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (texts[6].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[2].equals(texts[5]) && texts[5].equals(texts[8]) && !texts[2].equals("")) {
            if (texts[6].equals("X")) {
                showToast("Player X is the winner", R.drawable.winning);
                xCount++;
            } else if (texts[6].equals("O")) {
                showToast("Player O is the winner", R.drawable.winning);
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (!texts[0].equals("") && !texts[1].equals("") && !texts[2].equals("") && !texts[3].equals("") && !texts[4].equals("") && !texts[5].equals("") && !texts[6].equals("") && !texts[7].equals("") && !texts[8].equals("")) {
            showToast("Stalemate !", R.drawable.stalemate);
        }
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
        toastMessage=winner;
        toastDrawable=background;
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
        outState.putStringArray("textsArray", texts);
        outState.putInt("oCount", oCount);
        outState.putInt("xCount", xCount);
        outState.putInt("playerID", playerID);
        outState.putBoolean("gameOver",gameOver);
        outState.putString("toastMessage",toastMessage);
        outState.putInt("toastDrawable",toastDrawable);
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