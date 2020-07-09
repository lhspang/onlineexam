package com.sen.onlineexam.controller;

import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.pojo.Score;
import com.sen.onlineexam.service.ScoreService;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    //学生提交考试，并自动判卷
    @PostMapping("")
    @RequiresRoles(value="学生")
    public ResponseResult addScore(Integer userId, Integer examId, String panAndDanOptions,String duoOptions){
        Map<String,Object> map = new HashMap<>();
        map.put("panAndDanOptions",panAndDanOptions.split("&"));
        map.put("duoOptions",duoOptions);
        if(scoreService.addScore(userId, examId,map)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.SERVER_ERROR);
    }

    //删除成绩信息
    @DeleteMapping("{id}")
    @RequiresRoles(value="老师")
    public ResponseResult deleteScore(@PathVariable int id){
        if(scoreService.deleteScore(id)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.SERVER_ERROR);
    }

    //查询一个人的成绩信息
    @GetMapping("{userId}/student")
    @RequiresRoles(value={"学生","老师","管理员"},logical= Logical.OR)
    public ResponseResult getOneScore(@PathVariable int userId){
        List<Score> list = scoreService.getScoreByUserId(userId);
        if(list!=null && list.size()>0){
            return ResponseResult.success(list);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //查询所有学生成绩（可按科目分类查询）
    @GetMapping("all")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult getAllScore(Integer subjectId,Integer pageNum, Integer pageSize){
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageInfo<Score> pageInfo = scoreService.getAllScore(subjectId, pageNum, pageSize);
        if ((pageInfo!=null&&pageInfo.getSize()!=0)){
            return ResponseResult.success(pageInfo);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }
}
