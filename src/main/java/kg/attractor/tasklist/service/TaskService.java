package kg.attractor.tasklist.service;

import kg.attractor.tasklist.TaskRepo;
import kg.attractor.tasklist.dto.TaskDTO;
import kg.attractor.tasklist.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;

    public Page<TaskDTO> findTasks(Pageable pageable){
        return taskRepo.findAll(pageable).map(TaskDTO::from);
    }

    public TaskDTO addTask(TaskDTO taskData){

        Task task = Task.builder()
                .dateTime(LocalDateTime.now())
                .description(taskData.getDescription())
                .id(UUID.randomUUID().toString())
                .isDone(false)
                .build();
        taskRepo.save(task);
        return TaskDTO.from(task);
    }
    public TaskDTO changeTaskStatus(TaskDTO taskData){
        Task task = taskRepo.findById(taskData.getId()).get();
        System.out.println(task.isDone() + " -> " + !task.isDone());
        task.setDone(!task.isDone());
        taskRepo.save(task);
        return TaskDTO.from(task);
    }
}
