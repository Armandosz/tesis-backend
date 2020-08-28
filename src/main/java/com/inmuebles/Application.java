package com.inmuebles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String password = "yawinpassword";
	String encodedPassword = passwordEncoder.encode(password);
	System.out.println();
	System.out.println("Password is         : " + password);
	System.out.println("Encoded Password is : " + encodedPassword);
	System.out.println();

	boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
	System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);

	password = "yawin";
	isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
	System.out.println("Password : " + password + "           isPasswordMatch    : " + isPasswordMatch);
	
	/*
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").permitAll()
				.anyRequest().authenticated();
		}
	}
	*/
	}
}
