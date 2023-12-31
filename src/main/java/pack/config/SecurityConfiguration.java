package pack.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	private UserDetailsService userDetailsService;
   

	
	 @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws  Exception 
	  	  
	  {
			auth.userDetailsService(userDetailsService); 
	  }
	  
	  @Bean
	  public PasswordEncoder getPasswordEncoder() {
	  
	  return NoOpPasswordEncoder.getInstance(); }
	  
	  
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
			  
		  http.authorizeRequests()
		  .antMatchers("/pages/adminpage").hasRole("ADMIN")
		  .antMatchers("/pages/adminpage.jsp").hasRole("ADMIN")
        .antMatchers("/pages/userpage").hasAnyRole("USER","ADMIN")
        .antMatchers("/pages/userpage.jsp").hasAnyRole("USER","ADMIN")
  	  
        .and()
    	  .formLogin();
    	  
  	  http.logout(logout -> logout
  			  .logoutSuccessUrl("/pages/home.jsp")
  			  .invalidateHttpSession(true)
  			  
  			  
  			  );
  	  	  
			  
	  	  
	  }
	
	
	
	
	
	
	
	
	
	
}
