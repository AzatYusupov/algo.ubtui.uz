package algo.services;

import algo.models.Problem;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by AzatYusupov on 26.12.2017.
 */
public interface ProblemService {
    List<Problem> findAll();

    Page<Problem> findPage(int page);

    Problem findById(String id);

    long getCntPages();

    Page<Problem> findByTitle(String search);

    List<List<Problem>> preparedGropingProblems(List<Problem> problems, String solvedData, String unsolvedData);

    List<String> findAllId();
}
