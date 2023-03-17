package jp.musubi.mapper

import jp.musubi.model.MsbUser
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MsbUserMapper {
    fun findAll(): List<MsbUser>
    fun findById(id:String): MsbUser
}