package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.Role;
import org.springframework.stereotype.Repository;

/**
 * RoleDAO继承基类
 */
@Repository
public interface RoleDAO extends MyBatisBaseDao<Role, Integer> {
}