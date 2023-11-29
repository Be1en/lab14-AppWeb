package com.miempresa.sprinjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.miempresa.sprinjpa.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion {
    @Autowired
    private UsuarioServicio userDet;

    @Autowired
    private BCryptPasswordEncoder bycryp;

    @Bean
    public BCryptPasswordEncoder passEncoder(){
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        return bcpe;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDet).passwordEncoder(bycryp);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            (authz) -> authz
            .requestMatchers("/", "/listarEmpleados").permitAll()
            .requestMatchers("/agregarEmpleado").hasRole("ADMIN")
            .requestMatchers("/mostrarEmpleado").hasRole("ADMIN")
            .requestMatchers("/guardarEmpleado").hasRole("ADMIN")
            .requestMatchers("/editarEmpleado").hasRole("ADMIN")
            .requestMatchers("/eliminarEmpleado/*").hasRole("ADMIN")
            
            .requestMatchers("/agregarTarea").hasRole("ADMIN")
            .requestMatchers("/mostrarTarea").hasRole("ADMIN")
            .requestMatchers("/guardarTarea").hasRole("ADMIN")
            .requestMatchers("/editarTarea").hasRole("ADMIN")
            .requestMatchers("/eliminarTarea/*").hasRole("ADMIN")
            .anyRequest().authenticated())
            .formLogin((form) -> form.permitAll().loginPage("/login"))
            .logout((logout) -> logout.permitAll())
            .exceptionHandling((exceptionHandling) -> exceptionHandling
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        // Redirigir a la página de error según la operación solicitada
                        String errorPage = getErrorPage(request.getServletPath());
                        response.sendRedirect(errorPage);
                    })
                );
            
        return http.build();
        
    }
    private String getErrorPage(String servletPath) {
        switch (servletPath) {
            case "/agregarEmpleado":
                return "/error-agregar-empleado";
            case "/mostrarEmpleado":
                return "/error-mostrar-empleado";
            case "/agregarTarea":
                return "/error-agregar-tarea";
            case "/mostrarTarea":
                return "/error-mostrar-tarea";
            default:
                if (servletPath.startsWith("/eliminarEmpleado/")) {
                    return "/error-eliminar-empleado";
                } 
                else if(servletPath.startsWith("/eliminarTarea/")){
                    return "/error-eliminar-tarea";
                }
                else {
                    return "/error";
                }
        }
    }
};
    

