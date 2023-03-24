package jp.musubi.security

import jp.musubi.model.MsbUser
import jp.musubi.service.MsbUserService
import jp.musubi.util.MsbException
import jp.musubi.util.MsbRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
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
        // ユーザ情報の初期化

        val userId:String = username ?: throw MsbException("user ID is null")
        val password:String = authentication?.credentials.toString()

        // ユーザ情報検索
        val user:MsbUser? = msbUserService.findById(userId)
        if (passwordEncoder.matches(password, user!!.password)) {
            // あれば認証通す
            // ROLE無指定ならデフォルトユーザへ
            val roles: List<String> = MsbRole.fromOrdinal(user.role ?: 1).roleList
            val authorities:List<GrantedAuthority> = AuthorityUtils.createAuthorityList(*roles.toTypedArray())
            return User
                    .withUsername(userId)
                    .password(password)
                    .authorities(authorities)
                    .build()
        }
        throw MsbException("User not found: $userId");
    }


}