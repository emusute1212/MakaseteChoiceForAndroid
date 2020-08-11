package io.github.emusute1212.makasetechoice.data.repository

import io.github.emusute1212.makasetechoice.data.entity.Member
import javax.inject.Inject

class MemberDataRepository @Inject constructor() {
    private val cacheMembers = listOf(Member(1, "aiueo"), Member(2, "kakikukeko"))

    fun loadMembers(): List<Member> {
        return cacheMembers
    }
}