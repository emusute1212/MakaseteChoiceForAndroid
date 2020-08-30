package io.github.emusute1212.makasetechoice.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "groups",
    foreignKeys = [ForeignKey(
        entity = Member::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Group(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "member_id")
    val memberId: String
)