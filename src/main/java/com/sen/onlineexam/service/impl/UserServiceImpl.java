package com.sen.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.onlineexam.dao.UserAuthDAO;
import com.sen.onlineexam.dao.UserDAO;
import com.sen.onlineexam.pojo.Role;
import com.sen.onlineexam.pojo.User;
import com.sen.onlineexam.pojo.UserAuth;
import com.sen.onlineexam.pojo.UserInfo;
import com.sen.onlineexam.service.UserService;
import com.sen.onlineexam.utils.IdentityType;
import com.sen.onlineexam.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserAuthDAO userAuthDAO;
    PasswordUtil passwordUtil = new PasswordUtil();

    /**
     * 注册
     *
     * @param userName 昵称
     * @param phone    手机号
     * @param password 密码
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean register(String userName, String phone, String email, String password) {
        try {
            User user = new User();
            user.setUserName(userName);
            user.setRole(new Role(3, null));
            userDAO.insertSelective(user);

            String salt = passwordUtil.generateSalt();
            String newPass = passwordUtil.encryptPassword(password, salt);

            List<UserAuth> list = new ArrayList<>();
            list.add(new UserAuth(0, IdentityType.PHONE.getName(), phone, newPass, salt, user));
            list.add(new UserAuth(0, IdentityType.EMAIL.getName(), email, newPass, salt, user));
            list.add(new UserAuth(0, IdentityType.SYSTEM.getName(), userName, newPass, salt, user));
            userAuthDAO.insert(list);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 登录
     *
     * @param account 账号
     * @return 是否成功
     */
    @Override
    public UserAuth login(String account) {
        return userAuthDAO.selectByIdentifier(account);
    }

    /**
     * 注册检查，检查用户名、手机号、邮箱是否已存在
     *
     * @param account 用户账号
     * @return UserAuth类
     */
    @Override
    public UserAuth registerCheck(String account) {
        return userAuthDAO.selectByIdentifier(account);
    }

    /**
     * 修改用户密码，包括找回密码
     *
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean updatePass(Integer userId, String oldPass, String newPass) {
        List<UserAuth> authList = userAuthDAO.selectByUserId(userId);
        authList.removeIf(userAuth -> userAuth.getIdentityType().equals(IdentityType.QQ.getName()));
        if (oldPass == null) {  //找回密码
            String salt = passwordUtil.generateSalt();
            newPass = passwordUtil.encryptPassword(newPass, salt);
            try {
                for (UserAuth userAuth : authList) {

                    userAuth.setCredential(newPass);
                    userAuth.setSalt(salt);
                    userAuthDAO.updatePassByUserId(userAuth);
                }
                return true;
            } catch (Exception e) {
                throw new RuntimeException();
            }
        } else {   //修改密码
            String oldSalt = authList.get(0).getSalt();
            oldPass = passwordUtil.encryptPassword(oldPass, oldSalt);
            if (authList.get(0).getCredential().equals(oldPass)) {
                String salt = passwordUtil.generateSalt();
                newPass = passwordUtil.encryptPassword(newPass, salt);
                try {
                    for (UserAuth userAuth : authList) {
                        userAuth.setCredential(newPass);
                        userAuth.setSalt(salt);
                        userAuthDAO.updatePassByUserId(userAuth);
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }
        return false;
    }

    /**
     * 修改用户信息
     *
     * @param user user类
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean updateUser(User user, String phone, String email) {
        try {
            userDAO.updateByPrimaryKeySelective(user);
            if (phone != null) {
                UserAuth userAuth = new UserAuth(null, IdentityType.PHONE.getName(), phone, null, null, user);
                userAuthDAO.updateByPrimaryKey(userAuth);
            }
            if (email != null) {
                UserAuth userAuth1 = new UserAuth(null, IdentityType.EMAIL.getName(), email, null, null, user);
                userAuthDAO.updateByPrimaryKey(userAuth1);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 修改用户角色
     *
     * @param user user类
     * @return 是否成功
     */
    @Override
    public boolean updateUserRole(User user) {
        return userDAO.updateByPrimaryKeySelective(user) != 0;
    }

    /**
     * 根据id查找用户
     *
     * @param userId 用户id
     * @return 用户类
     */
    @Override
    public UserInfo selectById(int userId) {
        User user = userDAO.selectByPrimaryKey(userId);
        if (user != null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUser(user);
            List<UserAuth> userAuths = userAuthDAO.selectByUserId(userId);
            for (UserAuth userAuth : userAuths) {
                if (userAuth.getIdentityType().equals(IdentityType.EMAIL.getName())) {
                    userInfo.setEmail(userAuth.getIdentifier());
                } else if (userAuth.getIdentityType().equals(IdentityType.PHONE.getName())) {
                    userInfo.setPhone(userAuth.getIdentifier());
                } else if (userAuth.getIdentityType().equals(IdentityType.QQ.getName())) {
                    userInfo.setQq(userAuth.getIdentifier());
                }
            }
            return userInfo;
        }
        return null;
    }

    /**
     * 分页查找用户
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 结果
     */
    @Override
    public PageInfo<User> findAllUser(Integer roleId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userDAO.selectAll(roleId));
    }
}
