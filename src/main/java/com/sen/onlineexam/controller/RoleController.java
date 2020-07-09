package com.sen.onlineexam.controller;

import com.sen.onlineexam.pojo.Role;
import com.sen.onlineexam.service.RoleService;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    //添加角色
    @PostMapping("")
    @RequiresRoles(value="管理员")
    public ResponseResult addProtype(Role role) {
        if (roleService.addRole(role)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //删除角色
    @DeleteMapping("{id}")
    @RequiresRoles(value="管理员")
    public ResponseResult deleteProtype(@PathVariable int id) {
        if (roleService.deleteRole(id)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //修改角色
    @PutMapping("{id}")
    @RequiresRoles(value="管理员")
    public ResponseResult updateProtype(@PathVariable int id, Role role) {
        role.setRoleId(id);
        if (roleService.updateRole(role)) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //查询一个角色
    @GetMapping("{id}")
    @RequiresRoles(value="管理员")
    public ResponseResult getOneProtype(@PathVariable int id) {
        Role role = roleService.getOneRole(id);
        if (role != null) {
            return ResponseResult.success(role);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //查询所有角色
    @GetMapping("all")
    @RequiresRoles(value="管理员")
    public ResponseResult getAllProtype() {
        List<Role> list = roleService.getAllRole();
        if (list != null) {
            return ResponseResult.success(list);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }
}
