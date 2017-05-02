package blog.net.serviceImpl;

import blog.db.dao.RuleDao;
import blog.service.model.Rule;
import blog.service.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Epat on 2017/3/12.
 */
@Service
public class RuleServiceImpl implements RuleService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    RuleDao ruleDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Rule> all() {
        return ruleDao.all();
    }

}
