package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.ExamItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ExamItemDAO继承基类
 */
@Repository
public interface ExamItemDAO extends MyBatisBaseDao<ExamItem, Integer> {

    int insert(List<ExamItem> list);

    int deleteByExamId(int examId);

    int deleteExamItem(List<Integer> list);

    List<ExamItem> selectByExamId(int examId);

    ExamItem selectByExamIdAndProblemId(Map map);
}