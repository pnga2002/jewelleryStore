package fpoly.edu.jewelleryStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/admin/**").hasAnyAuthority("ROLE_ADMIN")
//                        .requestMatchers("/api/v1/auth/**").permitAll()
//                        .requestMatchers("/api/v1/user/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
//                        .requestMatchers("/api/v1/products/**").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll()
//                        .requestMatchers("/api/v1/**").authenticated()
//                        .requestMatchers("/**").permitAll()
//                        .anyRequest()
//                        .authenticated()
//                )
//                .addFilterBefore(jwtAuthenrizationFilter, UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests((requests) -> requests
	                .anyRequest().permitAll() // Allow all requests without authentication
	            )
	            .csrf().disable(); // Disable CSRF for testing purposes
	        
	        return http.build();
	    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//             User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
