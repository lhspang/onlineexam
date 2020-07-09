package com.sen.onlineexam.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.dao.ExamDAO;
import com.sen.onlineexam.dao.ExamItemDAO;
import com.sen.onlineexam.dao.ProblemDAO;
import com.sen.onlineexam.dao.ScoreDAO;
import com.sen.onlineexam.pojo.*;
import com.sen.onlineexam.service.ExamService;
import com.sen.onlineexam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDAO scoreDAO;
    @Autowired
    private ProblemDAO problemDAO;
    @Autowired
    private ExamItemDAO examItemDAO;
    @Autowired
    private ExamDAO examDAO;
    @Autowired
    private ExamService examService;

    //, List<JSONArray> list
    @Override
    public boolean addScore(Integer userId, Integer examId, Map optionMap) {
        Integer totalScore = 0;
        Map<String, Object> map = examService.getOneExam(examId);
        Exam exam = (Exam) map.get("exam");
        List<ExamItem> item = (List) map.get("item");
        //处理判断和单选题
        String[] panAndDanArr = (String[]) optionMap.get("panAndDanOptions");
        for (String pd : panAndDanArr) {
            String id = pd.substring(0, pd.indexOf("="));
            String option = pd.substring(pd.indexOf("=") + 1);
            for (ExamItem i : item) {
                if (i.getProblem().getProblemId().equals(Integer.parseInt(id)) && option.equals(i.getProblem().getProblemRight())) {
                    totalScore += i.getItemScore();
                }
            }
        }
        //处理多选题
        String duoOptions = (String) optionMap.get("duoOptions");
        JSONObject jsonObject = JSONObject.parseObject(duoOptions);
        for (String key : jsonObject.keySet()) {
            String value = jsonObject.getString(key);
            JSONArray jsonArray = JSONObject.parseArray(value);

            for (ExamItem ei : item) {
                if (ei.getProblem().getProblemId().equals(Integer.parseInt(key))) {
                    List<String> realOptionList = new ArrayList();
                    List<String> userOptionList = new ArrayList();
                    String[] realOpt = ei.getProblem().getProblemRight().split(",");
                    Collections.addAll(realOptionList, realOpt);

                    for (Object o : jsonArray) {
                        if (ei.getProblem().getProblemOption1().equals(o)) {
                            userOptionList.add("A");
                        } else if (ei.getProblem().getProblemOption2().equals(o)) {
                            userOptionList.add("B");
                        } else if (ei.getProblem().getProblemOption3().equals(o)) {
                            userOptionList.add("C");
                        } else if (ei.getProblem().getProblemOption4().equals(o)) {
                            userOptionList.add("D");
                        }
                    }
                    Collections.sort(realOptionList);
                    Collections.sort(userOptionList);
                    if (realOptionList.equals(userOptionList)) {
                        totalScore += ei.getItemScore();
                    }
                }
            }
        }

        User user = new User();
        user.setUserId(userId);
        Score score = new Score(0, totalScore, user, exam, new Subject(exam.getSubject().getSubjectId(), null));
        return scoreDAO.insert(score) != 0;
    }

    @Override
    public boolean deleteScore(Integer id) {
        return scoreDAO.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public List<Score> getScoreByUserId(Integer userId) {
        return scoreDAO.selectScoreByUserId(userId);
    }

    @Override
    public PageInfo<Score> getAllScore(Integer subjectId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(scoreDAO.selectAll(subjectId));
    }
}
