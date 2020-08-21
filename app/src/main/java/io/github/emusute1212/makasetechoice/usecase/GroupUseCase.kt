package io.github.emusute1212.makasetechoice.usecase

import io.github.emusute1212.makasetechoice.data.entity.Member
import javax.inject.Inject

class GroupUseCase @Inject constructor() {
    fun choiceGroup(members: List<Member>, splitNum: Int): Map<String, List<Member>> {
        return members
            .shuffled()
            .withIndex()
            .groupBy(
                {
                    "グループ${(it.index % splitNum) + 1}"
                }, {
                    it.value
                }
            )
    }
}