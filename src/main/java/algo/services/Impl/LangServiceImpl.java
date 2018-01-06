package algo.services.Impl;

import algo.models.Lang;
import algo.repositories.LangRepository;
import algo.services.LangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AzatYusupov on 31.12.2017.
 */

@Service
public class LangServiceImpl implements LangService {

    @Autowired
    private LangRepository langRepository;
    @Override
    public List<Lang> findAll() {
        return langRepository.findAll(new Sort(Sort.Direction.ASC, "title"));
    }


}
