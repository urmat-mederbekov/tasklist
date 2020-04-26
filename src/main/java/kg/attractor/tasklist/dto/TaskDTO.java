package kg.attractor.tasklist.dto;

import kg.attractor.tasklist.model.Task;
import lombok.*;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Data
public class TaskDTO {
    public static TaskDTO from(Task task){
        return  builder()
                .dateTime(task.getDateTime())
                .description(task.getDescription())
                .id(task.getId())
                .isDone(task.isDone())
                .build();
    }

    private String id;
    private String description;
    private LocalDateTime dateTime;
    private boolean isDone;
}
