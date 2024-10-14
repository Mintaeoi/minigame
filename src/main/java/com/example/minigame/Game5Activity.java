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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Random;

public class Game5Activity extends AppCompatActivity {
    private static final String TAG = "tag";
    Handler handler = new Handler();
    SharedPreferences sharedPreferences_game5;
    TextView textView_random, textView_score;
    Button button_success, button_fail;
    ImageView life1,life2,life3;
    int score = 0;
    int chance = 3;
    int max = 0;

    int successProbability = 95;
    int failProbability = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        textView_random = findViewById(R.id.textView_game5_random);
        textView_score = findViewById(R.id.textView_game5_score);
        button_success = findViewById(R.id.button_game5_success);
        button_fail = findViewById(R.id.button_game5_fail);

        life1 = findViewById(R.id.imageView_game5_life1);
        life2 = findViewById(R.id.imageView_game5_life2);
        life3 = findViewById(R.id.imageView_game5_life3);

        sharedPreferences_game5 = getSharedPreferences("dialog_game5", Context.MODE_PRIVATE);
        int key = sharedPreferences_game5.getInt("key5", 0);
        Log.d(TAG, "저장된 key 값" + key);
        max = key;

        Dialog dialog = new Dialog(Game5Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game5);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);
        Button start = (Button)dialog.findViewById(R.id.dialog_game5_button);
        Button end = (Button)dialog.findViewById(R.id.dialog_game5_button2);
        TextView dialog_score = (TextView)dialog.findViewById(R.id.dialog_game5_text2);
        dialog_score.setText("최고 기록 : "+key+"점");
        button_success.setEnabled(false);
        button_fail.setEnabled(false);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                textView_random.setText("준비하시고!!");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView_random.setText("3");
                    }
                },1000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView_random.setText("2");
                    }
                },2000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView_random.setText("1");
                    }
                },3000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button_success.setEnabled(true);
                        button_fail.setEnabled(true);
                        textView_random.setText("95%");
                    }
                },4000);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Game5Activity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        button_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game(true); // 성공에 배팅
            }
        });
        button_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game(false); // 실패에 배팅
            }
        });
    }
    private void Game(boolean isSuccessBet) {
        if (chance > 0) {
            Random random = new Random();
            int randomValue = random.nextInt(100)+1; // 1~100까지 난수 생성
            boolean isWin = isSuccessBet ? randomValue <= successProbability : randomValue <= failProbability; //success or fail안에 들어올 확률

            if (isWin) { // 배팅을 성공했을 때
                score++;
                textView_score.setText(score+"점");
                successProbability = random.nextInt(100)+1;
                failProbability = 100-successProbability;
                textView_random.setText(successProbability+"%");

            } else { // 배팅을 실패했을 때
                chance--;
                if(chance == 2){
                    life3.setImageResource(R.drawable.game5_life_lost);
                }else if(chance == 1){
                    life2.setImageResource(R.drawable.game5_life_lost);
                }else if(chance == 0){
                    life1.setImageResource(R.drawable.game5_life_lost);
                }
                successProbability = random.nextInt(100)+1;
                failProbability = 100-successProbability;
                textView_random.setText(successProbability+"%");
            }
            if (chance == 0) {
                showRestartDialog();
            }
        }
    }
    private void showRestartDialog() {
        button_success.setEnabled(false);
        button_fail.setEnabled(false);


        if(score > max){
            max = score;
            SharedPreferences.Editor editor = sharedPreferences_game5.edit();
            editor.putInt("key5", max);
            editor.apply();
            Log.d(TAG, "현재 점수 : " + score);
            Log.d(TAG, "현재 최고 점수 : " + max);
        }
        Dialog dialog = new Dialog(Game5Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game5);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);
        TextView dialog_score = dialog.findViewById(R.id.dialog_game5_text2);
        TextView title = dialog.findViewById(R.id.dialog_game5_title);
        Button restart = dialog.findViewById(R.id.dialog_game5_button);
        Button end = dialog.findViewById(R.id.dialog_game5_button2);
        dialog_score.setText("점수: " + score + "점" +"\n최고 기록: " + max + "점");

        title.setText("게임 결과");
        restart.setText("다시 시작");
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(Game5Activity.this, Game5Activity.class);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Game5Activity.this, MainActivity.class);
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
