package algo.controllers;

import algo.models.Problem;
import algo.models.User;
import algo.models.UserData;
import algo.services.*;
import algo.validators.UserValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by AzatYusupov on 30.12.2017.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MailService mailService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ProblemService problemService;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute User user, BindingResult bindingResult, Model model) {

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "registration";

        String password = user.getPassword();
        userService.saveWithNewPassword(user);
        UserData userData = new UserData();
        userData.setUser(user);
        userData.setSolvedData("");
        userData.setUnsolvedData("");
        ratingService.save(userData);

        securityService.autologin(user.getUsername(), password);
        mailService.sendGreetingMessage(user.getEmail(), user.getUsername());
        model.addAttribute("message", "Tabriklaymiz. Siz muvofaqiyatli ro'yxatdan o'tdingiz. Hoziroq masala yechishni boshlashingiz mumkin.");
        return "success";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Login yoki parol hato");

        if (logout != null)
            model.addAttribute("message", "Muvofaqiyatli tizimdan chiqildi");

        return "login";
    }

    @RequestMapping(value = "/forgot")
    public String forgot(Model model) {
        model.addAttribute("user", new User());
        return "forgot";
    }
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String forgot(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        String input = user.getUsername();
        if (input.isEmpty()) {
            bindingResult.rejectValue("username","NotEmpty");
            return "forgot";
        }

        User wantedUser = userService.findByEmail(input);
        if (wantedUser == null) {
            wantedUser = userService.findByUserName(input);
        }
        if (wantedUser == null) {
            bindingResult.rejectValue("username","Forgot.password.not.found");
            return "forgot";
        }

        String code = RandomStringUtils.randomAlphanumeric(30);
        wantedUser.setCode(code);
        userService.save(wantedUser);

        model.addAttribute("message", "Sizning emailingizga parolni tiklash bo'yicha yo'riqnoma yuborildi.");
        mailService.sendForgotPasswordMessage(wantedUser.getEmail(), wantedUser.getUsername(), code);
        return "success";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping("/passwordChange/{code}")
    public String passwordChange(@PathVariable("code") String code, Model model) {
        User user = userService.findByCode(code);
        if (user==null) {
            model.addAttribute("message", "Parolni tiklash kodi hato");
            return "error";
        }
        User newUser = new User();
        newUser.setCode(code);
        model.addAttribute("user", newUser);
        return "passwordChange";
    }

    @RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
    public String passwordChange(@ModelAttribute User user, BindingResult bindingResult, Model model) {

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasFieldErrors("password") || bindingResult.hasFieldErrors("passwordConfirm")) {
            return "passwordChange";
        }
        User wantedUser = userService.findByCode(user.getCode());
        if (wantedUser==null) {
            System.out.println("user not found by code");
            return "error";
        }
        wantedUser.setPassword(user.getPassword());
        wantedUser.setCode("");
        userService.saveWithNewPassword(wantedUser);
        model.addAttribute("message", "Parol muvaffaqiyatli o'zgartirildi!");
        return "success";
    }


}
