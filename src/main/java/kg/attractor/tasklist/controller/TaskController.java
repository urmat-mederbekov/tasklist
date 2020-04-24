package kg.attractor.tasklist.controller;

import kg.attractor.tasklist.dto.TaskDTO;
import kg.attractor.tasklist.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public Page<TaskDTO> getTasks(Pageable pageable){
        return taskService.findTasks(pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO addTask(@RequestBody TaskDTO taskData){
        return taskService.addTask(taskData);
    }
}
