package jp.musubi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {

        // 静的リソースアクセスは許容
        http.authorizeHttpRequests().requestMatchers("/login", "/logout", "/auth/*", "/webjars/**", "/js/**", "/health/**").permitAll().anyRequest().authenticated()
        // csrf非活性
        //http.csrf().disable()

        // ログイン各種の設定
        http
            .formLogin()
            .loginPage("/auth/login")
            .loginProcessingUrl("/login")
            .failureUrl("/auth/login?error=true")
            .defaultSuccessUrl("/dashboard/top", true)
                .usernameParameter("userid")
                .passwordParameter("password")
        // ログアウト各種設定
        http.logout().logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/auth/login")

        // セッション設定
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().newSession()
                .maximumSessions(1)


        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider? {
        return MsbAuthenticationProvider()
    }
}