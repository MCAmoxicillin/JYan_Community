package com.jyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jyan.entity.Role;
import com.jyan.entity.User;
import com.jyan.mapper.UserMapper;
import com.jyan.service.IRoleService;
import com.jyan.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    IUserService iUserService;
    @Autowired
    HttpSession session;
    @Autowired
    IRoleService iRoleService;

    /**
     * 根据姓名查询用户
     * @param name
     * @return
     */
    public User selectUserByName(String name){
        QueryWrapper<User> wrapper=new QueryWrapper<User>();
        wrapper.eq("username",name);
        User user=userMapper.selectOne(wrapper);
        return user;
    }

    /**
     * 保存用户
     * @param user
     */
    public void  saveUser(User user){
        userMapper.insert(user);
    }

    /**
     * security中用户登录的验证处理
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = iUserService.getOne(new QueryWrapper<User>().eq("username", s));
        session.setAttribute("LoginUser",user);
        UserDetails userDetails=null;
        if(user!=null){
            String userpwd=user.getUserpwd();
            Collection<GrantedAuthority> authorities=getAuthority(user);
           userDetails=new org.springframework.security.core.userdetails.User(s,
                   userpwd,
                   true,
                   true,
                   true,
                   true,authorities);

        }

        return userDetails;
    }
    public Collection<GrantedAuthority> getAuthority(User user){
        List<GrantedAuthority> authorityList=new ArrayList<>();
        Role role=iRoleService.getById(user.getRoleid());
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
        return authorityList;

    }
    public User selectById(int id){
        User user = userMapper.selectById(id);
        return user;
    }
    public void updateU(int id,User user){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        userMapper.update(user,wrapper);
    }

}
