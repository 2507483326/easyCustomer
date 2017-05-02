package blog.net.serviceImpl;

import blog.db.dao.UserDao;
import blog.service.dto.UserExcution;
import blog.service.model.User;
import blog.service.service.RuleService;
import blog.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Epat on 2017/2/10.
 */
@Service
public class UserServiceImpl implements UserService{

    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    UserDao userDao;

    @Autowired
    RuleService ruleService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<User> all() {
        return userDao.all();
    }

    public String getSalt(String loginName) {
        return userDao.getSalt(loginName);
    }

    public void setUid(String loginName, String uuid) {
        userDao.setUid(loginName,uuid);
    }

    public User getUserByUid(String uuid) {
        return userDao.getUserByUid(uuid);
    }

    public User Login(String loginName) {
        return userDao.selectByLogin(loginName);
    }

    public void register(User user) {
        userDao.insert(user);
    }

    @Override
    public User getUserByEmail(String email) {return userDao.selectByEmail(email);}

    @Override
    public UserExcution getUser(User user, Boolean isRemember) {
        UserExcution userExcution = new UserExcution(user.getId(),user.getState(),user.getAvatar(),user.getLoginName(),user.getNickName(),user.getRegCity(),user.getRegIp(),user.getRegProvince(),user.getRegTime(),isRemember);
        return userExcution;
    }
}
