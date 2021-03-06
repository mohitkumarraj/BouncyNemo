package com.example.bouncynemo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
  private GameView gameView;
  private Handler handler=new Handler();
  private final static long TIME_INTERVAL=30;

//  public  double blue_speed=getIntent().getDoubleExtra("bs",0);;
//  public double black_speed=getIntent().getDoubleExtra("bls",0);;
//  public double obstacle_speed=getIntent().getDoubleExtra("obs",0);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        gameView=new GameView(this);
        setContentView(gameView);
//        gameView.blueSpeed=getIntent().getDoubleExtra("bs",0);
//        gameView.blackSpeed=getIntent().getDoubleExtra("bls",0);
//        gameView.obs_speed=getIntent().getDoubleExtra("obs",0);

        Timer timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                        if (gameView.life_count==0){
                            Intent intent = new Intent(getApplicationContext(), result.class);
                            intent.putExtra("SCORE",gameView.score);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            gameView.life_count=-1;
                            finish();
                        }

                    }
                });
            }
        },0,TIME_INTERVAL);


    }
}