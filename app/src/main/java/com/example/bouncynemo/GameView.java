package com.example.bouncynemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.util.SparseIntArray;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameView extends View{

//     MainActivity mobj=new MainActivity();

    //canvas
    private int canvasWidth;
    private int canvasHeight;
    public String status;
    //bird
    // private Bitmap bird;
    private Bitmap bird[]=new Bitmap[2];
    private int birdX=50;
    private int birdY;
    public int birdSpeed;

    private SoundPlayer sound = new SoundPlayer(getContext());

    //Blue ball
    private Bitmap apple;
    private int blueX;
    private int blueY;
    public double blueSpeed=15;
  // public double blueSpeed=mobj.blue_speed;

    //private Paint bluepaint=new Paint();

    //black
    private int blackX;
    private int blackY;
    public double blackSpeed=20;
//    public double blackSpeed=mobj.black_speed;
    private Paint blackpaint=new Paint();

    //Background
    private Bitmap bgImage;
    // Score
    private Paint scorePaint=new Paint();
    public int score;
    //Level
    private Paint levelPaint=new Paint();
    public int level;
    //life
    private Bitmap life[]=new Bitmap[2];
    public int life_count;
    //status check
    private boolean touch_flg=false;


    //////////////////////////////////////////////obstacle//////////////////
    private Bitmap obstacle;
    private int obstX;
    private int obstY;
    public double obs_speed=15;
//    public double obs_speed=mobj.obstacle_speed;

    /////////////////////////////////////////////obstacle 2/////////////////////////////////
    private Bitmap obstacle2;
    private int obst2X;
    private int obst2Y;
    public double obs_speed2=15;
////////////////////////////////////////////chance ////////////////////////////////////////////////////////

//    private Bitmap chance;
//    private int chX;
//    private int chY;
//    public double ch_speed=20;






    /////////////////////////////////////////affected code//////////////////////////////////
    public GameView(Context context){
        super(context);
        bgImage=BitmapFactory.decodeResource(getResources(),R.drawable.bgnew3);
        bird[0]= BitmapFactory.decodeResource(getResources(),R.drawable.bird1);
        bird[1]= BitmapFactory.decodeResource(getResources(),R.drawable.bird2);
        ////////////////////////////////////////////////////////////////////////////////////
        apple=BitmapFactory.decodeResource(getResources(),R.drawable.apple);
        obstacle=BitmapFactory.decodeResource(getResources(),R.drawable.obstacle);
        obstacle2=BitmapFactory.decodeResource(getResources(),R.drawable.snake2);

       // chance=BitmapFactory.decodeResource(getResources(),R.drawable.chance);
      /////////////////////////////////////////////////////////////////////////////////////
//        bluepaint.setColor(Color.BLUE);
//        bluepaint.setAntiAlias(false);

        blackpaint.setColor(Color.BLACK);
        blackpaint.setAntiAlias(false);


        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(40);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setFontFeatureSettings("@font/bangers");   //this is the point
        scorePaint.setAntiAlias(true);


        levelPaint.setColor(Color.DKGRAY);
        levelPaint.setTextSize(40);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.heart);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_g);
        //initial position of bird
        birdY=500;
        score=0;
        life_count=3;
        level=1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();
        canvas.drawBitmap(bgImage,0,0,null);


        //     canvas.drawBitmap(bird,0,0,null);
        int minBirdY=bird[0].getHeight();
        int maxBirdY=canvasHeight-bird[0].getHeight()*3;

        birdY+=birdSpeed;
        if (birdY<minBirdY) birdY=minBirdY;
        if (birdY>maxBirdY) birdY=maxBirdY;
        birdSpeed+=2;

        if(touch_flg){
            //flap wings
            sound.playWingSound();
            canvas.drawBitmap(bird[1],birdX,birdY,null);
            touch_flg=false;
        }else {
            canvas.drawBitmap(bird[0],birdX,birdY,null);
        }

        //Blue
        blueX-=blueSpeed;
        if (hitCheck(blueX,blueY)){
            sound.playGainPointSound();
            score+=10;
            blueX=-100;

        }

        if(blueX<0){
            blueX=canvasWidth+20;
            blueY=(int)Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;
        }
        canvas.drawBitmap(apple,blueX,blueY,null);
