package com.carloslovera.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button startButton;
    int correctAns;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;
    int score = 0;
    TextView result;
    int numQuestions= 0;
    TextView points;
    TextView timer;
    TextView sum;
    Button playAgainButton;
    RelativeLayout gameLayout;
    public void generatedQs(){
        Random rand = new Random();
        int digit1 = rand.nextInt(100);
        int digit2 = rand.nextInt(100);


        sum.setText(Integer.toString(digit1) + " + " + Integer.toString(digit2));
        answers.clear();
        correctAns = rand.nextInt(4);
        int incorrectAns;

        for (int i = 0;i<4;i++){
            if(i == correctAns){
                answers.add(digit1 + digit2);
            } else {
                incorrectAns = rand.nextInt(199);
                if(digit1 + digit2 == incorrectAns){
                    incorrectAns += 1;
                }
                answers.add(incorrectAns);
            }
        } // end of [for]

        ans1.setText(Integer.toString(answers.get(0)));
        ans2.setText(Integer.toString(answers.get(1)));
        ans3.setText(Integer.toString(answers.get(2)));
        ans4.setText(Integer.toString(answers.get(3)));
    }

    // Start Button setup
    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
        gameLayout.setVisibility(view.VISIBLE);
        playAgain(playAgainButton);

    }


    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(correctAns))) {
            result.setText("Correct!");
            score++;
        } else {
            result.setText("Wrong!");
        }
        numQuestions++;
        points.setText(Integer.toString(score) +" / "+ Integer.toString(numQuestions));
        generatedQs();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        ans1 = (Button) findViewById(R.id.button);
        ans2 = (Button) findViewById(R.id.button1);
        ans3 = (Button) findViewById(R.id.button2);
        ans4 = (Button) findViewById(R.id.button3);
        result = (TextView) findViewById(R.id.scoreText);
        points = (TextView) findViewById(R.id.points);
        sum = (TextView) findViewById(R.id.sum);
        timer = (TextView) findViewById(R.id.timer);
        gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        generatedQs();



    }
    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        score = 0;
        numQuestions = 0;
        timer.setText("30s");
        points.setText(Integer.toString(score) +" / "+ Integer.toString(numQuestions));
        result.setText("");
        startTimer(view);

    }

    public void startTimer(View view){
        new CountDownTimer(30100, 1000) {


            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timer.setText("0s");
                result.setText("Your score is: " + Integer.toString(score) + " / " + Integer.toString(numQuestions));
            }
        }.start();
    }// end of [onCreate]

}

