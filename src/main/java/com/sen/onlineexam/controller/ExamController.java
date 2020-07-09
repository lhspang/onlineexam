package com.sen.onlineexam.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.pojo.*;
import com.sen.onlineexam.service.impl.ExamItemServiceImpl;
import com.sen.onlineexam.service.ExamService;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sen
 * @date 2020/6/27 0:51
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("exam")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private ExamItemServiceImpl examItemService;

    /**
     * 添加考试
     * @param exam
     * @param subjectId
     * @param userId
     * @param examSdateStr
     * @param examEdateStr
     * @param itemPro
     * @param itemScore
     * @return
     */
    @PostMapping("")
    @RequiresRoles(value = "老师")
    public ResponseResult addExam(Exam exam, Integer subjectId, Integer userId, String examSdateStr, String examEdateStr, String itemPro, String itemScore) {
        User user = new User();
        Subject subject = new Subject();
        user.setUserId(userId);
        subject.setSubjectId(subjectId);
        Date examSdate = formatDate(examSdateStr);
        Date examEdate = formatDate(examEdateStr);
        exam.setExamSdate(examSdate);
        exam.setExamEdate(examEdate);
        exam.setUser(user);
        exam.setSubject(subject);
        exam.setExamCdate(new Date());
        String[] itemProList = itemPro.split(",");
        String[] itemScoreList = itemScore.split(",");
        if (examService.addExam(exam, itemProList,itemScoreList)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.SERVER_ERROR);
    }

    /**
     * 删除考试
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @RequiresRoles(value = "老师")
    public ResponseResult deleteExam(@PathVariable int id) {
        if (examService.deleteExam(id)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    /**
     * 修改考试
     * @param id
     * @param exam
     * @return
     */
    @PutMapping("{id}")
    @RequiresRoles(value = "老师")
    public ResponseResult updateExam(@PathVariable int id, Exam exam) {
        exam.setExamId(id);
        if (examService.updateExam(exam)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    /**
     * 查询一个考试（老师端）
     * @param id
     * @return
     */
    @GetMapping("{id}/teacher")
    @RequiresRoles(value = "老师")
    public ResponseResult getOneExamByTeacher(@PathVariable int id) {
        Map<String, Object> map = examService.getOneExam(id);
        if (map != null && map.size() != 0) {
            return ResponseResult.success(map);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    /**
     * 查询一个考试（学生端，去除试题正确答案）
     * @param id
     * @return
     */
    @GetMapping("{id}/student")
    @RequiresRoles(value = "学生")
    public ResponseResult getOneExamByStudent(@PathVariable int id) {
        Map<String, Object> map = examService.getOneExam(id);
        if (map != null && map.size() != 0) {
            for (ExamItem item : (List<ExamItem>) map.get("item")) {
                Problem problem = item.getProblem();
                problem.setProblemRight("");
                item.setProblem(problem);
            }
            return ResponseResult.success(map);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    /**
     * 分页查询所有考试(可按科目)
     * @param subjectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("all")
    @RequiresRoles(value = {"管理员", "老师", "学生"}, logical = Logical.OR)
    public ResponseResult getAllExam(Integer subjectId, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageInfo<Exam> list = examService.getAllExam(subjectId, pageNum, pageSize);
        if (list != null) {
            return ResponseResult.success(list);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    /**
     * 添加考试题目
     * @param examId
     * @param examItem
     * @return
     */
    @PostMapping("item")
    @RequiresRoles(value = "老师")
    public ResponseResult addExamItem(int examId, String examItem) {
        List<JSONArray> list = JSONObject.parseObject(examItem, List.class);
        if (examItemService.addExamItem(examId, list)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.SERVER_ERROR);
    }

    /**
     * 删除考试题目
     * @param itemIds
     * @return
     */
    @DeleteMapping("item")
    @RequiresRoles(value = "老师")
    public ResponseResult deleteExamItem(String itemIds) {
        List<Integer> list = JSONObject.parseArray(itemIds, Integer.class);

        if (examItemService.deleteExamItem(list)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    /**
     * 修改考试题目分值
     * @param id
     * @param itemScore
     * @return
     */
    @PutMapping("item/{id}")
    @RequiresRoles(value = "老师")
    public ResponseResult updateExamItem(@PathVariable int id, int itemScore) {
        ExamItem examItem = new ExamItem(id, itemScore, null, null);
        if (examItemService.updateExamItem(examItem)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    public Date formatDate(String date) {
        Date date1 = null;
        DateFormat format;
        try {
            //是空格+UTC
            date = date.replace("Z", " UTC");
            //格式化的表达式
            format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }
}
