package com.sen.onlineexam.service;

import com.alibaba.fastjson.JSONArray;
import com.sen.onlineexam.pojo.ExamItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sen
 * @date 2020/7/9 0:17
 */
@Service
public interface ExamItemService {
    boolean addExamItem(int examId, List<JSONArray> list);

    boolean deleteExamItem(List<Integer> itemIds);

    boolean updateExamItem(ExamItem examItem);
}
