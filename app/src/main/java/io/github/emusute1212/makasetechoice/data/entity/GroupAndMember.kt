package io.github.emusute1212.makasetechoice.data.entity

import androidx.room.Embedded
import androidx.room.Relation

class GroupAndMember {
    @Embedded
    lateinit var group: Group

    @Relation(parentColumn = "member_id", entityColumn = "id")
    lateinit var members: List<Member>
}