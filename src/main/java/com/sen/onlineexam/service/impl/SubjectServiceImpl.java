package com.sen.onlineexam.service.impl;

import com.sen.onlineexam.dao.SubjectDAO;
import com.sen.onlineexam.pojo.Subject;
import com.sen.onlineexam.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    public boolean addSubject(Subject subject) {
        return subjectDAO.insert(subject) != 0;
    }

    @Override
    public boolean deleteSubject(int id) {
        return subjectDAO.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public boolean updateSubject(Subject subject) {
        return subjectDAO.updateByPrimaryKey(subject) != 0;
    }

    @Override
    public Subject getOneSubject(int id) {
        return subjectDAO.selectByPrimaryKey(id);
    }

    @Override
    public List<Subject> getAllSubject(){
        return subjectDAO.selectAll();
    }
}