////////////////////////////////////////end of blue/////////////////////////////////

        //black balls
        blackX-=blackSpeed;
        if (hitCheck(blackX,blackY)){
            sound.playDieSound();
            blackX=-100;
            life_count--;

            if(life_count==0){
                sound.playGameOverSound();
                //status="gameover";
               Toast.makeText(getContext(),"GAME OVER",Toast.LENGTH_LONG).show();
            }
        }

        if (blackX<0){
            blackX=canvasWidth+200;
            blackY=(int)Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;
        }
        canvas.drawCircle(blackX,blackY,20,blackpaint);

////////////////////////////////////////////////////////////////////////////////////////////////
       if(level>1){
           obstX-=obs_speed;
           if(hitCheck(obstX,obstY)){
               sound.playLoosePointSound();
               if(score==0)
                   score=0;
               else
                   score-=10;

               obstX=-100;
           }
           if(obstX<0){
               obstX=canvasWidth+200;
               obstY=(int)Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;
           }
           canvas.drawBitmap(obstacle,obstX,obstY,null);
       }
//////////////////////////////////////////////////////////////////////////////////////////////
  if(level>2){
      obst2X-=obs_speed2;
      if(hitCheck(obst2X,obst2Y)){
          sound.playLoosePointSound();
          if(score==0)
              score=0;
          else
              score-=10;

          obst2X=-100;
      }
      if(obst2X<0){
          obst2X=canvasWidth+200;
          obst2Y=(int)Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;
      }
      canvas.drawBitmap(obstacle2,obst2X,obst2Y,null);
  }

  //////////////////////////////////////////////getting chance ///////////////////////////////////////////////////////////

//        if(level>0){
//    boolean ch_bool=true;
//
//    if (score%60==0&&score!=0&&ch_bool){
//
//        chX-=ch_speed;
//        if(hitCheck(chX,chY)){
//            sound.playLoosePointSound();
//            if(life_count==3)
//                life_count=3;
//            else life_count++;
//            chX=-100;
//        }
//        if(chX<0){
//            chX=canvasWidth+200;
//            chY=(int)Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;
//        }
//        canvas.drawBitmap(chance,chX,chY,null);
//        score=score+10;
//        ch_bool=false;
//    }
//}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
        boolean st=true;
        while (score%200==0&&score!=0&&st){
            level=level+1;
            st=false;
            score+=10;
            blueSpeed=blueSpeed+ 0.13*blueSpeed;
            blackSpeed=blackSpeed+0.15*blackSpeed;
            obs_speed=obs_speed+0.15*obs_speed;
            obs_speed2=obs_speed2+0.1*obs_speed2;
            break;
        }

//        if(score%100==0&&score!=0){
//           boolean st=true;
//           while (st){
//               level=level+1;
//               st=false;
//           }
//        }



        //Score
        canvas.drawText("Score : "+score,20,60,scorePaint);

        //level
        canvas.drawText("Level : "+level,canvasWidth /2,60,levelPaint);
        //life
        for (int i=0; i<3; i++){
            int x=(int)(800+life[0].getWidth()*1.5*i);
            int y=30;
            if (i<life_count){
                canvas.drawBitmap(life[0],x,y,null);
            }else {
                canvas.drawBitmap(life[1],x,y,null);
            }
        }

    }



    public boolean hitCheck(int x,int y){
        if (birdX<x &&x<(birdX+bird[0].getWidth())&&
                birdY<y &&y<(birdY+bird[0].getWidth())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touch_flg=true;
            birdSpeed=-20;
        }
        return true;
    }
//   public boolean status(){
//        if(status!="gameover"){
//            return false;
//        }
//        return true;
//   }
}



