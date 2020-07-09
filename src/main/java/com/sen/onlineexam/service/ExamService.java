package com.sen.onlineexam.service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.dao.ExamDAO;
import com.sen.onlineexam.dao.ExamItemDAO;
import com.sen.onlineexam.pojo.Exam;
import com.sen.onlineexam.pojo.ExamItem;
import com.sen.onlineexam.pojo.Problem;
import com.sen.onlineexam.pojo.Protype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface ExamService {
    boolean addExam(Exam exam, String[] itemProList, String[] itemScoreList) ;

    boolean deleteExam(int examId) ;

    boolean updateExam(Exam exam);

    Map<String, Object> getOneExam(int id) ;

    PageInfo<Exam> getAllExam(Integer subjectId, int pageNum, int pageSize) ;
}
