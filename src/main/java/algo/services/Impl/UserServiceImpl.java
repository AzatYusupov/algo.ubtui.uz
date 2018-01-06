package algo.services.Impl;

import algo.Md5AndBCryptPasswordEncoder;
import algo.models.User;
import algo.repositories.RoleRepository;
import algo.repositories.UserRepository;
import algo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by AzatYusupov on 30.12.2017.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Md5AndBCryptPasswordEncoder md5AndBCryptPasswordEncoder;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.finByEmail(email);
    }

    @Override
    public User findByCode(String code) {
        if (code==null || code.length() != 30)
            return null;
        return userRepository.findByCode(code);
    }

    @Override
    public void saveWithNewPassword(User user) {
        user.setPassword(md5AndBCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
