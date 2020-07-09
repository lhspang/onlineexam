package com.sen.onlineexam.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class Exam implements Serializable {
    private Integer examId;

    private String examName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examSdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examEdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examCdate;

    private Integer examLong;

    private Integer examTotalscore;

    private Integer examTotalnum;

    private Subject subject;

    private User user;

    private static final long serialVersionUID = 1L;

    public Exam() {
    }

    public Exam(Integer examId, String examName, Date examSdate, Date examEdate, Date examCdate, Integer examLong, Integer examTotalscore, Integer examTotalnum, Subject subject, User user) {
        this.examId = examId;
        this.examName = examName;
        this.examSdate = examSdate;
        this.examEdate = examEdate;
        this.examCdate = examCdate;
        this.examLong = examLong;
        this.examTotalscore = examTotalscore;
        this.examTotalnum = examTotalnum;
        this.subject = subject;
        this.user = user;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getExamSdate() {
        return examSdate;
    }

    public void setExamSdate(Date examSdate) {
        this.examSdate = examSdate;
    }

    public Date getExamEdate() {
        return examEdate;
    }

    public void setExamEdate(Date examEdate) {
        this.examEdate = examEdate;
    }

    public Date getExamCdate() {
        return examCdate;
    }

    public void setExamCdate(Date examCdate) {
        this.examCdate = examCdate;
    }

    public Integer getExamLong() {
        return examLong;
    }

    public void setExamLong(Integer examLong) {
        this.examLong = examLong;
    }

    public Integer getExamTotalscore() {
        return examTotalscore;
    }

    public void setExamTotalscore(Integer examTotalscore) {
        this.examTotalscore = examTotalscore;
    }

    public Integer getExamTotalnum() {
        return examTotalnum;
    }

    public void setExamTotalnum(Integer examTotalnum) {
        this.examTotalnum = examTotalnum;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", examName='" + examName + '\'' +
                ", examSdate=" + examSdate +
                ", examEdate=" + examEdate +
                ", examCdate=" + examCdate +
                ", examLong=" + examLong +
                ", examTotalscore=" + examTotalscore +
                ", examTotalnum=" + examTotalnum +
                ", subject=" + subject +
                ", user=" + user +
                '}';
    }
}