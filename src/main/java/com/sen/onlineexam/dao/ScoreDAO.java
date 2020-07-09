package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.Score;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

/**
 * ScoreDAO继承基类
 */
@Repository
public interface ScoreDAO extends MyBatisBaseDao<Score, Integer> {
    List<Score> selectScoreByUserId(Integer userId);

    List<Score> selectAll(Integer subjectId);
}