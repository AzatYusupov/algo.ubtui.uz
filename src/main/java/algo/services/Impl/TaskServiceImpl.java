package algo.services.Impl;

import algo.models.Lang;
import algo.models.State;
import algo.models.Task;
import algo.models.User;
import algo.repositories.TaskRepository;
import algo.services.SecurityService;
import algo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AzatYusupov on 28.12.2017.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public Page<Task> findPage(int page) {
        return taskRepository.findAll(new PageRequest(page-1, Task.PEAR_PAGE,
                Sort.Direction.DESC, "id"));
    }

    @Override
    public void save(String problemId, String langId, String source) {
        Task task = new Task();
        task.setProblemId(problemId);
        task.setLang(new Lang(langId));
        task.setSource(source);
        task.setSize(getSourceSize(source));
        task.setUser(new User(securityService.findLoggedInUserId()));
        if (langId.equals("java"))
            task.setFileName(getFileName(source));
        else
            task.setFileName("Main");
        long time = System.currentTimeMillis() / 1000;
        task.setCreatedOn(time);
        task.setCreatedTime(time);
        task.setState(new State(-2));
        taskRepository.save(task);
    }

    @Override
    public void prepareToShow(Page<Task> results) {
        for (Task task : results) {
            task.setSource("");
            task.setFileName("");
            Date createdDate = new Date(task.getCreatedOn() * 1000);
            Date curDate = new Date();
            String createdDay = new SimpleDateFormat("dd.MM.yyyy").format(createdDate);
            String curDay = new SimpleDateFormat("dd.MM.yyyy").format(curDate);
            String createdTime = new SimpleDateFormat("HH.mm.ss").format(createdDate);

            if (createdDay.equals(curDay))
                task.setDate(createdTime);
            else
                task.setDate(createdDay + "\n" + createdTime);
        }
    }

    private String getFileName(String source) {
        String fileName = "Main";
        Pattern regex = Pattern.compile("public+\\s+class+\\s+[a-zA-Z_$][a-zA-Z0-9_$]+", Pattern.DOTALL);
        Matcher regexMatcher = regex.matcher(source);
        if (regexMatcher.find()) {
            System.out.println("++++++");
            System.out.println(regexMatcher.group(0));
            String[]ss = regexMatcher.group(0).split(" ");
            fileName = ss[ss.length-1];
        }
        return fileName;
    }
    private int getSourceSize(String source) {
        int cnt = 0;
        for (int i = 0; i < source.length(); i++) {
            int w = source.codePointAt(i);
            if (w != 32 && w != 9 && w != 13 && w != 10)
                cnt++;
        }
        return cnt;
    }
}
