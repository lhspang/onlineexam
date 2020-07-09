package com.sen.onlineexam.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class Grade implements Serializable {
    private Integer gradeId;

    private String gradeName;

    private static final long serialVersionUID = 1L;

    public Grade() {
    }

    public Grade(Integer gradeId, String gradeName) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}