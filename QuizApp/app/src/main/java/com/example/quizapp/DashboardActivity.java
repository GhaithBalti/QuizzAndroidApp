package com.example.quizapp;

import static com.example.quizapp.MainActivity.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int timervalue = 20;
    ProgressBar progressBar;
    List<ModelClass> AllQuestionsList;
    ModelClass modelClass;
    int index = 0;
    TextView card_question, optiona, optionb, optionc, optiond;
    CardView cardOA, cardOB, cardOC, cardOD;
    int correctCount = 0;
    int wrongCount = 0;
    LinearLayout nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        AllQuestionsList = list;
        Collections.shuffle(AllQuestionsList);
        modelClass = list.get(index);

        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));


        nextBtn.setClickable(false);

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                timervalue = timervalue - 1;
                progressBar.setProgress(timervalue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this, R.style.Dialog);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.btn_tryagain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                dialog.show();

            }
        }.start();
    }

    private void setAllData() {

        card_question.setText(modelClass.getQuestion());
        optiona.setText(modelClass.getoA());
        optionb.setText(modelClass.getoB());
        optionc.setText(modelClass.getoC());
        optiond.setText(modelClass.getoD());

    }

    private void Hooks() {
        progressBar = findViewById(R.id.progressBar2);
        card_question = findViewById(R.id.card_question);
        optiona = findViewById(R.id.card_option);
        optionb = findViewById(R.id.card_optionB);
        optionc = findViewById(R.id.card_optionC);
        optiond = findViewById(R.id.card_optionD);
        cardOA = findViewById(R.id.cardA);
        cardOB = findViewById(R.id.cardB);
        cardOC = findViewById(R.id.cardC);
        cardOD = findViewById(R.id.cardD);
        nextBtn = findViewById(R.id.nextBtn);

    }

    public void Correct(CardView cardView) {


        cardView.setBackgroundColor(getResources().getColor(R.color.green));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                index++;
                modelClass = list.get(index);
                setAllData();
            }
        });

    }

    public void Wrong(CardView cardOA) {

        cardOA.setCardBackgroundColor(getResources().getColor(R.color.red));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongCount++;
                if (index < list.size() - 1) {
                    index++;
                    modelClass = list.get(index);
                    setAllData();
                    resetColor();
                } else {
                    GameWon();
                }
            }
        });


    }

    private void GameWon() {
        Intent intent = new Intent(DashboardActivity.this, WonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);

    }

    public void enableButton() {
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);

    }

    public void disableButton() {
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);

    }

    public void resetColor() {
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));

    }

    public void OptionAClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelClass.getoA().equals(modelClass.ans)) {
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index<list.size()-1)
            {
                Correct(cardOA);
            } else{
                GameWon();
            }
        }
        else {
            Wrong(cardOA);
        }
    }


    public void OptionBClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelClass.getoB().equals(modelClass.ans)) {
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index<list.size()-1)
            {
                Correct(cardOB);
            } else{
                GameWon();
            }
        }
        else {
            Wrong(cardOB);
        }
    }

    public void OptionCClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelClass.getoC().equals(modelClass.ans)) {
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index<list.size()-1)
            {
                Correct(cardOC);
            } else{
                GameWon();
            }
        }
        else {
            Wrong(cardOC);
        }
    }

    public void OptionDClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelClass.getoD().equals(modelClass.ans)) {
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index<list.size()-1)
            {
                Correct(cardOD);
            } else{
                GameWon();
            }
        }
        else {
            Wrong(cardOD);
        }
    }
}
