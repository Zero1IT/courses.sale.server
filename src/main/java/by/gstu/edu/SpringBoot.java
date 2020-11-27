package by.gstu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * createdAt: 11/12/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@SpringBootApplication(exclude = {
        //DataSourceAutoConfiguration.class,
        //DataSourceTransactionManagerAutoConfiguration.class,
        //HibernateJpaAutoConfiguration.class,
        WebSocketServletAutoConfiguration.class,
        TaskExecutionAutoConfiguration.class,
        AopAutoConfiguration.class,
        TaskSchedulingAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class,
        RestTemplateAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        JmxAutoConfiguration.class,
        SpringApplicationAdminJmxAutoConfiguration.class,
        //SecurityAutoConfiguration.class
})
@EnableJpaRepositories(basePackages = "by.gstu.edu.repository")
@EntityScan(basePackages = "by.gstu.edu.model")
@PropertySource("classpath:credentials.secure.properties")
public class SpringBoot {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(SpringBoot.class, args);
        System.out.println(context.getBeanDefinitionCount());
    }
}
