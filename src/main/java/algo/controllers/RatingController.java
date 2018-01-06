package algo.controllers;

import algo.models.Problem;
import algo.models.User;
import algo.models.UserData;
import algo.services.ProblemService;
import algo.services.RatingService;
import algo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AzatYusupov on 02.01.2018.
 */
@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @RequestMapping("ratings/page/{page}")
    public String ratings(@PathVariable("page") Integer page, Model model) {
        if (page==null || page <= 0)
            page = 1;
        Page<UserData> userDatas = ratingService.findPage(page);
        ratingService.prepareToShow(userDatas, page);
        model.addAttribute("userDatas", userDatas);
        model.addAttribute("search", "");
        model.addAttribute("curPage", page);
        model.addAttribute("cntPages", ratingService.getCntPages());
        return "ratings";
    }
    @RequestMapping("ratings/search")
    public String ratingSearch(@RequestParam("search") String search, Model model) {
        if (search.isEmpty())
            return "redirect:/ratings";
        Page<UserData> userDatas = ratingService.finByUsernameLike(search);
        ratingService.prepareToShow(userDatas, 0);
        model.addAttribute("userDatas", userDatas);
        model.addAttribute("search", search);
        model.addAttribute("curPage", 1);
        model.addAttribute("cntPages", 1);
        return "ratings";
    }

    @RequestMapping("ratings")
    public String ratings(Model model) {
        return ratings(1, model);
    }

    @RequestMapping("/profile/{username:.+}")
    public String profile(@PathVariable("username") String username, Model model) {
        User user = userService.findByUserName(username);
        if (user==null) {
            model.addAttribute("message", "Foydalanuvchi topilmadi.");
            return "error";
        }
        user.setCode("");
        user.setPassword("");

        List<String> problemsId = problemService.findAllId();
        List<Problem> problems = new ArrayList<>();
        for (String problemId : problemsId) {
            Problem p = new Problem();
            p.setId(problemId);
            problems.add(p);
        }
        List<List<Problem>> problemsGroupList = problemService.preparedGropingProblems(problems,
                user.getData().getSolvedData(), user.getData().getUnsolvedData());

        user.getData().setPlace(ratingService.calcPlace(user.getData()));

        model.addAttribute("user", user);
        model.addAttribute("problemsGroupList", problemsGroupList);
        return "profile";
    }
}
