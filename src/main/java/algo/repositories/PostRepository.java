package algo.repositories;

import algo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by AzatYusupov on 20.12.2017.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
