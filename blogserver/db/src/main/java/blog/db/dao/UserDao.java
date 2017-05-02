package blog.db.dao;

import blog.service.model.User;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * Created by Epat on 2017/2/10.
 */
public interface UserDao extends BaseMapper<User> {

    @SqlStatement(params = "loginName")
    String getSalt(String loginName);

    //设置uuid
    @SqlStatement(params = "loginName,uuid")
    void setUid(String loginName, String uuid);

    //根据uuid获取user
    @SqlStatement(params = "uuid")
    User getUserByUid(String uuid);

    //根据用户名密码获取User
    @SqlStatement(params = "loginName")
    User selectByLogin(String loginName);

    //根据用户名密码获取User
    @SqlStatement(params = "email")
    User selectByEmail(String email);

}
