package com.sen.onlineexam.service.impl;

import com.sen.onlineexam.dao.RoleDAO;
import com.sen.onlineexam.pojo.Role;
import com.sen.onlineexam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public boolean addRole(Role role) {
        return roleDAO.insert(role) != 0;
    }

    @Override
    public boolean deleteRole(int id) {
        return roleDAO.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public boolean updateRole(Role role) {
        return roleDAO.updateByPrimaryKey(role) != 0;
    }

    @Override
    public Role getOneRole(int id) {
        return roleDAO.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> getAllRole(){
        return roleDAO.selectAll();
    }
}
