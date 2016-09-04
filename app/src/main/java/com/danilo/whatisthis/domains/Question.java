package com.danilo.whatisthis.domains;

import android.animation.TimeInterpolator;

import java.util.List;

/**
 * Created by danilo on 03/09/2016.
 */
public class Question {

    private Long id;
    private String content;
    private User questioner;
    private Answer answer;
    private Answer bestAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getQuestioner() {
        return questioner;
    }

    public void setQuestioner(User questioner) {
        this.questioner = questioner;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getBestAnswer() {
        return bestAnswer;
    }

    public void setBestAnswer(Answer bestAnswer) {
        this.bestAnswer = bestAnswer;
    }
}
