package com.sen.onlineexam.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.sen.onlineexam.dao.ExamItemDAO;
import com.sen.onlineexam.pojo.Exam;
import com.sen.onlineexam.pojo.ExamItem;
import com.sen.onlineexam.pojo.Problem;
import com.sen.onlineexam.service.ExamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamItemServiceImpl implements ExamItemService {
    @Autowired
    private ExamItemDAO examItemDAO;

    @Override
    public boolean addExamItem(int examId, List<JSONArray> list) {
        List<ExamItem> examItems = new ArrayList<>();
        for (JSONArray li : list) {
            ExamItem examItem = new ExamItem(0, 0, null, examId);
            Problem problem = new Problem();
            problem.setProblemId((Integer) li.get(0));
            examItem.setProblem(problem);
            examItem.setItemScore((Integer) li.get(1));
            examItems.add(examItem);
        }
        return examItemDAO.insert(examItems) != 0;
    }

    @Override
    public boolean deleteExamItem(List<Integer> itemIds) {
        return examItemDAO.deleteExamItem(itemIds) != 0;
    }

    @Override
    public boolean updateExamItem(ExamItem examItem) {
        return examItemDAO.updateByPrimaryKeySelective(examItem) != 0;
    }

}
