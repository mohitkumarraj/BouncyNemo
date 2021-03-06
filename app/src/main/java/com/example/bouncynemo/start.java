package com.example.bouncynemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class start extends AppCompatActivity {
  Intent intent;
  RadioGroup rg;
  RadioButton r1,r2,r3;
  Button st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_start);
//        intent = new Intent(getApplicationContext(), MainActivity.class);
//
//         rg=findViewById(R.id.rdgroup);
//         r1=findViewById(R.id.easy);
//         r2=findViewById(R.id.medium);
//         r3=findViewById(R.id.hard);
//         st=findViewById(R.id.startgame);
//         st.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//               int selected_id=rg.getCheckedRadioButtonId();
//               if(selected_id==-1){
//                   Toast.makeText(getApplicationContext(), "Please Select Level", Toast.LENGTH_LONG).show();
//               }
//
//               if(selected_id==R.id.easy){
//                   intent.putExtra("bs",20);
//                   intent.putExtra("bls",20);
//                   intent.putExtra("obs",20);
//                   startActivity(intent);
//               }else if (selected_id==R.id.medium){
//                   intent.putExtra("bs",35);
//                   intent.putExtra("bls",35);
//                   intent.putExtra("obs",35);
//                   startActivity(intent);
//               }else if (selected_id==R.id.hard){
//                   intent.putExtra("bs",50);
//                   intent.putExtra("bls",50);
//                   intent.putExtra("obs",50);
//                   startActivity(intent);
//               }
//             }
//         });

    }





    public void startGame(View view){
      startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }



}