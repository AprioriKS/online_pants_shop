package com.example.online_pants_shop.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	@Value("${USER_NAME_DEBUG}")
	private String userName;

	@Value("${USER_PASSWORD_DEBUG}")
	private String userPassword;



	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.builder() //withDefaultPasswordEncoder()
				.username(userName)
				.password(passwordEncoder.encode(userPassword))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)  // Вимикаємо CSRF
				.cors(AbstractHttpConfigurer::disable)  // Вимикаємо CORS
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/")
						.permitAll()
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Stateless режим
				.build();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
