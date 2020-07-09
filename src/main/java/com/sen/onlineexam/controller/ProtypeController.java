package com.sen.onlineexam.controller;

import com.sen.onlineexam.pojo.Protype;
import com.sen.onlineexam.service.ProtypeService;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("protype")
public class ProtypeController {
    @Autowired
    private ProtypeService protypeService;

    //添加题型
    @PostMapping("")
    @RequiresRoles(value="老师")
    public ResponseResult addProtype(Protype protype){
        if(protypeService.addProtype(protype)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //删除题型
    @DeleteMapping("{id}")
    @RequiresRoles(value="老师")
    public ResponseResult deleteProtype(@PathVariable int id){
        if(protypeService.deleteProtype(id)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //修改题型
    @PutMapping("{id}")
    @RequiresRoles(value="老师")
    public ResponseResult updateProtype(@PathVariable int id,Protype protype){
        protype.setProtypeId(id);
        if(protypeService.updateProtype(protype)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.BAD_REQUEST);
    }

    //查询一个题型
    @GetMapping("{id}")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult getOneProtype(@PathVariable int id){
        Protype protype = protypeService.getOneProtype(id);
        if(protype!= null){
            return ResponseResult.success(protype);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }

    //查询所有题型
    @GetMapping("all")
    @RequiresRoles(value={"管理员","老师"},logical= Logical.OR)
    public ResponseResult getAllProtype(){
        List<Protype> list = protypeService.getAllProtype();
        if(list!= null){
            return ResponseResult.success(list);
        }
        return ResponseResult.failure(ResultCode.NOT_FOUND);
    }
}
