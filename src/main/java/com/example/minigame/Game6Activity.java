package com.example.minigame;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Game6Activity extends AppCompatActivity {
    Button button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button10, button11, button12,
            button13, button14, button15, button16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        button1 = findViewById(R.id.button1_game6);
        button2 = findViewById(R.id.button2_game6);
        button3 = findViewById(R.id.button3_game6);
        button4 = findViewById(R.id.button4_game6);
        button5 = findViewById(R.id.button5_game6);
        button6 = findViewById(R.id.button6_game6);
        button7 = findViewById(R.id.button7_game6);
        button8 = findViewById(R.id.button8_game6);
        button9 = findViewById(R.id.button9_game6);
        button10 = findViewById(R.id.button10_game6);
        button11 = findViewById(R.id.button11_game6);
        button12 = findViewById(R.id.button12_game6);
        button13 = findViewById(R.id.button13_game6);
        button14 = findViewById(R.id.button14_game6);
        button15 = findViewById(R.id.button15_game6);
        button16 = findViewById(R.id.button16_game6);
    }
}
