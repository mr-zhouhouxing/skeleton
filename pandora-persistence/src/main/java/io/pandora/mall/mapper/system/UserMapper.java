package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.system.User;
import io.pandora.mall.pojo.vo.system.UserInfo;

import java.util.List;

/**
 * @author Created by mr_zhou on 2020/12/1
 */
public interface UserMapper extends BaseDao<User> {

    /**
     * 根据账号 查找
     * @param account
     * @return
     */
    User findUserByAccount(String account);

    /**
     *
     * @param account
     * @return
     */
    UserInfo findUserInfoByAccount(String account);

    /**
     *
     * @param account
     * @return
     */
    List<UserInfo> findUserList(String account);
}
