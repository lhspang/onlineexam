package com.sen.onlineexam.service;

import com.sen.onlineexam.dao.SubjectDAO;
import com.sen.onlineexam.pojo.Protype;
import com.sen.onlineexam.pojo.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    boolean addSubject(Subject subject);

    boolean deleteSubject(int id);

    boolean updateSubject(Subject subject);

    Subject getOneSubject(int id);

    List<Subject> getAllSubject();
}
