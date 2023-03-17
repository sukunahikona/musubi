package jp.musubi.security

import jp.musubi.mapper.MsbUserMapper
import jp.musubi.model.MsbUser
import jp.musubi.service.MsbUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

open class MsbAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var msbUserService: MsbUserService

    // <<< N/A >>>
    override fun additionalAuthenticationChecks(userDetails: UserDetails?, authentication: UsernamePasswordAuthenticationToken?) {}
    //---

    @Transactional
    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails? {
        val userId:String? = username
        val password:String = authentication?.credentials.toString()
        val authorities:List<GrantedAuthority> = AuthorityUtils.createAuthorityList("ROLE_DEFAULT")
        //val users:List<MsbUser> = msbUserService.findAll()
        if (userId == null) {
            throw UsernameNotFoundException("User id is null");
        }

        val user:MsbUser? = msbUserService.findById(userId)
        if (user != null && passwordEncoder.matches(password, user.password)) {
            return User
                    .withUsername(userId)
                    .password(password)
                    .authorities(authorities)
                    .build()
        }
        throw UsernameNotFoundException("User not found: $userId");
    }


}