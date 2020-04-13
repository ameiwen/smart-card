package com.zx.card.system.dao;

import com.zx.card.system.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TaskMapper {

    Task selectTaskByPrimaryKey(Integer id);

    List<Task> selectTaskListWhere(Map<String, Object> map);

    int insertTaskSelective(Task task);

    int updateTaskByPrimaryKeySelective(Task task);

    int deleteTaskByPrimaryKey(Integer id);

}

