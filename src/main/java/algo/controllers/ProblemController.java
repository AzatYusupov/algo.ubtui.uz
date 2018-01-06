package algo.controllers;

import algo.models.Problem;
import algo.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by AzatYusupov on 26.12.2017.
 */
@Controller
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @RequestMapping("problems/page/{page}")
    public String problems(@PathVariable(name = "page") Integer page, Model model) {
        if (page==null)
            page = 1;
        Page<Problem> problems = problemService.findPage(page);
        model.addAttribute("problems", problems);
        model.addAttribute("curPage", page);
        long cntPages=  problemService.getCntPages();
        model.addAttribute("cntPages", cntPages);
        model.addAttribute("search", "");
        return "problems";
    }

    @RequestMapping(value = "problems/search", method = RequestMethod.GET)
    public String problemSearch(@RequestParam(name = "search") String search, Model model) {
        if (search.isEmpty())
            return  "redirect:/problems";

        Page<Problem> problems = problemService.findByTitle(search);
        model.addAttribute("problems", problems);
        model.addAttribute("curPage", 1);
        model.addAttribute("cntPages", 1);
        model.addAttribute("search", search);
        return "problems";
    }

    @RequestMapping("problems")
    public String problems(Model model) {
        return problems(1, model);
    }

    @RequestMapping("problem/{id}")
    public String aboutProblem(@PathVariable(name = "id") String id, Model model) {
        Problem problem = problemService.findById(id);
        if (problem==null) {
            model.addAttribute("message", "Masala topilmadi");
            return "error";
        }
        model.addAttribute("problem", problem);
        return "aboutProblem";
    }
}
