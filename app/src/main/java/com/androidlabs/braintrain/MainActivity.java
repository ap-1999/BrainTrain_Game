package com.androidlabs.braintrain;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button,button1,button2,button3,button4;
    TextView display,viewer,number,timer;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    CountDownTimer counter;
    int clocation,numberofq=0,correctanswer=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);
        button3 = (Button) findViewById(R.id.button4);
        button4 = (Button) findViewById(R.id.button5);
        display = (TextView) findViewById(R.id.textView3);
        viewer = (TextView) findViewById(R.id.textView4);
        number=(TextView)findViewById(R.id.textView2);
        timer=(TextView)findViewById(R.id.textView);

        generateQuestion();
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Integer.toString((int)millisUntilFinished/1000)+"S");
            }

            @Override
            public void onFinish() {
                   new AlertDialog.Builder(MainActivity.this)
                           .setTitle("Your Score is "+Integer.toString(correctanswer)+"/"+ Integer.toString(numberofq))
                            .setMessage("Time Over")
                           .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   finish();
                                 Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                                 startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                 getApplicationContext().startActivity(startIntent);


                               }
                           })
                           .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   finish();
                               }
                           })
                           .show();

                    }
            }   .start();
        }


    public void generateQuestion() {

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        display.setText(Integer.toString(a) + "+" + Integer.toString(b));
        answers.clear();
        clocation = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == clocation)
                answers.add(a + b);
            else {
                int wanswer = rand.nextInt(41);
                while (wanswer == (a + b)) {
                    wanswer = rand.nextInt(41);
                }
                answers.add(wanswer);
            }

        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }








    public void start(View view)
    {
        button.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        display.setVisibility(View.VISIBLE);
        viewer.setVisibility(View.VISIBLE);
        number.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);










    }
    public  void option(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(clocation)))
        {
                               viewer.setText("CORRECT");
            correctanswer=correctanswer+1;
        }
        else
            viewer.setText("INCORRECT");
        numberofq=numberofq+1;


        number.setText(Integer.toString(correctanswer)+"/"+ Integer.toString(numberofq));
        generateQuestion();
    }
}
