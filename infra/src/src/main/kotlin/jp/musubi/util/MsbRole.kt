package jp.musubi.util

enum class MsbRole(val roleList: List<String>) {
    DEFAULT(listOf("ROLE_DEFAULT")),
    ADMIN(listOf("ROLE_DEFAULT","ROLE_ADMIN"));

    companion object {
        fun fromOrdinal(ordinal: Int) :MsbRole {
            return values().first { it.ordinal == ordinal}
        }
    }

}