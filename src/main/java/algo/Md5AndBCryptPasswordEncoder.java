package algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by AzatYusupov on 31.12.2017.
 */
public class Md5AndBCryptPasswordEncoder extends BCryptPasswordEncoder {
    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

    @Override
    public String encode(CharSequence rawPassword) {
        return super.encode(md5PasswordEncoder.encodePassword(rawPassword.toString(), null));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return super.matches(md5PasswordEncoder.encodePassword(rawPassword.toString(), null), encodedPassword);
    }
}
