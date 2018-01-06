package algo.services;

import algo.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by AzatYusupov on 28.12.2017.
 */
public interface TaskService {

    Page<Task> findPage(int page);
    void save(String problemId, String langId, String source);
    void prepareToShow(Page<Task> results);
}
