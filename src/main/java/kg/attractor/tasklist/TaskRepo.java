package kg.attractor.tasklist;

import kg.attractor.tasklist.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepo extends PagingAndSortingRepository<Task, String> {
}
