package com.jyan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyan.entity.Article;
import com.jyan.entity.Comment;
import com.jyan.entity.User;
import com.jyan.result.RegisterMessage;
import com.jyan.service.IUserService;
import com.jyan.service.impl.ArticleServiceImpl;
import com.jyan.service.impl.CommentServiceImpl;
import com.jyan.service.impl.UserServiceImpl;
import com.jyan.untils.JYanUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    IUserService iUserService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ArticleServiceImpl articleService;
    @GetMapping({"/","/index"})
    /**
     * 首页
     */
    public String index(){
        return "index";
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 注册页面
     * @return
     */
    @GetMapping("/register")
    public String doRegister(){
        return "register";
    }

    /**
     * 注册逻辑
     * @param registerMessage
     * @param model
     * @return
     */
    @PostMapping("/register")
    public String register(RegisterMessage registerMessage, Model model){
        JYanUntil.print("注册表单信息："+registerMessage.toString());
        if (!registerMessage.getUserpwd().equals(registerMessage.getReuserpwd())){
            model.addAttribute("registerMsg","密码不一致");
            return "register";
        }
        User tuser = userService.selectUserByName(registerMessage.getUsername());
        if(tuser!=null){
            model.addAttribute("registerMsg","用户已存在");
            return "register";
        }else {
            User user=new User();
            user.setUsername(registerMessage.getUsername());
            String SePwd=new BCryptPasswordEncoder().encode(registerMessage.getUserpwd());
            user.setUserpwd(SePwd);
            user.setNetname(registerMessage.getUsername());
            user.setRoleid(2);
            user.setImage("images/avatar/defimg.jpg");
            userService.saveUser(user);
        }
        return "redirect:/toLogin";
    }
    @GetMapping("/user/{uid}")
    public String userIndex(@PathVariable int uid,
                            Model model){
        User user=userService.selectById(uid);
        Integer acount=articleService.countAByU(uid);
        Integer comcount=commentService.countCByUser(uid);
        model.addAttribute("userInfo",user);
        model.addAttribute("articleCount",acount);
        model.addAttribute("commentCount",comcount);
        return "/user/index";
    }
    @GetMapping("/user/article/{uid}/{page}/{limit}")
    public String userArticle(@PathVariable int uid,
                              @PathVariable int page,
                              @PathVariable int limit,
                              Model model){
        User user=userService.selectById(uid);
        Page<Article> articlePage=new Page<>(page,limit);
        QueryWrapper<Article> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",uid).orderByDesc("createdTime");
        articleService.page(articlePage, wrapper);
        List<Article> articles=articlePage.getRecords();
        model.addAttribute("userInfo",user);
        model.addAttribute("articleList",articles);
        model.addAttribute("pageParam",articlePage);
        return "user/index";

    }
    @GetMapping("/user/comment/{uid}/{page}/{limit}")
    public String userComment(@PathVariable int uid,
                              @PathVariable int page,
                              @PathVariable int limit,
                              Model model){
        User user=userService.selectById(uid);
        Page<Comment> articlePage=new Page<>(page,limit);
        QueryWrapper<Comment> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",uid).orderByDesc("createdTime");
        commentService.page(articlePage, wrapper);
        List<Comment> comments=articlePage.getRecords();
        model.addAttribute("userInfo",user);
        model.addAttribute("commentList",comments);
        model.addAttribute("pageParam",articlePage);
        return "user/user-comment";

    }
    @GetMapping("/user/setting/{uid}")
    public String setting(@PathVariable int uid,
                          Model model){
        User user=userService.selectById(uid);
        model.addAttribute("userInfo",user);
        return "user/settings";
    }
    @PostMapping("/user/update/{uid}")
    public String doSetting(@PathVariable int uid,
                            User user){
            userService.updateU(uid,user);
            return "redirect:/user/"+uid;
    }
    @GetMapping("/user/img/{uid}")
    public String img(@PathVariable int uid,
                      Model model){
        User user=userService.selectById(uid);
        model.addAttribute("userInfo",user);
        return "user/update-avatar";

    }
    @GetMapping("/user/comment/delete/{uid}/{cid}")
    public String deleteC(@PathVariable int uid,
                          @PathVariable int cid){
        commentService.deleteCById(cid);
        System.out.println("评论已经删除");
        return "redirect:/user/"+uid;
    }


}

