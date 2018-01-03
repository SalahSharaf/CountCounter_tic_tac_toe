package com.example.android.countcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int playerID = 0;
    String[] texts;
    Button[] btn;
    public static int xCount,oCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = new Button[]{findViewById(R.id.b1), findViewById(R.id.b2), findViewById(R.id.b3), findViewById(R.id.b4), findViewById(R.id.b5), findViewById(R.id.b6)
                , findViewById(R.id.b7), findViewById(R.id.b8), findViewById(R.id.b9)};
        texts = new String[btn.length];
        for (int i = 0; i < btn.length; i++) {
            texts[i] = btn[i].getText().toString();
            btn[i].setText("");
        }
        TextView textX=findViewById(R.id.playerOcount);
        TextView textO=findViewById(R.id.playerXcount);
        textX.setText(""+oCount);
        textO.setText(""+xCount);
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

        for (int i = 0; i < btn.length; i++) {
            texts[i] = btn[i].getText().toString();
        }
        if (texts[0].equals(texts[1]) && texts[1].equals(texts[2]) && !texts[0].equals("")) {
            if (texts[0].equals("X")) {
                showToast("Player X is the winner");
                xCount++;
            } else if (texts[0].equals("O")) {
                showToast("Player O is the winner");
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[0].equals(texts[3]) && texts[3].equals(texts[6]) && !texts[0].equals("")) {
            if (texts[0].equals("X")) {
                showToast("Player X is the winner");
                xCount++;
            } else if (texts[0].equals("O")) {
                showToast("Player O is the winner");
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[0].equals(texts[4]) && texts[4].equals(texts[8]) && !texts[0].equals("")) {
            if (texts[0].equals("X")) {
                showToast("Player X is the winner");
                xCount++;
            } else if (texts[0].equals("O")) {
                showToast("Player O is the winner");
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[2].equals(texts[4]) && texts[4].equals(texts[6]) && !texts[2].equals("")) {
            if (texts[2].equals("X")) {
                showToast("Player X is the winner");
                xCount++;
            } else if (texts[2].equals("O")) {
                showToast("Player O is the winner");
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[1].equals(texts[4]) && texts[4].equals(texts[7]) && !texts[1].equals("")) {
            if (texts[1].equals("X")) {
                showToast("Player X is the winner");
                xCount++;
            } else if (texts[1].equals("O")) {
                showToast("Player O is the winner");
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[3].equals(texts[4]) && texts[4].equals(texts[5]) && !texts[3].equals("")) {
            if (texts[3].equals("X")) {
                showToast("Player X is the winner");
                xCount++;
            } else if (texts[3].equals("O")) {
                showToast("Player O is the winner");
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[6].equals(texts[7]) && texts[7].equals(texts[8]) && !texts[6].equals("")) {
            if (texts[6].equals("X")) {
                showToast("Player X is the winner");
                xCount++;
            } else if (texts[6].equals("O")) {
                showToast("Player O is the winner");
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        } else if (texts[2].equals(texts[5]) && texts[5].equals(texts[8]) && !texts[2].equals("")) {
            if (texts[6].equals("X")) {
                showToast("Player X is the winner");
                xCount++;
            } else if (texts[6].equals("O")) {
                showToast("Player O is the winner");
                oCount++;
            }
            for (int i = 0; i < btn.length; i++) {
                btn[i].setEnabled(false);
            }
        }
    }

    private void showToast(String winner) {
        CardView layout=findViewById(R.id.menu);
        layout.setVisibility(View.VISIBLE);
        TextView text=findViewById(R.id.Winnerplayer);
        text.setText(winner);
    }

    public void rematch(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void mainMenu(View view){
        Intent intent=new Intent(this,MainMenu.class);
        startActivity(intent);
    }

}