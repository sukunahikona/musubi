package jp.musubi.service

import jp.musubi.mapper.MsbUserMapper
import jp.musubi.model.MsbUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MsbUserService {

    @Autowired
    lateinit var msbUserMapper: MsbUserMapper
    fun findAll(): List<MsbUser> {
        return msbUserMapper.findAll()
    }

    fun findById(id:String): MsbUser? {
        return msbUserMapper.findById(id)
    }
}