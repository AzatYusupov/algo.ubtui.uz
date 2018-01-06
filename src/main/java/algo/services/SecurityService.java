package algo.services;

/**
 * Created by AzatYusupov on 30.12.2017.
 */
public interface SecurityService{

    String findLoggedInUsername();
    long findLoggedInUserId();
    void autologin(String username, String password);
}
