package com.sen.onlineexam.dao;

import com.sen.onlineexam.pojo.Protype;
import org.springframework.stereotype.Repository;

/**
 * ProtypeDAO继承基类
 */
@Repository
public interface ProtypeDAO extends MyBatisBaseDao<Protype, Integer> {
}