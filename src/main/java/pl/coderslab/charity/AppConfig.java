package pl.coderslab.charity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="pl.coderslab.charity.repositories")
@ComponentScan("pl.coderslab.charity")
public class AppConfig implements WebMvcConfigurer {
}
