package algo.services.Impl;

import algo.models.UserData;
import algo.repositories.RatingRepository;
import algo.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by AzatYusupov on 02.01.2018.
 */
@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Page<UserData> findPage(int page) {
        return ratingRepository.findAll(new PageRequest(page-1, UserData.PEAR_PAGE, sortRating()));
    }

    public Sort sortRating() {
        return new Sort(new Sort.Order(Sort.Direction.DESC, "solved"),
                new Sort.Order(Sort.Direction.ASC, "lastAccept"));
    }

    @Override
    public long getCntPages() {
        return (ratingRepository.count() + UserData.PEAR_PAGE - 1) / UserData.PEAR_PAGE;
    }

    @Override
    public void prepareToShow(Page<UserData> userDatas, int page) {
        List<UserData> allUserData = null;
        int place = (page-1) * UserData.PEAR_PAGE - 1;
        if (page == 0) { // If search by username
            allUserData = ratingRepository.findAll(sortRating());
            place = 0;
        }

        for (UserData userData : userDatas) {
            userData.setSolvedData("");
            userData.setUnsolvedData("");
            userData.getUser().setPassword("");
            userData.getUser().setCode("");
            userData.getUser().setEmail("");
            if (page==0) {
                while (userData.getUser().getId() != allUserData.get(place).getUser().getId()) {
                    place++;
                }
            }
            else
                place++;
            userData.setPlace(place+1);

            Date lastAccept = new Date(userData.getLastAccept() * 1000);
            userData.setLastAcceptDate(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(lastAccept));
        }
    }


    @Override
    public Page<UserData> finByUsernameLike(String search) {
        return ratingRepository.finByUsernameLike(search,
                new PageRequest(0, UserData.PEAR_PAGE, sortRating()));
    }

    @Override
    public void save(UserData userData) {
        ratingRepository.save(userData);
    }

    @Override
    public int calcPlace(UserData userdata) {
        return ratingRepository.calcPlace(userdata.getSolved(), userdata.getLastAccept()) + 1;
    }
}
