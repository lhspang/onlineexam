package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.UserSubject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserSubjectDAO继承基类
 */
@Repository
public interface UserSubjectDAO extends MyBatisBaseDao<UserSubject, UserSubject> {

    List<UserSubject> selectByUserId(Integer userId);

}