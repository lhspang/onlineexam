package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProblemDAO继承基类
 */
@Repository
public interface ProblemDAO extends MyBatisBaseDao<Problem, Integer> {

    List<Problem> selectAllProblemOrName(String problemName, Integer subjectId, Integer protypeId);
}