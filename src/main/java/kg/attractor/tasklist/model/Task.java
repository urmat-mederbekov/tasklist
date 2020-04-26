package kg.attractor.tasklist.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Task {

    @Id
    private String id;
    private String description;
    private LocalDateTime dateTime;
    private boolean isDone;
}
