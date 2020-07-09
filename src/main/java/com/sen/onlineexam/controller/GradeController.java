package com.sen.onlineexam.controller;

import com.sen.onlineexam.pojo.Grade;
import com.sen.onlineexam.service.GradeService;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    //添加等级
    @PostMapping("")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult addProtype(Grade grade) {
        if (gradeService.addGrade(grade)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //删除等级
    @DeleteMapping("{id}")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult deleteProtype(@PathVariable int id) {
        if (gradeService.deleteGrade(id)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //修改等级
    @PutMapping("{id}")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult updateProtype(@PathVariable int id, Grade grade) {
        grade.setGradeId(id);
        if (gradeService.updateGrade(grade)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //查询一个等级
    @GetMapping("{id}")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult getOneProtype(@PathVariable int id) {
        Grade grade = gradeService.getOneGrade(id);
        if (grade != null) {
            return ResponseResult.success(grade);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //查询所有等级
    @GetMapping("all")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult getAllProtype() {
        List<Grade> list = gradeService.getAllGrade();
        if (list != null) {
            return ResponseResult.success(list);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }
}
