package kg.attractor.tasklist.util;

import kg.attractor.tasklist.TaskRepo;
import kg.attractor.tasklist.model.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class DataPreloader {

    @Bean
    CommandLineRunner initDatabase(TaskRepo taskRepo){
        return args -> {
            taskRepo.deleteAll();

            List<Task> tasks = new ArrayList<>();
            tasks.add(Task.builder().description("Read a book").id(UUID.randomUUID().toString()).dateTime(LocalDateTime.now()).isDone(true).build());
            tasks.add(Task.builder().description("Watch Youtube").id(UUID.randomUUID().toString()).dateTime(LocalDateTime.now()).isDone(false).build());
            tasks.add(Task.builder().description("Learn marketing").id(UUID.randomUUID().toString()).dateTime(LocalDateTime.now()).isDone(false).build());

            taskRepo.saveAll(tasks);
        };
    }
}
