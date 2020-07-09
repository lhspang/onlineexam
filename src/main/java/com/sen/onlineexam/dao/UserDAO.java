package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDAO继承基类
 */
@Repository
public interface UserDAO extends MyBatisBaseDao<User, Integer> {

    List<User> selectAll(Integer roleId);

}