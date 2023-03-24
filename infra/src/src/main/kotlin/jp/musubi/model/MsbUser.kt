package jp.musubi.model

data class MsbUser (
    var id: String,
    var name: String?,
    var password: String?,
    var role: Int?
)