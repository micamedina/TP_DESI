package tuti.desi.presentacion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tuti.desi.servicios.UserDetailsServiceImpl;

/**
 * Aquí configuro las restricciones de acceso 
 * @author dardo
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	 @Autowired
	 UserDetailsServiceImpl userDetailsService;

	 /**
	  * Le digo a Spring Security con qué algoritmo encriptar las pass
	  * @return
	  */
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
    	//No usar NoOpPasswordEncoder  en produccion ya que no estamos encriptando password. Usar BCryptPasswordEncoder o algun otro algoritmo
    	return NoOpPasswordEncoder.getInstance();
	}


   
    /**
     * Restringe acceso a recursos
     * @param http
     * @return configuracion de acceso
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
            	.requestMatchers("/personasEditar").hasAuthority("ADMIN") 
            	.anyRequest().authenticated()
            )
//            .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
//            .rememberMe(org.springframework.security.config.Customizer.withDefaults());
            .formLogin();
            

        return http.build();
    }
    
    
    

}
