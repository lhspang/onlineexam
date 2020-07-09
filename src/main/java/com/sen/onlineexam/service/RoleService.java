package com.sen.onlineexam.service;

import com.sen.onlineexam.dao.RoleDAO;
import com.sen.onlineexam.pojo.Role;
import com.sen.onlineexam.pojo.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    boolean addRole(Role role);

    boolean deleteRole(int id);

    boolean updateRole(Role role);

    Role getOneRole(int id);

    List<Role> getAllRole();
}
