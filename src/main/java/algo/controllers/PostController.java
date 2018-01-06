package algo.controllers;

import algo.models.Post;
import algo.models.User;
import algo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.*;


/**
 * Created by AzatYusupov on 19.12.2017.
 */
@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/page/{page}")
    public String indexPage(@PathVariable(name = "page") Integer page, Model model) {
        if (page <= 0)
            page = 1;
        Page<Post> posts = postService.findPage(page);
        model.addAttribute("posts", posts);
        model.addAttribute("curPage", page);
        long cntPages = postService.getCntPages();
        model.addAttribute("cntPages", cntPages);
        return "index";
    }

    @RequestMapping("/")
    public String index(Model model) {

//        "Userid"
//        List<User> users = jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<>(User.class));
//        int not = 0;
//        for (User user : users) {
//            String sql = "SELECT COUNT(*) FROM userdata WHERE USER_ID=?";
//
//            int cnt = jdbcTemplate.queryForObject(sql, Integer.class, user.getId());
//            if (cnt==0) {
//                System.out.println(user.getId()+" not found");
//                sql = "INSERT INTO userdata (USER_ID, SOLVED, SOLVED_DATA, UNSOLVED_DATA, LAST_ACCEPT) VALUES (?, ?, ?, ?, ?)";
//                jdbcTemplate.update(sql, user.getId(), 0, "", "", 0);
//                not++;
//            }
//        }
//        System.out.println(not+" inserted");

//      Avatars
//        File directory = new File("d:\\Spring\\MyProjects\\algo-mvc\\avatars");
//        List<String> files = new ArrayList<>();
//        for (File file : directory.listFiles()) {
//            files.add(file.getName());
//        }
//        Collections.sort(files);
//        Collections.reverse(files);
//        Set<String> set = new HashSet<>();
//        int cnt = 0;
//        for (String filename : files) {
//            String name = filename;
//            name = name.replace('_', ' ');
//            String[]s = name.split(" ");
//            System.out.println("d:\\Spring\\MyProjects\\algo-mvc\\avatars\\" + s[0] + "_" + s[1]);
//            File oldFile = new File("d:\\Spring\\MyProjects\\algo-mvc\\avatars\\"+s[0]+"_"+s[1]);
//            if (set.contains(s[0])) {
////                System.out.println(s[0]+" "+s[1]);
//                oldFile.delete();
//                cnt++;
//            }
//            else {
//                File newFile = new File("d:\\Spring\\MyProjects\\algo-mvc\\avatars\\"+s[0]+".jpg");
//                if (oldFile.renameTo(newFile))
//                    System.out.println("rename success");
//                else
//                    System.out.println("rename unsuccess");
//                set.add(s[0]);
//            }
//        }
//        System.out.println(cnt+" files deleted");
        return indexPage(1, model);
    }

    @RequestMapping("/post/{id}")
    public String aboutPost(@PathVariable("id") Long id, Model model) {
        Post post = postService.findById(id);
        if (post==null) {
            model.addAttribute("message", "Post topilmadi");
            return "error";
        }
        model.addAttribute("post", post);
        return "aboutPost";
    }
}
