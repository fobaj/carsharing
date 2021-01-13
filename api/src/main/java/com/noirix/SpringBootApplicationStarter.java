package com.noirix;

import com.noirix.config.SwaggerConfiguration;
import com.noirix.config.PersistenceContextBeansConfiguration;
import com.noirix.security.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.noirix")
@EnableSwagger2
@Import({
        WebSecurityConfiguration.class,
        SwaggerConfiguration.class,
        PersistenceContextBeansConfiguration.class})
public class SpringBootApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStarter.class, args);
    }
}
