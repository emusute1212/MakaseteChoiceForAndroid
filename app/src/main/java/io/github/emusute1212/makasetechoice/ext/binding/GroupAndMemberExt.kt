package io.github.emusute1212.makasetechoice.ext.binding

import io.github.emusute1212.makasetechoice.data.entity.GroupAndMember
import io.github.emusute1212.makasetechoice.data.entity.Member

fun List<GroupAndMember>.toMap(): Map<String, List<Member>> {
    return associateBy(
        keySelector = {
            it.group.name
        },
        valueTransform = {
            it.members
        }
    )
}