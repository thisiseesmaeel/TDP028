package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private BlankFragment blankFragment;
    private BlankFragment2 blankFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (blankFragment == null)
            blankFragment = BlankFragment.newInstance();
        if (blankFragment2 == null)
            blankFragment2 = BlankFragment2.newInstance(" ", " ");
            blankFragment2.setOnTDP028(new TDP028Listener() {
                @Override
                public void newData(String data) {
                    swapBackToFirst(data);
                }
            });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, blankFragment).commitNow();
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapToSecond();
            }
        });
    }

    private void swapBackToFirst(String data) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, blankFragment).commitNow();
    }

    private void swapToSecond() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, blankFragment2).commitNow();
    }
}