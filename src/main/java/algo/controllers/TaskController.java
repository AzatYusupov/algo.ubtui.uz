package algo.controllers;

import algo.models.Lang;
import algo.models.Problem;
import algo.models.Task;
import algo.services.LangService;
import algo.services.ProblemService;
import algo.services.TaskService;
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
 * Created by AzatYusupov on 28.12.2017.
 */
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private LangService langService;

    @Autowired
    private ProblemService problemService;


    @RequestMapping("/results/page/{page}")
    public String results(@PathVariable("page") Integer page, Model model) {
        if (page==null)
            page = 1;
        if (page > 100) {
            return "redirect:/results/page/100";
        }
        Page<Task> results = taskService.findPage(page);
        taskService.prepareToShow(results);
        model.addAttribute("results", results);
        return "results";
    }

    @RequestMapping("/results")
    public String results(Model model) {
        return results(1, model);
    }

    @RequestMapping("/send/{problemId}")
    public String send(@PathVariable("problemId") String problemId, Model model) {
        Problem problem = problemService.findById(problemId);
        if (problem==null) {
            model.addAttribute("message", "Masala topilmadi");
            return "error";
        }
        problem.setBody("");
        List<Lang> langs = langService.findAll();
        model.addAttribute("problem", problem);
        model.addAttribute("langs", langs);
        return "send";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(@RequestParam("problemId") String problemId, @RequestParam("source") String source, @RequestParam("langId") String langId) {
        if (source.isEmpty())
            return "redirect:/send/"+problemId;
        taskService.save(problemId, langId, source);
        return "redirect:/results";
    }

}
