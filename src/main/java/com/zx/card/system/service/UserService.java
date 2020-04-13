package com.zx.card.system.service;

import com.github.pagehelper.Page;
import com.zx.card.system.model.User;
import com.zx.card.utils.Result;

public interface UserService {

    User selectUserByUsername(String username);

    Result selectUserByPage(Page<User> pageInfo, User user);

    User selectUserByID(Integer id);

    int removeUserByID(Integer id);

    int updateUserByRecord(User user);

    int saveUser(User user);

    Result resetPassword(User user, User loginUser);

    Result adminResetPwd(User user);

}
