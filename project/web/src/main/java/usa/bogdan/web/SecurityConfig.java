package usa.bogdan.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import usa.bogdan.web.Entities.UserEntity;
import usa.bogdan.web.Repositories.UserRepository;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll() // Разрешите доступ к H2 Console
                        .requestMatchers("/register", "/login").permitAll()
                        .requestMatchers("/todo").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/todo")
                        .failureHandler((request, response, exception) -> {
                            String errorMessage = exception.getMessage().contains("Bad credentials") ?
                                    "Неправильный пароль" : "Нет такого имени";
                            request.getSession().setAttribute("error", errorMessage);
                            response.sendRedirect("/login");
                        })
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Отключите CSRF для H2 Console
                )
                .headers(headers -> headers
                        .frameOptions().sameOrigin() // Разрешите отображение H2 Console
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            UserEntity user = userRepository.findByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("You provide to us an incorrect data");
            }
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Collections.emptyList());
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}