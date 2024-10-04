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

public class Game1Activity extends AppCompatActivity {
    private static final String TAG = "tag";
    Handler handler = new Handler();
    SharedPreferences sharedPreferences_game1;
    TextView textView_time,textView_score;
    Button button;
    int score = 0;
    int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지

        textView_score = findViewById(R.id.textView_game1_score);
        textView_time = findViewById(R.id.textView_game1_time);
        button = findViewById(R.id.button_game1);

        sharedPreferences_game1 = getSharedPreferences("dialog_game1", Context.MODE_PRIVATE);
        int key = sharedPreferences_game1.getInt("key1", 0);
        Log.d(TAG, "저장된 key 값" + key);
        max = key;


        Dialog dialog = new Dialog(Game1Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.setCancelable(false);
        Button start = (Button)dialog.findViewById(R.id.dialog_game1_button);
        Button end = (Button)dialog.findViewById(R.id.dialog_game1_button2);
        TextView dialog_score = (TextView)dialog.findViewById(R.id.dialog_game1_text2);
        dialog_score.setText("최고 기록 : "+key+"점");
        button.setEnabled(false);
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
                        button.setEnabled(true);
                    }
                },4000);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Game1Activity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                textView_score.setText((String.valueOf(score))+"점");
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
                button.setEnabled(false);


                if(score > max){
                    max = score;
                    SharedPreferences.Editor editor = sharedPreferences_game1.edit();
                    editor.putInt("key1", max);
                    editor.apply();
                    Log.d(TAG, "현재 점수 : " + score);
                    Log.d(TAG, "현재 최고 점수 : " + max);
                }
                showRestartDialog();
            }
        }.start();
    }
    private void showRestartDialog() {
        Dialog dialog = new Dialog(Game1Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.setCancelable(false);
        TextView dialog_score = dialog.findViewById(R.id.dialog_game1_text2);
        TextView title = dialog.findViewById(R.id.dialog_game1_title);
        Button restart = dialog.findViewById(R.id.dialog_game1_button);
        Button end = dialog.findViewById(R.id.dialog_game1_button2);
        dialog_score.setText("점수: " + score + "점" +"\n최고 기록: " + max + "점");
        title.setText("게임 결과");
        restart.setEnabled(false);
        end.setEnabled(false);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                restart.setEnabled(true);
                end.setEnabled(true);
            }
        },1000);

        restart.setText("다시 시작");
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(Game1Activity.this, Game1Activity.class);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Game1Activity.this, MainActivity.class);
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
