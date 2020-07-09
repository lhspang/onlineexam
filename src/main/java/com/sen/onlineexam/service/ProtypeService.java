package com.sen.onlineexam.service;

import com.sen.onlineexam.dao.ProtypeDAO;
import com.sen.onlineexam.pojo.Protype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProtypeService {
    boolean addProtype(Protype protype);

    boolean deleteProtype(int id);

    boolean updateProtype(Protype protype);

    Protype getOneProtype(int id);

    List<Protype> getAllProtype();
}
