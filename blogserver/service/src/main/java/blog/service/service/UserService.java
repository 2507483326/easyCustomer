package blog.service.service;

import blog.service.dto.Excution;
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

    Excution Login (User  user);

    User getUserByEmail (String email);

    User getUserByLoginName (String loginName);

    UserExcution getUser (User user, Boolean isRemember);

    Excution rememberLogin ();

    Boolean isExistEmail (String data);

    Excution getValidateCode (String email);

    Excution register (User user, String validateCode, String ipAddress, Integer hashIterations);

}
