package com.sen.onlineexam.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class Problem implements Serializable {
    private Integer problemId;

    private String problemDesc;

    private String problemRight;

    private Integer problemCount;

    private String problemOption1;

    private String problemOption2;

    private String problemOption3;

    private String problemOption4;

    private Subject subject;

    private Protype protype;

    private static final long serialVersionUID = 1L;

    public Problem() {
    }

    public Problem(Integer problemId, String problemDesc, String problemRight, Integer problemCount, String problemOption1, String problemOption2, String problemOption3, String problemOption4, Subject subject, Protype protype) {
        this.problemId = problemId;
        this.problemDesc = problemDesc;
        this.problemRight = problemRight;
        this.problemCount = problemCount;
        this.problemOption1 = problemOption1;
        this.problemOption2 = problemOption2;
        this.problemOption3 = problemOption3;
        this.problemOption4 = problemOption4;
        this.subject = subject;
        this.protype = protype;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getProblemRight() {
        return problemRight;
    }

    public void setProblemRight(String problemRight) {
        this.problemRight = problemRight;
    }

    public Integer getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(Integer problemCount) {
        this.problemCount = problemCount;
    }

    public String getProblemOption1() {
        return problemOption1;
    }

    public void setProblemOption1(String problemOption1) {
        this.problemOption1 = problemOption1;
    }

    public String getProblemOption2() {
        return problemOption2;
    }

    public void setProblemOption2(String problemOption2) {
        this.problemOption2 = problemOption2;
    }

    public String getProblemOption3() {
        return problemOption3;
    }

    public void setProblemOption3(String problemOption3) {
        this.problemOption3 = problemOption3;
    }

    public String getProblemOption4() {
        return problemOption4;
    }

    public void setProblemOption4(String problemOption4) {
        this.problemOption4 = problemOption4;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Protype getProtype() {
        return protype;
    }

    public void setProtype(Protype protype) {
        this.protype = protype;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problemId=" + problemId +
                ", problemDesc='" + problemDesc + '\'' +
                ", problemRight='" + problemRight + '\'' +
                ", problemCount=" + problemCount +
                ", problemOption1='" + problemOption1 + '\'' +
                ", problemOption2='" + problemOption2 + '\'' +
                ", problemOption3='" + problemOption3 + '\'' +
                ", problemOption4='" + problemOption4 + '\'' +
                ", subject=" + subject +
                ", protype=" + protype +
                '}';
    }
}