package com.example.bouncynemo;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPlayer {
private AudioAttributes audioAttributes;
private static SoundPool soundPool;
public static int wings,gainpoint,loosepoint,die,gameover;

 public SoundPlayer(Context context){
     if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
       audioAttributes =new AudioAttributes.Builder()
       .setUsage(AudioAttributes.USAGE_GAME)
       .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
       .build();
       soundPool=new SoundPool.Builder()
               .setAudioAttributes(audioAttributes)
               .setMaxStreams(2)
               .build();
     }else{
         soundPool=new SoundPool(2,AudioManager.STREAM_MUSIC,0);
     }
    wings=soundPool.load(context,R.raw.sfx_wing,1);
    gainpoint=soundPool.load(context,R.raw.sfx_point,1);
    loosepoint=soundPool.load(context,R.raw.sfx_hit,1);
    die=soundPool.load(context,R.raw.sfx_die,1);
    gameover=soundPool.load(context,R.raw.game_over,1);
 }

public void playWingSound(){
soundPool.play(wings,1.0f,1.0f,1,0,1.0f);
}

public void playGainPointSound(){
        soundPool.play(gainpoint,1.0f,1.0f,1,0,1.0f);
 }


 public void playLoosePointSound(){
        soundPool.play(loosepoint,1.0f,1.0f,1,0,1.0f);
 }

 public void playDieSound(){
        soundPool.play(die,1.0f,1.0f,1,0,1.0f);
 }

 public void playGameOverSound(){
        soundPool.play(gameover,1.0f,1.0f,1,0,1.0f);
 }

}
