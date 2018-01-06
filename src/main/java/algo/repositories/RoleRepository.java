package algo.repositories;

import algo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AzatYusupov on 30.12.2017.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
