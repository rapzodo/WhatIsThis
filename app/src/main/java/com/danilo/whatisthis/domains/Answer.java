package com.danilo.whatisthis.domains;

import java.util.List;

/**
 * Created by danilo on 03/09/2016.
 */
public class Answer {

    private Long id;
    private User replier;
    private String answerContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReplier() {
        return replier;
    }

    public void setReplier(User replier) {
        this.replier = replier;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}
