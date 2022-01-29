package com.example.trabalhofinaldaw2.Service.Security;

import com.example.trabalhofinaldaw2.Repository.Usuario.UsuarioRepository;
import com.example.trabalhofinaldaw2.Service.TokenService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityService extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //COnfigurações de autenticações
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configurações de Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.PUT,"/api/leilao").hasRole("ADM")
                .antMatchers(HttpMethod.POST,"/api/leilao").hasRole("ADM")
                .antMatchers(HttpMethod.DELETE,"/api/leilao/*").hasRole("ADM")
                .antMatchers(HttpMethod.GET,"/api/leilao").permitAll()
                .antMatchers(HttpMethod.GET,"/api/leilao/*").permitAll()

                .antMatchers(HttpMethod.POST,"/api/usuario/validarLogin").permitAll()
                .antMatchers(HttpMethod.POST,"/api/usuario").permitAll()

                .antMatchers(HttpMethod.GET,"/api/lance").permitAll()
                .antMatchers(HttpMethod.POST,"/api/lance").permitAll()
                .antMatchers(HttpMethod.GET,"/api/lance/*").permitAll()

                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class).cors();
    }

    //Configurações de recursos estaticos(js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
