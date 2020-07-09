package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.Grade;
import org.springframework.stereotype.Repository;

/**
 * GradeDAO继承基类
 */
@Repository
public interface GradeDAO extends MyBatisBaseDao<Grade, Integer> {
}