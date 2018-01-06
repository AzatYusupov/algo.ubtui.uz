package algo.services;

import algo.models.User;

/**
 * Created by AzatYusupov on 30.12.2017.
 */
public interface UserService {

    User findByUserName(String username);
    User findByEmail(String email);
    User findByCode(String code);
    void saveWithNewPassword(User user);
    void save(User user);
}
