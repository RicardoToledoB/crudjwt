package com.spring.security.service;

import com.spring.security.dto.TaskDTO;
import com.spring.security.entity.TaskEntity;
import com.spring.security.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;


    private TaskDTO mapToDTO(TaskEntity entity) {
        return TaskDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }

    private TaskEntity mapToEntity(TaskDTO dto) {
        return TaskEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public TaskDTO create(TaskDTO dto) {
        TaskEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public TaskDTO update(Long id, TaskDTO dto) {
        TaskEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return mapToDTO(repository.save(entity));
    }

    @Override
    public TaskDTO getById(Long id) {
        TaskEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<TaskDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
