package com.sen.onlineexam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.dao.UserAuthDAO;
import com.sen.onlineexam.dao.UserDAO;
import com.sen.onlineexam.pojo.Role;
import com.sen.onlineexam.pojo.User;
import com.sen.onlineexam.pojo.UserAuth;
import com.sen.onlineexam.pojo.UserInfo;
import com.sen.onlineexam.utils.IdentityType;
import com.sen.onlineexam.utils.JwtUtil;
import com.sen.onlineexam.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public interface UserService {
    /**
     * 注册
     *
     * @param userName 昵称
     * @param phone    手机号
     * @param password 密码
     * @return 是否成功
     */
    boolean register(String userName, String phone, String email, String password) ;

    /**
     * 登录
     *
     * @param account 账号
     * @return 是否成功
     */
    UserAuth login(String account);

    /**
     * 注册检查，检查用户名、手机号、邮箱是否已存在
     *
     * @param account 用户账号
     * @return UserAuth类
     */
    UserAuth registerCheck(String account);

    /**
     * 修改用户密码，包括找回密码
     *
     * @return 是否成功
     */
    @Transactional
    boolean updatePass(Integer userId, String oldPass, String newPass) ;

    /**
     * 修改用户信息
     *
     * @param user user类
     * @return 是否成功
     */
    @Transactional
    boolean updateUser(User user, String phone, String email) ;

    /**
     * 修改用户角色
     *
     * @param user user类
     * @return 是否成功
     */
    boolean updateUserRole(User user);

    /**
     * 根据id查找用户
     *
     * @param userId 用户id
     * @return 用户类
     */
    UserInfo selectById(int userId) ;

    /**
     * 分页查找用户
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 结果
     */
    PageInfo<User> findAllUser(Integer roleId, int pageNum, int pageSize) ;
}
