package algo.repositories;

import algo.models.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by AzatYusupov on 02.01.2018.
 */
public interface RatingRepository extends JpaRepository<UserData, Long> {
    @Query("SELECT ud FROM UserData ud WHERE ud.user.username LIKE %?1%")
    Page<UserData> finByUsernameLike(String search, Pageable pageable);

    @Query("SELECT COUNT(USER_ID) FROM UserData WHERE solved > ?1 OR (solved=?1 AND lastAccept < ?2)")
    int calcPlace(long solved, long lastAccept);
}
