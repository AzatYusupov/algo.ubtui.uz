package algo.services;

import algo.models.Post;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by AzatYusupov on 20.12.2017.
 */
public interface PostService {
    List<Post> findAll();
    Post findById(long id);
    Page<Post>findPage(int page);
    long getCntPages();
}
