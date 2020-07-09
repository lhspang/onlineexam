package com.sen.onlineexam.service.impl;

import com.sen.onlineexam.dao.ProtypeDAO;
import com.sen.onlineexam.pojo.Protype;
import com.sen.onlineexam.service.ProtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtypeServiceImpl implements ProtypeService {

    @Autowired
    private ProtypeDAO protypeDAO;

    @Override
    public boolean addProtype(Protype protype) {
        return protypeDAO.insert(protype) != 0;
    }

    @Override
    public boolean deleteProtype(int id) {
        return protypeDAO.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public boolean updateProtype(Protype protype) {
        return protypeDAO.updateByPrimaryKey(protype) != 0;
    }

    @Override
    public Protype getOneProtype(int id) {
        return protypeDAO.selectByPrimaryKey(id);
    }

    @Override
    public List<Protype> getAllProtype(){
        return protypeDAO.selectAll();
    }
}
