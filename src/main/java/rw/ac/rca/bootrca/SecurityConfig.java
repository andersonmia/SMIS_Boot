package rw.ac.rca.bootrca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/user/signup", "/api/user/login").permitAll()
                        .requestMatchers("/api/user/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "PARENT")

                        .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "STUDENT")

                        .requestMatchers("/api/instructor/**").hasAnyRole("ADMIN", "TEACHER")

                        .requestMatchers("/api/marks/listMarks/student/{student_id}").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "PARENT")
                        .requestMatchers("/api/marks/listMarks/course/{course_id}").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/api/marks/**").hasAnyRole("ADMIN", "TEACHER")

                        .requestMatchers("/api/parent/**").hasAnyRole("ADMIN", "PARENT")

                        .requestMatchers("/api/course/**").hasAnyRole("ADMIN", "TEACHER")

                        .requestMatchers("/api/address/**","/api/admin/*").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}