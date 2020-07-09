package com.sen.onlineexam.controller;

import com.sen.onlineexam.pojo.Subject;
import com.sen.onlineexam.service.SubjectService;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    //添加考试科目
    @PostMapping("")
    @RequiresRoles(value="管理员")
    public ResponseResult addProtype(Subject subject){
        if(subjectService.addSubject(subject)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //删除科目
    @DeleteMapping("{id}")
    @RequiresRoles(value="管理员")
    public ResponseResult deleteProtype(@PathVariable int id){
        if(subjectService.deleteSubject(id)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //修改科目
    @PutMapping("{id}")
    @RequiresRoles(value="管理员")
    public ResponseResult updateProtype(@PathVariable int id,Subject subject){
        subject.setSubjectId(id);
        if(subjectService.updateSubject(subject)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //查询一个科目
    @GetMapping("{id}")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult getOneProtype(@PathVariable int id){
        Subject subject = subjectService.getOneSubject(id);
        if(subject!= null){
            return ResponseResult.success(subject);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //查询所有科目
    @GetMapping("all")
    @RequiresRoles(value={"管理员","老师","学生"},logical= Logical.OR)
    public ResponseResult getAllProtype(){
        List<Subject> list = subjectService.getAllSubject();
        if(list!= null){
            return ResponseResult.success(list);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }
}
