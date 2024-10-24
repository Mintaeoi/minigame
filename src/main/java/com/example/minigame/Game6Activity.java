package com.example.minigame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private Button[] button1 = new Button[8];
    private Button[] button2 = new Button[8];
    private TextView textView_time, textView_score;
    private Random random = new Random();

    int score = 0;
    int max = 0;
    private int currentRedButtonIndex1 = -1;// 현재 색상 변경된 버튼의 인덱스 저장1
    private int currentRedButtonIndex1_1 = 0;
    private int currentRedButtonIndex2 = -1;// 현재 색상 변경된 버튼의 인덱스 저장2
    private int currentRedButtonIndex2_1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        button1[0] = findViewById(R.id.button1_game6);
        button1[1] = findViewById(R.id.button2_game6);
        button1[2] = findViewById(R.id.button3_game6);
        button1[3] = findViewById(R.id.button4_game6);
        button1[4] = findViewById(R.id.button5_game6);
        button1[5] = findViewById(R.id.button6_game6);
        button1[6] = findViewById(R.id.button7_game6);
        button1[7] = findViewById(R.id.button8_game6);
        button2[0] = findViewById(R.id.button9_game6);
        button2[1] = findViewById(R.id.button10_game6);
        button2[2] = findViewById(R.id.button11_game6);
        button2[3] = findViewById(R.id.button12_game6);
        button2[4] = findViewById(R.id.button13_game6);
        button2[5] = findViewById(R.id.button14_game6);
        button2[6] = findViewById(R.id.button15_game6);
        button2[7] = findViewById(R.id.button16_game6);
        textView_time = findViewById(R.id.textView_game6_time);
        textView_score = findViewById(R.id.textView_game6_score);

        sharedPreferences_game6 = getSharedPreferences("dialog_game6", Context.MODE_PRIVATE);
        int key = sharedPreferences_game6.getInt("key6", 0);
        Log.d(TAG, "저장된 key 값" + key);
        max = key;

        currentRedButtonIndex1 = random.nextInt(button1.length);
        currentRedButtonIndex2 = random.nextInt(button2.length);

        // 새로 선택된 버튼의 배경색을 #123456 색상으로 변경
        button1[currentRedButtonIndex1].setBackgroundResource(R.drawable.button_game6_circle_red);
        button1[currentRedButtonIndex1].setTag("red1");

        button2[currentRedButtonIndex2].setBackgroundResource(R.drawable.button_game6_circle_red);
        button2[currentRedButtonIndex2].setTag("red2");

        Dialog dialog = new Dialog(Game6Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game6);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);
        Button start = (Button)dialog.findViewById(R.id.dialog_game6_button);
        Button end = (Button)dialog.findViewById(R.id.dialog_game6_button2);
        TextView dialog_score = (TextView)dialog.findViewById(R.id.dialog_game6_text2);
        dialog_score.setText("최고 기록 : "+key+"점");
        for(int i = 0; i < 8; i++){
            button1[i].setEnabled(false);
            button2[i].setEnabled(false);
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
                        for(int i = 0; i < 8; i++){
                            button1[i].setEnabled(true);
                            button2[i].setEnabled(true);
                        }
                        startTimer();
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

        for (Button button : button1) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("red1".equals(v.getTag())) {
                        // 버튼이 빨간색으로 설정된 경우 처리할 코드
                        score++;  // 예: 점수 증가
                        textView_score.setText(score+"점");
                    }else{
                        score--;
                        score--;
                        textView_score.setText(score+"점");
                    }
                    changeRandomButtonColor1();
                }
            });
        }
        for (Button button : button2) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("red2".equals(v.getTag())) {
                        // 버튼이 빨간색으로 설정된 경우 처리할 코드
                        score++;  // 예: 점수 증가
                        textView_score.setText(score+"점");
                    }else{
                        score--;
                        score--;
                        textView_score.setText(score+"점");
                    }
                    changeRandomButtonColor2();
                }
            });
        }
    }
    private void changeRandomButtonColor1() {
        // 기존 색상 변경된 버튼 색깔 초기화 (이전에 색상이 변경된 경우)
        if (currentRedButtonIndex1 != -1) {
            button1[currentRedButtonIndex1].setBackgroundResource(R.drawable.button_game6_circle); // 기본 색상으로 변경 (원하는 기본 색상 사용)
            for (int i = 0; i < 8; i++) {
                button1[i].setTag("green1");
            }
        }
        //중복된 위치에 등장시키지 않기 위해서
        currentRedButtonIndex1_1 = currentRedButtonIndex1;
        do {
            // 0부터 15 사이의 새로운 랜덤 인덱스 생성
            currentRedButtonIndex1 = random.nextInt(button1.length);
        } while (currentRedButtonIndex1 == currentRedButtonIndex1_1);
        button1[currentRedButtonIndex1].setBackgroundResource(R.drawable.button_game6_circle_red);
        button1[currentRedButtonIndex1].setTag("red1");

    }
    private void changeRandomButtonColor2() {
        // 기존 색상 변경된 버튼 색깔 초기화 (이전에 색상이 변경된 경우)
        if (currentRedButtonIndex2 != -1) {
            button2[currentRedButtonIndex2].setBackgroundResource(R.drawable.button_game6_circle); // 기본 색상으로 변경 (원하는 기본 색상 사용)
            for (int i = 0; i < 8; i++) {
                button2[i].setTag("green2");
            }
        }
        //중복된 위치에 등장시키지 않기 위해서
        currentRedButtonIndex2_1 = currentRedButtonIndex2;
        do {
            // 0부터 15 사이의 새로운 랜덤 인덱스 생성
            currentRedButtonIndex2 = random.nextInt(button2.length);
        } while (currentRedButtonIndex2 == currentRedButtonIndex2_1);
        // 새로 선택된 버튼의 배경색을 #123456 색상으로 변경
        button2[currentRedButtonIndex2].setBackgroundResource(R.drawable.button_game6_circle_red);
        button2[currentRedButtonIndex2].setTag("red2");
    }

    private void startTimer(){
        new CountDownTimer(15000, 10) {

            public void onTick(long millisUntilFinished) {
                // 매 틱마다 호출되는 코드
                int seconds = (int) (millisUntilFinished / 1000);
                int milliseconds = (int) ((millisUntilFinished % 1000)/10);
                String timeLeftFormatted = String.format("%02d:%02d", seconds, milliseconds);
                textView_time.setText(timeLeftFormatted);
            }

            public void onFinish() {
                // 타이머가 끝났을 때 호출되는 코드
                textView_time.setText("00:00");
                for(int i = 0; i < 8; i++){
                    button1[i].setEnabled(false);
                    button2[i].setEnabled(false);
                }


                if(score > max){
                    max = score;
                    SharedPreferences.Editor editor = sharedPreferences_game6.edit();
                    editor.putInt("key6", max);
                    editor.apply();
                    Log.d(TAG, "현재 점수 : " + score);
                    Log.d(TAG, "현재 최고 점수 : " + max);
                }
                showRestartDialog();
            }
        }.start();
    }
    private void showRestartDialog() {
        Dialog dialog = new Dialog(Game6Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game6);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView title = dialog.findViewById(R.id.dialog_game6_title);
        TextView dialog_score = dialog.findViewById(R.id.dialog_game6_text2);
        Button restart = dialog.findViewById(R.id.dialog_game6_button);
        Button end = dialog.findViewById(R.id.dialog_game6_button2);
        dialog_score.setText("점수: " + score + "점" +"\n최고 기록: " + max + "점");

        title.setText("게임 결과");
        restart.setText("다시 시작");
        restart.setEnabled(false);
        end.setEnabled(false);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                restart.setEnabled(true);
                end.setEnabled(true);
            }
        },1000);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(Game6Activity.this, Game6Activity.class);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
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
    }
    @Override
    public void onBackPressed() {
        int aa = 0;
        if(aa == 1){
            super.onBackPressed();
        }
    }
}
