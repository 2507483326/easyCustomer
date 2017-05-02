package blog.net.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by Epat on 2017/5/2.
 */

@Configuration
public class TaskConfig {

    // 创建一个线程池
    @Bean
    public ThreadPoolTaskExecutor createThreadPoolTaskExecutor () {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        return threadPoolTaskExecutor;
    }

}
