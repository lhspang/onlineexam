package com.sen.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.dao.ExamDAO;
import com.sen.onlineexam.dao.ExamItemDAO;
import com.sen.onlineexam.pojo.Exam;
import com.sen.onlineexam.pojo.ExamItem;
import com.sen.onlineexam.pojo.Problem;
import com.sen.onlineexam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDAO examDAO;
    @Autowired
    private ExamItemDAO examItemDAO;

    @Override
    @Transactional
    public boolean addExam(Exam exam, String[] itemProList, String[] itemScoreList) {
        try {
            examDAO.insert(exam);
            List<ExamItem> examItems = new ArrayList();
            for (int i = 0; i < itemProList.length; i++) {
                ExamItem examItem = new ExamItem(0, 0, null, exam.getExamId());
                Problem problem = new Problem();
                problem.setProblemId(Integer.parseInt(itemProList[i]));
                examItem.setProblem(problem);
                examItem.setItemScore(Integer.parseInt(itemScoreList[i]));
                examItems.add(examItem);
            }
            examItemDAO.insert(examItems);
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public boolean deleteExam(int examId) {
        try {
            examItemDAO.deleteByExamId(examId);
            examDAO.deleteByPrimaryKey(examId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateExam(Exam exam) {
        return examDAO.updateByPrimaryKeySelective(exam) != 0;
    }

    @Override
    public Map<String, Object> getOneExam(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("exam", examDAO.selectByPrimaryKey(id));
        map.put("item", examItemDAO.selectByExamId(id));
        return map;
    }

    @Override
    public PageInfo<Exam> getAllExam(Integer subjectId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(examDAO.selectAllExam(subjectId));
    }
}
