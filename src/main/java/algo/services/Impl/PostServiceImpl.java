package algo.services.Impl;

import algo.configurations.Constants;
import algo.models.Post;
import algo.repositories.PostRepository;
import algo.services.PostService;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AzatYusupov on 20.12.2017.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(long id) {
        return postRepository.findOne(id);
    }

    @Override
    public Page<Post> findPage(int page) {
        return postRepository.findAll(new PageRequest(page-1,
                Post.PEAR_PAGE,
                Sort.Direction.DESC,
                "date"));
    }

    @Override
    public long getCntPages() {
        return (postRepository.count() + Post.PEAR_PAGE - 1) / Post.PEAR_PAGE;
    }
}
