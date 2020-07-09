package com.sen.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.dao.ProblemDAO;
import com.sen.onlineexam.pojo.Problem;
import com.sen.onlineexam.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemDAO problemDAO;

    @Override
    public boolean addProblem(Problem problem) {
        return problemDAO.insertSelective(problem) != 0;
    }

    @Override
    public boolean deleteProblem(int id) {
        return problemDAO.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public boolean updateProblem(Problem problem) {
        return problemDAO.updateByPrimaryKey(problem) != 0;
    }

    @Override
    public Problem getOneProblem(int id) {
        return problemDAO.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Problem> getAllProblem(String problemName, Integer subjectId, Integer protypeId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(problemDAO.selectAllProblemOrName(problemName,subjectId,protypeId));
    }
}
