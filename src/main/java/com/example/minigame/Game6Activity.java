package com.example.minigame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Random;

public class Game6Activity extends AppCompatActivity {
    private static final String TAG = "tag";

    Handler handler = new Handler();
    SharedPreferences sharedPreferences_game6;
    private Button[] button = new Button[16];
    private TextView textView_time, textView_score;
    private Random random = new Random();

    int score = 0;
    int max = 0;
    private int currentRedButtonIndex = -1; // 현재 색상 변경된 버튼의 인덱스 저장
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        button[0] = findViewById(R.id.button1_game6);
        button[1] = findViewById(R.id.button2_game6);
        button[2] = findViewById(R.id.button3_game6);
        button[3] = findViewById(R.id.button4_game6);
        button[4] = findViewById(R.id.button5_game6);
        button[5] = findViewById(R.id.button6_game6);
        button[6] = findViewById(R.id.button7_game6);
        button[7] = findViewById(R.id.button8_game6);
        button[8] = findViewById(R.id.button9_game6);
        button[9] = findViewById(R.id.button10_game6);
        button[10] = findViewById(R.id.button11_game6);
        button[11] = findViewById(R.id.button12_game6);
        button[12] = findViewById(R.id.button13_game6);
        button[13] = findViewById(R.id.button14_game6);
        button[14] = findViewById(R.id.button15_game6);
        button[15] = findViewById(R.id.button16_game6);
        textView_time = findViewById(R.id.textView_game6_time);
        textView_score = findViewById(R.id.textView_game6_score);

        sharedPreferences_game6 = getSharedPreferences("dialog_game6", Context.MODE_PRIVATE);
        int key = sharedPreferences_game6.getInt("key6", 0);
        Log.d(TAG, "저장된 key 값" + key);
        max = key;

        currentRedButtonIndex = random.nextInt(button.length);

        // 새로 선택된 버튼의 배경색을 #123456 색상으로 변경
        button[currentRedButtonIndex].setBackgroundColor(R.drawable.button_game6_circle_red);

        Dialog dialog = new Dialog(Game6Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game6);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.setCancelable(false);
        Button start = (Button)dialog.findViewById(R.id.dialog_game6_button);
        Button end = (Button)dialog.findViewById(R.id.dialog_game6_button2);
        TextView dialog_score = (TextView)dialog.findViewById(R.id.dialog_game6_text2);
        dialog_score.setText("최고 기록 : "+key+"점");
        for(int i = 0; i < 16; i++){
            button[i].setEnabled(false);
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                textView_time.setText("준비하시고!!");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView_time.setText("3");
                    }
                },1000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView_time.setText("2");
                    }
                },2000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView_time.setText("1");
                    }
                },3000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < 16; i++){
                            button[i].setEnabled(true);
                        }
                    }
                },4000);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Game6Activity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        for (Button button : button) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeRandomButtonColor();
                }
            });
        }
    }
    private void changeRandomButtonColor() {
        // 기존 색상 변경된 버튼 색깔 초기화 (이전에 색상이 변경된 경우)
        if (currentRedButtonIndex != -1) {
            button[currentRedButtonIndex].setBackgroundColor(R.drawable.button_game6_circle); // 기본 색상으로 변경 (원하는 기본 색상 사용)
        }

        // 0부터 15 사이의 새로운 랜덤 인덱스 생성
        currentRedButtonIndex = random.nextInt(button.length);

        // 새로 선택된 버튼의 배경색을 #123456 색상으로 변경
        button[currentRedButtonIndex].setBackgroundColor(R.drawable.button_game6_circle_red);
    }
}
