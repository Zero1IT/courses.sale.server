package by.gstu.courses;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * createdAt: 11/12/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@SpringBootApplication(exclude = {
        WebSocketServletAutoConfiguration.class,
        TaskExecutionAutoConfiguration.class,
        AopAutoConfiguration.class,
        //TaskSchedulingAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class,
        RestTemplateAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        JmxAutoConfiguration.class,
        SpringApplicationAdminJmxAutoConfiguration.class,
        SecurityAutoConfiguration.class
})
@EnableJpaRepositories(basePackages = "by.gstu.courses.repository")
@EntityScan(basePackages = "by.gstu.courses.domain")
@PropertySource("classpath:credentials.secure.properties")
public class SpringBoot {

    public static void main(String[] args) {
        System.setProperty("log4jdbc.drivers", "com.mysql.cj.jdbc.Driver");
        System.setProperty("log4jdbc.auto.load.popular.drivers", "false");
        ApplicationContext context = SpringApplication.run(SpringBoot.class, args);
        System.out.println(context.getBeanDefinitionCount());
    }

    @Bean
    @ConditionalOnProperty(value = "app.jpa.show-details-sql", havingValue = "true")
    public DataSource dataSource(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        int i = url.indexOf(":");
        dataSource.setUrl(url.substring(0, i) + ":log4jdbc" + url.substring(i));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
