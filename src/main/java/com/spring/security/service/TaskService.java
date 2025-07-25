package com.spring.security.service;

import com.spring.security.dto.TaskDTO;
import com.spring.security.entity.TaskEntity;

import java.util.List;

public interface TaskService {

   TaskDTO create(TaskDTO dto);

   TaskDTO update(Long id, TaskDTO dto);

   TaskDTO getById(Long id);

   List<TaskDTO> getAll();

   void delete(Long id);
}
