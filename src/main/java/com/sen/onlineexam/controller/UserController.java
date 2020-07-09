package com.sen.onlineexam.controller;

import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.pojo.*;
import com.sen.onlineexam.service.UserService;
import com.sen.onlineexam.service.UserSubjectService;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserSubjectService userSubjectService;

    //用户修改密码
    @PutMapping("updatePass")
    @RequiresRoles(value = {"管理员", "老师", "学生"}, logical = Logical.OR)
    public ResponseResult updatePass(Integer userId, String oldPass, String newPass) {
        if (userService.updatePass(userId, oldPass, newPass)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.ERROR);
    }

    //用户找回密码
    @PostMapping("findPass")
    public ResponseResult findPass(Integer userId, String password) {
        if (userService.updatePass(userId, null, password)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.ERROR);
    }

    //为老师修改所授课程
    @PutMapping("subject/{userId}")
    @RequiresRoles(value = "管理员")
    public ResponseResult addUserSubject(@PathVariable Integer userId, String newSubjectId,String oldSubjectId) {
        User user = new User();
        user.setUserId(userId);
        if (userSubjectService.updateUserSubject(user,newSubjectId,oldSubjectId)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.ERROR);
    }

    //删除老师所授课程
   /* @DeleteMapping("{userId}/{subjectId}")
    @RequiresRoles(value = "管理员")
    public ResponseResult deleteUserSubject(@PathVariable Integer userId, @PathVariable Integer subjectId) {
        User user = new User();
        Subject subject = new Subject();
        user.setUserId(userId);
        subject.setSubjectId(subjectId);
        if (userSubjectService.deleteUserSubject(new UserSubject(user, subject))) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.ERROR);
    }*/

    //修改用户信息
    @PutMapping("{id}")
    public ResponseResult updateUser(@PathVariable Integer id, User user, String phone, String email) {
        user.setUserId(id);
        if (userService.updateUser(user, phone, email)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.ERROR);
    }

    //修改用户角色
    @PutMapping("{userId}/{roleId}")
    @RequiresRoles(value = "管理员")
    public ResponseResult updateUserRole(@PathVariable Integer userId, @PathVariable Integer roleId) {
        User user = new User();
        user.setUserId(userId);
        user.setRole(new Role(roleId, null));
        if (userService.updateUserRole(user)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.ERROR);
    }

    //查询一个老师所有教授的课程
    @GetMapping("{userId}/subject")
    @RequiresRoles(value = {"管理员", "老师"}, logical = Logical.OR)
    public ResponseResult getUserSubject(@PathVariable Integer userId) {
        List<UserSubject> list = userSubjectService.getUserSubject(userId);
        if (list != null && list.size() != 0) {
            List<Subject> subjectList = new ArrayList<>();
            for (UserSubject u : list) {
                subjectList.add(u.getSubject());
            }
            /*Map<String,Object> map = new HashMap<>();
            map.put("user",list.get(0).getUser());
            map.put("subject",subjectList);*/
            return ResponseResult.success(subjectList);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //查询一个用户的信息
    @GetMapping("{userId}")
    @RequiresRoles(value = {"管理员", "老师", "学生"}, logical = Logical.OR)
    public ResponseResult getOneUser(@PathVariable Integer userId) {
        UserInfo userInfo = userService.selectById(userId);
        if (userInfo == null) {
            return ResponseResult.failure(ResultCode.NOT_FOUND);
        }
        return ResponseResult.success(userInfo);
    }

    //分页查询所有用户信息
    @GetMapping("all")
    @RequiresRoles(value = {"管理员", "老师"}, logical = Logical.OR)
    public ResponseResult getAllUser(Integer roleId, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageInfo<User> pageInfo = userService.findAllUser(roleId, pageNum, pageSize);
        if (pageInfo == null || pageInfo.getSize() == 0) {
            return ResponseResult.failure(ResultCode.NOT_FOUND);
        }
        return ResponseResult.success(pageInfo);
    }

}
