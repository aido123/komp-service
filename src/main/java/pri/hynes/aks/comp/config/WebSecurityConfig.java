package pri.hynes.aks.comp.config;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.microsoft.azure.spring.autoconfigure.aad.AADAppRoleStatelessAuthenticationFilter;
import com.microsoft.azure.spring.autoconfigure.aad.AADAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AADAuthenticationFilter appRoleAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(appRoleAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors().and().authorizeRequests().antMatchers("/api/levels/*/**").authenticated().and().exceptionHandling().accessDeniedPage("/403");
        http.cors().and().authorizeRequests().antMatchers("/api/validate/**").authenticated().and().exceptionHandling().accessDeniedPage("/403");
        http.cors().and().authorizeRequests().antMatchers("/api/setup/**").authenticated().and().exceptionHandling().accessDeniedPage("/403");

    }
}