package com.mpmsg.config

import com.mpmsg.enums.Roles
import com.mpmsg.repository.UserRepository
import com.mpmsg.security.AuthenticationFilter
import com.mpmsg.security.AuthorizationFilter
import com.mpmsg.security.JwtUtil
import com.mpmsg.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val userRepository: UserRepository,
    private val configuration: AuthenticationConfiguration,
    private val userDetails: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
        ) {

    private val PUBLIC_POST_MATCHER = arrayOf(
        "/user"
    )

    private val ADMIN_MATCHERS = arrayOf(
        "/message/**"
    )

    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and().csrf().disable()
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST,  *PUBLIC_POST_MATCHER).permitAll()
            .antMatchers(*ADMIN_MATCHERS).hasAuthority(Roles.ADMIN.description)
            .anyRequest().authenticated()
        http.addFilter(AuthenticationFilter(configuration.authenticationManager, userRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(configuration.authenticationManager,userDetails, jwtUtil ))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder (): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}