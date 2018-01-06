package algo.repositories;

import algo.models.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AzatYusupov on 26.12.2017.
 */
@Repository
public interface ProblemRepository extends JpaRepository<Problem, String> {

    @Query("SELECT p FROM Problem p WHERE p.title LIKE %?1% OR p.id LIKE %?1%")
    Page<Problem> findByTitleLike(@Param("search") String search, Pageable pageable);

    @Query("SELECT id FROM Problem")
    List<String> findAllId();
}
