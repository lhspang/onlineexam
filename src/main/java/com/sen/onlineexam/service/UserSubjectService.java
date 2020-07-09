package com.sen.onlineexam.service;

import com.sen.onlineexam.dao.UserSubjectDAO;
import com.sen.onlineexam.pojo.Subject;
import com.sen.onlineexam.pojo.User;
import com.sen.onlineexam.pojo.UserSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public interface UserSubjectService {
    boolean addUserSubject(UserSubject userSubject);

    boolean deleteUserSubject(UserSubject userSubject) ;

    boolean updateUserSubject(User user, String newSubjectId, String oldSubjectId);

    List<UserSubject> getUserSubject(Integer userId);

}
