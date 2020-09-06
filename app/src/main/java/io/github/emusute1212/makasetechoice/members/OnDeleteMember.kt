package io.github.emusute1212.makasetechoice.members

import io.github.emusute1212.makasetechoice.data.entity.Member

interface OnDeleteMember {
    fun delete(member: Member)
}