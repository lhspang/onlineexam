package com.sen.onlineexam.service.impl;

import com.sen.onlineexam.dao.UserSubjectDAO;
import com.sen.onlineexam.pojo.Subject;
import com.sen.onlineexam.pojo.User;
import com.sen.onlineexam.pojo.UserSubject;
import com.sen.onlineexam.service.UserSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSubjectServiceImpl implements UserSubjectService {
    @Autowired
    private UserSubjectDAO userSubjectDAO;

    @Override
    public boolean addUserSubject(UserSubject userSubject) {
        return userSubjectDAO.insert(userSubject) != 0;
    }

    @Override
    public boolean deleteUserSubject(UserSubject userSubject) {
        return userSubjectDAO.deleteByPrimaryKey(userSubject) != 0;
    }

    @Override
    @Transactional
    public boolean updateUserSubject(User user, String newSubjectId, String oldSubjectId) {
        String[] newList = newSubjectId.split(",");
        String[] oldList = oldSubjectId.split(",");
        Subject subject = new Subject();
        try {
            for (String o : oldList) {
                if (!newSubjectId.contains(o)) {
                    subject.setSubjectId(Integer.parseInt(o));
                    userSubjectDAO.deleteByPrimaryKey(new UserSubject(user, subject));
                }
            }
            for (String n : newList) {
                if (!oldSubjectId.contains(n)) {
                    subject.setSubjectId(Integer.parseInt(n));
                    userSubjectDAO.insert(new UserSubject(user, subject));
                }
            }
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<UserSubject> getUserSubject(Integer userId) {
        return userSubjectDAO.selectByUserId(userId);
    }

}
