package jp.musubi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {

        http.authorizeHttpRequests().requestMatchers("/login", "/logout", "/auth/*", "/webjars/**").permitAll().anyRequest().authenticated()

        http.csrf().disable()

        http
            .formLogin()
            .loginPage("/auth/login")
            .loginProcessingUrl("/login")
            .failureUrl("/auth/login?error=true")
            .defaultSuccessUrl("/dashboard/top", true)
                .usernameParameter("username")
                .passwordParameter("password")

        http.logout().logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/auth/login")

        return http.build()
    }

//    @Bean
//    fun users(): UserDetailsService {
//        val user = User.builder()
//                .username("user")
//                .password(passwordEncoder()?.encode("password"))
//                .roles("USER")
//                .build()
//        return MsbUserDetailsService()
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider? {
        return MsbAuthenticationProvider()
    }
}