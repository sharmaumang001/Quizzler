package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button True_button,False_button;
    TextView Score_TextView;
    ProgressBar mProgressBar;
    TextView  Questions;
    int QuestionID;
    int Index;
    int score;


    TrueFalse QuestionBank[] = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2,true),
            new TrueFalse(R.string.question_3,true),
            new TrueFalse(R.string.question_4,false),
            new TrueFalse(R.string.question_5,true),
            new TrueFalse(R.string.question_6,true),
            new TrueFalse(R.string.question_7,true),
            new TrueFalse(R.string.question_8,true),
            new TrueFalse(R.string.question_9,true),
            new TrueFalse(R.string.question_10,true),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            score= savedInstanceState.getInt("Score_key");
            Index= savedInstanceState.getInt("Index_key");

        }
        else{
            score=0;
        }

        True_button = findViewById(R.id.TRUE);
        False_button = findViewById(R.id.FALSE);
        Questions = findViewById(R.id.questions);
        Score_TextView = findViewById(R.id.Score);
        mProgressBar = findViewById(R.id.progressBar);

        Score_TextView.setText("SCORE" +score+"/" +QuestionBank.length);



        QuestionID = QuestionBank[Index].getQuestionId();
        Questions.setText(QuestionID);


        True_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_answer(true);
                update_Question();
            }
        });


        False_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_answer(false);
                update_Question();
            }
        });

    }

    private void update_Question() {
        Index = (Index +1) % QuestionBank.length;

        if(Index == 0){
            AlertDialog.Builder END_alert = new AlertDialog.Builder(this);
            END_alert.setTitle("GAME OVER");
            END_alert.setCancelable(false);
            END_alert.setMessage("YOU SCORED  " +score+ "  POINTS!");
            END_alert.setPositiveButton("CLOSE APPLICATION", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                      finish();
                }
            });

            END_alert.show();
        }

        QuestionID = QuestionBank[Index].getQuestionId();
        Questions.setText(QuestionID);
        Score_TextView.setText("SCORE" +score+"/" +QuestionBank.length);
    }

    private void check_answer(boolean userSelection) {

        boolean correct = QuestionBank[Index].isAnswer();

        if(userSelection == correct){
            Toast.makeText(getApplicationContext(),"you got it!!",Toast.LENGTH_SHORT).show();
            score = score + 1;
        }
        else{
            Toast.makeText(getApplicationContext(),"WRONG!!",Toast.LENGTH_SHORT).show();
        }

        mProgressBar.incrementProgressBy(10);
    }
    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);

        outstate.putInt("Score_key",score);
        outstate.putInt("Index_key",Index);
    }


}
