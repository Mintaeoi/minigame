package com.example.minigame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout  layout1, layout2, layout3, layout4, layout5, layout6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
        새롭게 생긴 초기 코드 값
        EdgeToEdge.enable(this); // status bar(배터리 표시되는 부분)부터 Navigation Bar(하단의 안드로이드 버튼 영역)까지 액티비티 화면이 나오게 하는 코드
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지

        layout1 = findViewById(R.id.main_item1);
        layout2 = findViewById(R.id.main_item2);
        layout3 = findViewById(R.id.main_item3);
        layout4 = findViewById(R.id.main_item4);
        layout5 = findViewById(R.id.main_item5);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                startActivity(intent);
                finish();
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game2Activity.class);
                startActivity(intent);
                finish();
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game3Activity.class);
                startActivity(intent);
                finish();
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game4Activity.class);
                startActivity(intent);
                finish();
            }
        });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game5Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        int aa = 0;
        if(aa == 1){
            super.onBackPressed();
        }
    }
}