package com.sen.onlineexam.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.dao.ExamDAO;
import com.sen.onlineexam.dao.ExamItemDAO;
import com.sen.onlineexam.dao.ProblemDAO;
import com.sen.onlineexam.dao.ScoreDAO;
import com.sen.onlineexam.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface ScoreService {
    boolean addScore(Integer userId, Integer examId, Map optionMap) ;

    boolean deleteScore(Integer id);

    List<Score> getScoreByUserId(Integer userId);

    PageInfo<Score> getAllScore(Integer subjectId, Integer pageNum, Integer pageSize) ;
}
