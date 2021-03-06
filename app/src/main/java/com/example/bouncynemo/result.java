package com.example.bouncynemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class result extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_result);

        TextView scoreLabel= (TextView)findViewById(R.id.scoreLabel);
        TextView highScoreLabel=(TextView)findViewById(R.id.highScoreLabel);

        int score=getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText("Score : "+score);

        SharedPreferences setting=getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore=setting.getInt("HIGH_SCORE",0);
        if (score>highScore){
            highScoreLabel.setText("High Score : "+score);
          //now save
            SharedPreferences.Editor editor=setting.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();
        }else {
            highScoreLabel.setText("High Score : "+highScore);
        }
    }


    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void quit(View view){
        finish();
    }


}
