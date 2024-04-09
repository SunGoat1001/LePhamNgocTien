package com.midterm.lephamngoctien;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.midterm.lephamngoctien.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Questions mQuestions;
    int questionsLength;
    ArrayList<Item> questionsList;
    String[] listResult;
    int currentQuestion = 0;
    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mQuestions = new Questions();
        questionsLength = mQuestions.mQuestions.length;
        questionsList = new ArrayList<>();
        listResult = new String[10];

        for (int i = 0; i < 10; i++) {
            listResult[i] = "";
        }

        for (int i = 0; i < questionsLength; i++) {
            questionsList.add(new Item(mQuestions.getQuestion(i), mQuestions.getAnswer(i)));
        }

//        Collections.shuffle(questionsList);

        setQuestion(currentQuestion);

        binding.btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion < questionsLength - 1) {
                    currentQuestion++;
                    if (listResult[currentQuestion].isEmpty()) {
                        binding.main.setBackgroundColor(Color.TRANSPARENT);
                    } else if (listResult[currentQuestion].equals(questionsList.get(currentQuestion).getQuestion() + "      CORRECT")){
                        binding.main.setBackgroundColor(Color.GREEN);
                    } else {
                        binding.main.setBackgroundColor(Color.RED);
                    }
                    setQuestion(currentQuestion);
                }
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion > 0) {
                    currentQuestion--;
                    if (listResult[currentQuestion].isEmpty()) {
                        binding.main.setBackgroundColor(Color.TRANSPARENT);
                    } else if (listResult[currentQuestion].equals(questionsList.get(currentQuestion).getQuestion() + "      CORRECT")){
                        binding.main.setBackgroundColor(Color.GREEN);
                    } else {
                        binding.main.setBackgroundColor(Color.RED);
                    }
                    setQuestion(currentQuestion);
                }
            }
        });

        binding.btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listResult[currentQuestion].isEmpty()) {
                    if (checkQuestion(currentQuestion)) {
                        binding.main.setBackgroundColor(Color.GREEN);
                        listResult[currentQuestion] = questionsList.get(currentQuestion).getQuestion() + "      CORRECT";
                        score++;
                    } else {
                        binding.main.setBackgroundColor(Color.RED);
                        listResult[currentQuestion] = questionsList.get(currentQuestion).getQuestion() + "      WRONG";
                    }
                }
            }
        });

        binding.btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listResult[currentQuestion].isEmpty()) {
                    if (!checkQuestion(currentQuestion)) {
                        binding.main.setBackgroundColor(Color.GREEN);
                        listResult[currentQuestion] = questionsList.get(currentQuestion).getQuestion() + "      CORRECT";
                        score++;
                    } else {
                        binding.main.setBackgroundColor(Color.RED);
                        listResult[currentQuestion] = questionsList.get(currentQuestion).getQuestion() + "      WRONG";
                    }
                }
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("result", listResult);
                String scoreResult = "" + score;
                intent.putExtra("score", scoreResult);
                startActivity(intent);
            }
        });
    }

    private void setQuestion(int number) {
        binding.textView.setText(questionsList.get(number).getQuestion());
    }

    private boolean checkQuestion(int number) {
        String answer = questionsList.get(number).getAnswer();
        return answer.equals("true");
    }
}