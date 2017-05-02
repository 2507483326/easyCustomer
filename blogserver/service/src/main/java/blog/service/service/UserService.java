package blog.service.service;

import blog.service.dto.UserExcution;
import blog.service.model.User;

import java.util.List;

/**
 * Created by Epat on 2017/2/10.
 */
public interface UserService {

    List<User> all();

    String getSalt(String loginName);

    void setUid (String loginName, String uuid);

    User getUserByUid (String uuid);

    User Login (String loginName);

    User getUserByEmail (String email);

    void  register (User user);

    UserExcution getUser (User user, Boolean isRemember);
}
