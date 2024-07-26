package com.example.minigame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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

public class Game3Activity extends AppCompatActivity {
    private static final String TAG = "tag";
    Handler handler = new Handler();
    SharedPreferences sharedPreferences_game3;
    TextView textView_time,textView_score,textView_color;
    Button button_red, button_blue;
    int score = 0;
    int max = 0;

    String[] color = {"빨강", "파랑"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지

        textView_time = findViewById(R.id.textView_game3_time);
        textView_score = findViewById(R.id.textView_game3_score);
        textView_color = findViewById(R.id.textView_game3_color);

        button_red = findViewById(R.id.button_game3_red);
        button_blue = findViewById(R.id.button_game3_blue);

        sharedPreferences_game3 = getSharedPreferences("dialog_game3", Context.MODE_PRIVATE);
        int key = sharedPreferences_game3.getInt("key3", 0);
        Log.d(TAG, "저장된 key 값" + key);
        max = key;


        Dialog dialog = new Dialog(Game3Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game3);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Button start = (Button)dialog.findViewById(R.id.dialog_game3_button);
        Button end = (Button)dialog.findViewById(R.id.dialog_game3_button2);
        TextView dialog_score = (TextView)dialog.findViewById(R.id.dialog_game3_text2);
        dialog_score.setText("최고 기록 : "+key+"점");
        button_red.setEnabled(false);
        button_blue.setEnabled(false);
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
                        startTimer();
                        button_red.setEnabled(true);
                        button_blue.setEnabled(true);
                    }
                },4000);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Game3Activity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        int random1 = (int) (Math.random()*color.length);
        textView_color.setText(""+color[random1]);
        if(textView_color.getText().toString().equals("빨강")){
            textView_color.setTextColor(Color.parseColor("#FF0000"));
        }else if(textView_color.getText().toString().equals("파랑")){
            textView_color.setTextColor(Color.parseColor("#0040FF"));
        }
        button_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView_color.getText().toString().equals("빨강")){
                    score++;
                    int random2 = (int) (Math.random()*color.length);
                    textView_color.setText(""+color[random2]);
                    if(textView_color.getText().toString().equals("빨강")){
                        textView_color.setTextColor(Color.parseColor("#FF0000"));
                    }else if(textView_color.getText().toString().equals("파랑")){
                        textView_color.setTextColor(Color.parseColor("#0040FF"));
                    }
                    textView_score.setText(score+"점");
                }else if(textView_color.getText().toString().equals("파랑")){
                    score--;
                    score--;
                    int random2 = (int) (Math.random()*color.length);
                    textView_color.setText(""+color[random2]);
                    if(textView_color.getText().toString().equals("빨강")){
                        textView_color.setTextColor(Color.parseColor("#FF0000"));
                    }else if(textView_color.getText().toString().equals("파랑")){
                        textView_color.setTextColor(Color.parseColor("#0040FF"));
                    }
                    textView_score.setText(score+"점");
                }
                Log.d(TAG,"현재 점수 : " + score);
            }
        });
        button_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView_color.getText().toString().equals("파랑")){
                    score++;
                    int random2 = (int) (Math.random()*color.length);
                    textView_color.setText(""+color[random2]);
                    if(textView_color.getText().toString().equals("빨강")){
                        textView_color.setTextColor(Color.parseColor("#FF0000"));
                    }else if(textView_color.getText().toString().equals("파랑")){
                        textView_color.setTextColor(Color.parseColor("#0040FF"));
                    }
                    textView_score.setText(score+"점");
                }else if(textView_color.getText().toString().equals("빨강")){
                    score--;
                    score--;
                    int random2 = (int) (Math.random()*color.length);
                    textView_color.setText(""+color[random2]);
                    if(textView_color.getText().toString().equals("빨강")){
                        textView_color.setTextColor(Color.parseColor("#FF0000"));
                    }else if(textView_color.getText().toString().equals("파랑")){
                        textView_color.setTextColor(Color.parseColor("#0040FF"));
                    }
                    textView_score.setText(score+"점");
                }
                Log.d(TAG,"현재 점수 : " + score);
            }
        });

    }

    private void startTimer(){
        new CountDownTimer(10000, 10) {

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
                button_red.setEnabled(false);
                button_blue.setEnabled(false);


                if(score > max){
                    max = score;
                    SharedPreferences.Editor editor = sharedPreferences_game3.edit();
                    editor.putInt("key3", max);
                    editor.apply();
                    Log.d(TAG, "현재 점수 : " + score);
                    Log.d(TAG, "현재 최고 점수 : " + max);
                }
                showRestartDialog();
            }
        }.start();
    }
    private void showRestartDialog() {
        Dialog dialog = new Dialog(Game3Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        TextView dialog_score = dialog.findViewById(R.id.dialog_game1_text2);
        Button restart = dialog.findViewById(R.id.dialog_game1_button);
        Button end = dialog.findViewById(R.id.dialog_game1_button2);
        dialog_score.setText("점수: " + score + "점" +"\n최고 기록: " + max + "점");

        restart.setText("다시 시작");
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(Game3Activity.this, Game3Activity.class);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Game3Activity.this, MainActivity.class);
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
