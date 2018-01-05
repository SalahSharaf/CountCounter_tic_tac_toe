package com.example.android.countcounter;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int playerID = 0;
    String[] texts;
    Button[] btn;
    public static int xCount, oCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = new Button[]{findViewById(R.id.b1), findViewById(R.id.b2), findViewById(R.id.b3), findViewById(R.id.b4), findViewById(R.id.b5), findViewById(R.id.b6)
                , findViewById(R.id.b7), findViewById(R.id.b8), findViewById(R.id.b9)};
        texts = new String[btn.length];
        TextView textX = findViewById(R.id.playerXcount);
        TextView textO = findViewById(R.id.playerOcount);
        textX.setText("" + xCount);
        textO.setText("" + oCount);
        if (savedInstanceState != null) {
            texts = savedInstanceState.getStringArray("textsArray");
            xCount = savedInstanceState.getInt("xCount");
            oCount = savedInstanceState.getInt("oCount");
        }
        final CardView layout = findViewById(R.id.menu);
        layout.setTag("22");
        if (layout.getVisibility() == View.VISIBLE) {
            layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Create a new ClipData.
                    // This is done in two steps to provide clarity. The convenience method
                    // ClipData.newPlainText() can create a plain text ClipData in one step.

                    // Create a new ClipData.Item from the ImageView object's tag
                    ClipData.Item item = new ClipData.Item(v.getTag().toString());

                    // Create a new ClipData using the tag a2s a label, the plain text MIME type, and
                    // the already-created item. This will create a new ClipDescription object within the
                    // ClipData, and set its MIME type entry to "text/plain"
                    ClipData dragData = new ClipData(v.getTag().toString(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

                    // Instantiates the drag shadow builder.
                    View.DragShadowBuilder myShadow = new MyDragShadowBuilder(layout);

                    // Starts the drag

                    v.startDrag(dragData,  // the data to be dragged
                            myShadow,  // the drag shadow builder
                            null,      // no need to use local data
                            0          // flags (not currently used, set to 0)
                    );

                    return false;
                }
            });
        }
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
        } else if(!texts[0].equals("")&&!texts[1].equals("")&&!texts[2].equals("")&&!texts[3].equals("")&&!texts[4].equals("")&&!texts[5].equals("")&&!texts[6].equals("")&&!texts[7].equals("")&&!texts[8].equals("")){
            showToast("Stalemate !",R.drawable.stalemate);
        }
    }

    private void showToast(String winner, int background) {
        CardView cvlayout = findViewById(R.id.menu);
        FrameLayout flayout = findViewById(R.id.winningbackground);
        TextView text = findViewById(R.id.Winnerplayer);
        cvlayout.setVisibility(View.VISIBLE);
        flayout.setBackgroundResource(background);
        cvlayout.setBackgroundResource(background);
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
    public void onSaveInstanceState(Bundle savedState) {
        savedState.putStringArray("textsArray", texts);
        savedState.putInt("cCount", xCount);
        savedState.putInt("xCount", oCount);
        super.onSaveInstanceState(savedState);
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable thing
        private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {

            // Stores the View parameter passed to myDragShadowBuilder.
            super(v);

            // Creates a draggable image that will fill the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics(Point size, Point touch) {
            // Defines local variables
            int width, height;

            // Sets the width of the shadow to half the width of the original View
            width = getView().getWidth() / 2;

            // Sets the height of the shadow to half the height of the original View
            height = getView().getHeight() / 2;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
            // Canvas that the system will provide. As a result, the drag shadow will fill the
            // Canvas.
            shadow.setBounds(0, 0, width, height);

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
            size.set(width, height);

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {

            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas);
        }
    }
}