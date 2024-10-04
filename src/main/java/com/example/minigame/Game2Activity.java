package com.example.minigame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Game2Activity extends AppCompatActivity {
    private static final String TAG = "tag";
    SharedPreferences sharedPreferences_game2;
    Button button;
    TextView textView;
    private Handler timerHandler;
    private Runnable timerRunnable;
    private boolean isTimerRunning = false;
    private long startTime = 0;
    private long elapsedTime = 0;
    private long currentTime = 0;
    private int seconds = 0;
    private int milliseconds = 0;
    private long elapsedTimeFrom5Seconds = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지
        sharedPreferences_game2 = getSharedPreferences("dialog_game2", Context.MODE_PRIVATE);
        int key = sharedPreferences_game2.getInt("key2", 0);
        Dialog dialog = new Dialog(Game2Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Button start = (Button)dialog.findViewById(R.id.dialog_game2_button);
        Button end = (Button)dialog.findViewById(R.id.dialog_game2_button2);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_game2.edit();
                editor.putInt("key2", 1);
                editor.apply();
                dialog.dismiss();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(Game2Activity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        textView = findViewById(R.id.textView_game2_time);
        button = findViewById(R.id.button_game2);

        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                updateTimer();
                timerHandler.postDelayed(this, 10); // 10밀리초마다 업데이트
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTimerRunning) {
                    pauseTimer();
                } else {
                    textView.setText("00 ' 000");
                    elapsedTime = 0;
                    startTimer();
                }
            }
        });
    }

    private void startTimer() {
        startTime = System.currentTimeMillis() - elapsedTime;
        timerHandler.postDelayed(timerRunnable, 0);
        button.setText("S T O P");
        isTimerRunning = true;
    }

    private void pauseTimer() {
        timerHandler.removeCallbacks(timerRunnable);
        button.setText("다시하기");
        button.setEnabled(false);
        isTimerRunning = false;
        long seconds = (elapsedTime - 5000) / 1000;
        long milliseconds = (((elapsedTime - 5000) % 1000));
        String elapsedTimeString = String.format("%02d:%03d", Math.abs(seconds), Math.abs(milliseconds));

        Dialog dialog = new Dialog(Game2Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        TextView textView = (TextView)dialog.findViewById(R.id.dialog_game2_text1);
        TextView title = (TextView)dialog.findViewById(R.id.dialog_game2_title);
        Button restart = (Button)dialog.findViewById(R.id.dialog_game2_button);
        Button end = (Button)dialog.findViewById(R.id.dialog_game2_button2);
        textView.setText("누른 후 5초와 시간 차이는 "+elapsedTimeString + "입니다.");
        title.setText("게임 결과");

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(Game2Activity.this, Game2Activity.class);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Game2Activity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void updateTimer() {
        currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - startTime;
        seconds = (int) (elapsedTime / 1000);
        milliseconds = (int) ((elapsedTime % 1000));
        textView.setText(String.format("%02d ' %03d", seconds, milliseconds));
    }

    @Override
    public void onBackPressed() {
        int aa = 0;
        if(aa == 1){
            super.onBackPressed();
        }
    }
}
