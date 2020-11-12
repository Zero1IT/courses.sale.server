package by.gstu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

/**
 * createdAt: 11/12/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@SpringBootApplication
@PropertySource("classpath:data_source.properties")
public class SpringBoot {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(SpringBoot.class, args);
        System.out.println(context.getBeanDefinitionCount());
    }
}
