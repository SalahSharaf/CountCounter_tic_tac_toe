package com.example.android.countcounter;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //zeroing the result for both players
        MainActivity.xCount = 0;
        MainActivity.oCount = 0;
        //
        MainActivity.singlePlayer = false;
        MainActivity.multiPlayer = false;
    }

    public void newMultiGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        MainActivity.multiPlayer = true;
    }

    public void newSingleGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        MainActivity.singlePlayer = true;
    }

    public void Exit(View view) {
        finishAffinity();
    }

    @Override
    public void onBackPressed() {
        //don't go back
    }

}