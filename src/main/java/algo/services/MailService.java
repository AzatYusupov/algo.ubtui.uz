package algo.services;

/**
 * Created by AzatYusupov on 01.01.2018.
 */
public interface MailService {
    void sendGreetingMessage(String to, String username);
    void sendForgotPasswordMessage(String to, String username, String code);
}
