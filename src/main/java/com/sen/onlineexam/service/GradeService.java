package com.sen.onlineexam.service;

import com.sen.onlineexam.dao.GradeDAO;
import com.sen.onlineexam.pojo.Grade;
import com.sen.onlineexam.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService {
    boolean addGrade(Grade grade);

    boolean deleteGrade(int id);

    boolean updateGrade(Grade grade);

    Grade getOneGrade(int id);

    List<Grade> getAllGrade();
}
