package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.Subject;
import org.springframework.stereotype.Repository;

/**
 * SubjectDAO继承基类
 */
@Repository
public interface SubjectDAO extends MyBatisBaseDao<Subject, Integer> {
}