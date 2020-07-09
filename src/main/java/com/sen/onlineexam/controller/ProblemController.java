package com.sen.onlineexam.controller;

import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.pojo.Problem;
import com.sen.onlineexam.pojo.Protype;
import com.sen.onlineexam.pojo.Subject;
import com.sen.onlineexam.service.ProblemService;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    //添加问题
    @PostMapping("")
    @RequiresRoles(value = "老师")
    public ResponseResult addProblem(@Param("subjectId") int subjectId, @Param("protypeId") int protypeId, Problem problem) {
        problem.setSubject(new Subject(subjectId, null));
        problem.setProtype(new Protype(protypeId, null, 0));
        if (problemService.addProblem(problem)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //删除问题
    @DeleteMapping("{id}")
    @RequiresRoles(value = "老师")
    public ResponseResult deleteProblem(@PathVariable int id) {
        if (problemService.deleteProblem(id)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //修改问题
    @PutMapping("{id}")
    @RequiresRoles(value = "老师")
    public ResponseResult updateProblem(@PathVariable int id, @Param("subjectId") int subjectId, @Param("protypeId") int protypeId, Problem problem) {
        problem.setProblemId(id);
        problem.setSubject(new Subject(subjectId, null));
        problem.setProtype(new Protype(protypeId, null, 0));
        if (problemService.updateProblem(problem)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //查询一个问题
    @GetMapping("{id}")
    @RequiresRoles(value = {"管理员", "老师"}, logical = Logical.OR)
    public ResponseResult getOneProblem(@PathVariable int id) {
        Problem problem = problemService.getOneProblem(id);
        if (problem != null) {
            return ResponseResult.success(problem);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //查询所有问题或模糊查询
    @GetMapping("all")
    @RequiresRoles(value = {"管理员", "老师"}, logical = Logical.OR)
    public ResponseResult getAllProblem(String problemName, Integer subjectId, Integer protypeId, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageInfo<Problem> list = problemService.getAllProblem(problemName, subjectId, protypeId, pageNum, pageSize);
        if (list != null) {
            return ResponseResult.success(list);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }
}
