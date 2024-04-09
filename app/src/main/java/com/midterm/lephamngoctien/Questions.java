package com.midterm.lephamngoctien;

public class Questions {

    public String mQuestions[] = {
            "Câu 1 đúng nè",
            "Câu 2 sai nè",
            "Câu 3 đúng nè",
            "Câu 4 sai nè",
            "Câu 5 sai nè",
            "Câu 6 sai nè",
            "Câu 7 đúng nè",
            "Câu 8 đúng nè",
            "Câu 9 sai nè",
            "Câu 10 đúng nè"
    };

    public String mAnswers[] = {
            "true",
            "false",
            "true",
            "false",
            "false",
            "false",
            "true",
            "true",
            "false",
            "true"
    };

    public String getQuestion(int number) {
        return mQuestions[number];
    }

    public String getAnswer(int number) {
        return mAnswers[number];
    }
}
