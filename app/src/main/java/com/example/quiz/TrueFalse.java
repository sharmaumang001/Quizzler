package com.example.quiz;

public class TrueFalse {
    private int QuestionId;
    private boolean Answer;

    public TrueFalse (int questionResourceId,boolean answer){
       QuestionId = questionResourceId;
       Answer = answer;
    }

    public int getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(int questionId) {
        QuestionId = questionId;
    }

    public boolean isAnswer() {
        return Answer;
    }

    public void setAnswer(boolean answer) {
        Answer = answer;
    }
}
