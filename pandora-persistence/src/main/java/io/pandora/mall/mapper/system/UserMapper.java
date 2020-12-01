package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.system.User;
import io.pandora.mall.pojo.vo.system.UserInfo;
import org.apache.ibatis.annotations.Select;

/**
 * @author Created by mr_zhou on 2020/12/1
 */
public interface UserMapper extends BaseDao<User> {
    int insertSelective(User record);

    @Select("SELECT * FROM mall_sys_user WHERE account = #{account}")
    User findUserByAccount(String account);

    UserInfo findUserInfoByAccount(String account);
}
