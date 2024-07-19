package io.github.emusute1212.makasetechoice.ext

import io.github.emusute1212.makasetechoice.data.entity.Group
import io.github.emusute1212.makasetechoice.data.entity.GroupAndMember
import io.github.emusute1212.makasetechoice.data.entity.Member

fun List<GroupAndMember>.toMap(): Map<String, List<Member>> {
    return groupBy(
        keySelector = {
            it.group.name
        },
        valueTransform = {
            it.member
        }
    )
}

fun Map<String, List<Member>>.toGroups(): List<Group> {
    return flatMap { group ->
        group.value.map {
            Group(0, group.key, it.id)
        }
    }
}