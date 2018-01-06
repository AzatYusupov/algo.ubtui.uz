package algo.repositories;

import algo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by AzatYusupov on 30.12.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username=?1")
    User findByUserName(String username);

    @Query("SELECT u FROM User u WHERE u.email=?1")
    User finByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.code=?1")
    User findByCode(String code);
}
