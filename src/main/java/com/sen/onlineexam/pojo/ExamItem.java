package com.sen.onlineexam.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class ExamItem implements Serializable {
    private Integer itemId;

    private Integer itemScore;

    private Problem problem;

    private Integer examId;

    private static final long serialVersionUID = 1L;

    public ExamItem() {
    }

    public ExamItem(Integer itemId, Integer itemScore, Problem problem, Integer examId) {
        this.itemId = itemId;
        this.itemScore = itemScore;
        this.problem = problem;
        this.examId = examId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemScore() {
        return itemScore;
    }

    public void setItemScore(Integer itemScore) {
        this.itemScore = itemScore;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    @Override
    public String toString() {
        return "ExamItem{" +
                "itemId=" + itemId +
                ", itemScore=" + itemScore +
                ", problem=" + problem +
                ", examId=" + examId +
                '}';
    }
}