package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.Exam;
import com.sen.onlineexam.pojo.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ExamDAO继承基类
 */
@Repository
public interface ExamDAO extends MyBatisBaseDao<Exam, Integer> {

    List<Exam> selectAllExam(Integer subjectId);
}