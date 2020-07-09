package com.sen.onlineexam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.dao.ProblemDAO;
import com.sen.onlineexam.pojo.Problem;
import com.sen.onlineexam.pojo.Protype;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProblemService {
    boolean addProblem(Problem problem);

    boolean deleteProblem(int id);

    boolean updateProblem(Problem problem);

    Problem getOneProblem(int id);

    PageInfo<Problem> getAllProblem(String problemName, Integer subjectId, Integer protypeId, int pageNum, int pageSize);
}
