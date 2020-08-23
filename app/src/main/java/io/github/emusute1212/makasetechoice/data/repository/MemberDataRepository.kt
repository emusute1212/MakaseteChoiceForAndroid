package io.github.emusute1212.makasetechoice.data.repository

import io.github.emusute1212.makasetechoice.data.entity.Member
import javax.inject.Inject

class MemberDataRepository @Inject constructor() {
    // TODO This is mock
    private val cacheMembers = mutableListOf(Member(1, "aiueo"), Member(2, "kakikukeko"))

    fun loadMembers(): List<Member> {
        return cacheMembers
    }

    fun addMember(name: String) {
        cacheMembers += Member(cacheMembers.maxBy { it.id }?.id?.plus(1) ?: 1, name)
    }
}