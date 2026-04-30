package com.security;

import org.springframework.security.core.userdetails.User;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.
	        csrf( csrf -> csrf.disable() ). //CSRF se deshabilita porque no uso sesiones ni cookies, sino autenticación stateless
			cors( cors -> cors.configurationSource( corsConfig() ) ).
			headers( headers -> headers.frameOptions( frame -> frame.disable() ) ).
			authorizeHttpRequests( auth -> auth
					.requestMatchers("/public/**", "/swagger-ui/**" ).permitAll()
					.requestMatchers("/orders/**").authenticated()
					.anyRequest().permitAll()
							).
			 httpBasic(Customizer.withDefaults());

	        return http.build();
	    }
	 
	 
	 
		@Bean
		InMemoryUserDetailsManager inmemoUsers()
		{
			//usuarios perzonalizados
			UserDetails admin = User.withUsername("admin")
					                .password( encoder().encode("adminmgm"))
//					                .authorities("ADMIN")
					                .roles("ADMIN")
					                .build();
			
			UserDetails user = User.withUsername("mgm@mgm.com")
					               .password( encoder().encode("test"))
//					               .authorities("USER")
					               .roles("ADMIN")
					               .build();
			
			
			return new InMemoryUserDetailsManager(admin,user);
			
		}
		
		
		// contraseñas sin encoder
		@Bean
		PasswordEncoder encoder()
		{
//			return NoOpPasswordEncoder.getInstance();
			return new BCryptPasswordEncoder();
		}
		
		
		@Bean
	    CorsConfigurationSource corsConfig()
	    {
	        var config = new CorsConfiguration();

	        config.setAllowedOrigins(List.of( "http://localhost:8081","http://my-app.com"));
	        //config.setAllowedOrigins(List.of("*")); // deja pasar todos
	        //config.setAllowedMethods(List.of("GET","POST")); //cualquiera que quisieramos
	        config.setAllowedMethods(List.of("*"));
	        config.setAllowedHeaders(List.of("*"));

	        var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();

	        source.registerCorsConfiguration("/**",config); //todos los paths protegidos

	        return source;

	    }

		
		

}
