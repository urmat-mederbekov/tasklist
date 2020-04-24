package kg.attractor.tasklist.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Task {

    @Id
    private String id = UUID.randomUUID().toString();
    private String description;
    private LocalDateTime dateTime = LocalDateTime.now();
}
