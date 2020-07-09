package com.sen.onlineexam.service.impl;

import com.sen.onlineexam.dao.GradeDAO;
import com.sen.onlineexam.pojo.Grade;
import com.sen.onlineexam.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeDAO gradeDAO;

    @Override
    public boolean addGrade(Grade grade) {
        return gradeDAO.insert(grade) != 0;
    }

    @Override
    public boolean deleteGrade(int id) {
        return gradeDAO.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public boolean updateGrade(Grade grade) {
        return gradeDAO.updateByPrimaryKey(grade) != 0;
    }

    @Override
    public Grade getOneGrade(int id) {
        return gradeDAO.selectByPrimaryKey(id);
    }

    @Override
    public List<Grade> getAllGrade(){
        return gradeDAO.selectAll();
    }
}
