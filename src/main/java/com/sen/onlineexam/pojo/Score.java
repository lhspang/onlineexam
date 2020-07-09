package com.sen.onlineexam.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class Score implements Serializable {
    private Integer scoreId;

    private Integer totalScore;

    private User user;

    private Exam exam;

    private Subject subject;

    private static final long serialVersionUID = 1L;

    public Score() {
    }

    public Score(Integer scoreId, Integer totalScore, User user, Exam exam, Subject subject) {
        this.scoreId = scoreId;
        this.totalScore = totalScore;
        this.user = user;
        this.exam = exam;
        this.subject = subject;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", totalScore=" + totalScore +
                ", user=" + user +
                ", exam=" + exam +
                ", subject=" + subject +
                '}';
    }
}