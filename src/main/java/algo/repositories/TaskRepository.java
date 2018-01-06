package algo.repositories;

import algo.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AzatYusupov on 28.12.2017.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
