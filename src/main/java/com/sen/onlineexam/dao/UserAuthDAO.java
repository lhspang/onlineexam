package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.User;
import com.sen.onlineexam.pojo.UserAuth;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserAuthDAO继承基类
 */
@Repository
public interface UserAuthDAO extends MyBatisBaseDao<UserAuth, Integer> {

    int insert(List<UserAuth> list);

    UserAuth selectByIdentifier(String account);

    List<UserAuth> selectByUserId(int userId);

    int updatePassByUserId(UserAuth userAuth);

}