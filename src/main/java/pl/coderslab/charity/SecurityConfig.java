package pl.coderslab.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.coderslab.charity.services.SpringDataUserDetailsService;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.name FROM users u INNER JOIN users_roles ur ON u.id = ur.user_id INNER JOIN role r on ur.role_id = r.id WHERE u.username=?");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/donation/*").authenticated()
                .antMatchers("/user/*").authenticated()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .and().formLogin().permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/logged")
                .and().logout().permitAll().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/login");

    }
}
