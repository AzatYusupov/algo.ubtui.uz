package algo.validators;

import algo.models.User;
import algo.services.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by AzatYusupov on 30.12.2017.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername() != null) {
            if (user.getUsername().length() < 3 || user.getUsername().length() > 20) {
                errors.rejectValue("username", "Size.userForm.username");
            }

            Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_]+", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(user.getUsername());
            if (!matcher.find() || !matcher.group(0).equals(user.getUsername())) {
                errors.rejectValue("username", "Incorrect.userForm.username");
            }

            if (userService.findByUserName(user.getUsername()) != null) {
                errors.rejectValue("username", "Duplicate.userForm.username");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            errors.rejectValue("email", "Incorrect.userForm.email");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword() != null) {
            if (user.getPassword().length() < 6 || user.getPassword().length() > 40) {
                errors.rejectValue("password", "Size.userForm.password");
            }
            Pattern pattern = Pattern.compile("[\\s\\n\\r\\t]", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(user.getPassword());
            if (matcher.find()) {
                errors.rejectValue("password", "Unacceptable.userForm.password");
            }

            int digits = 0, letters = 0;
            System.out.println(user.getPassword());
            for (int i = 0; i < user.getPassword().length(); i++) {
                if (Character.isDigit(user.getPassword().charAt(i)))
                    digits++;
                else if (Character.isLetter(user.getPassword().charAt(i)))
                    letters++;
            }
            if (digits == 0 || letters == 0) {
                errors.rejectValue("password", "Incorrect.userForm.password");
            }
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

    }
}
