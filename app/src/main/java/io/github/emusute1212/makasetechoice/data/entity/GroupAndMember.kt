package io.github.emusute1212.makasetechoice.data.entity

import androidx.room.Embedded
import androidx.room.Relation

class GroupAndMember {
    @Embedded
    lateinit var group: Group

    @Relation(parentColumn = "id", entityColumn = "member_id")
    lateinit var members: List<Member>
}