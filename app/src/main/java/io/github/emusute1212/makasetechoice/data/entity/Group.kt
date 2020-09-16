package io.github.emusute1212.makasetechoice.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "groups",
    foreignKeys = [ForeignKey(
        entity = Member::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("member_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Group(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "member_id")
    val memberId: Int
)