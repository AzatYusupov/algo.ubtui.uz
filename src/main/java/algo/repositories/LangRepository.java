package algo.repositories;

import algo.models.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AzatYusupov on 28.12.2017.
 */
@Repository
public interface LangRepository extends JpaRepository<Lang, String> {
}
