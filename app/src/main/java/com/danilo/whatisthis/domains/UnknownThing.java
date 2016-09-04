package com.danilo.whatisthis.domains;

import java.util.List;

/**
 * Created by danilo on 03/09/2016.
 */
public class UnknownThing {

    private Long id;
    private String description;
    private List<String> pics;
    private List<Question> questions;
    public enum STATUS {PENDING, DEMYSTIFIED,IN_REVIEW};
    private User curious;
    private STATUS status;

    public UnknownThing(Long id, String description) {
        this.id = id;
        this.description = description;
        status = STATUS.PENDING;
    }

    public UnknownThing(Long id, String description, List<String> pics, List<Question> questions, User curious) {
        this.id = id;
        this.description = description;
        this.pics = pics;
        this.questions = questions;
        this.curious = curious;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public User getCurious() {
        return curious;
    }

    public void setCurious(User curious) {
        this.curious = curious;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
