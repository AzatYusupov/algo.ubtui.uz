package algo.services;

import algo.models.UserData;
import org.springframework.data.domain.Page;


/**
 * Created by AzatYusupov on 02.01.2018.
 */
public interface RatingService {
    Page<UserData> findPage(int page);

    void prepareToShow(Page<UserData> userDatas, int page);

    long getCntPages();

    Page<UserData> finByUsernameLike(String search);

    void save(UserData userData);

    int calcPlace(UserData data);
}
