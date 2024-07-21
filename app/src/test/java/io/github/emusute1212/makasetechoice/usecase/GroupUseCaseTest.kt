package io.github.emusute1212.makasetechoice.usecase

import com.google.common.truth.Truth
import io.github.emusute1212.makasetechoice.data.entity.Group
import io.github.emusute1212.makasetechoice.data.entity.GroupAndMember
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.data.repository.GroupDataRepository
import io.mockk.every
import io.mockk.spyk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GroupUseCaseTest {
    @Test
    fun loadGroupsTest() = runTest {
        val repository = GroupDataRepositoryForTest()
        val useCase = GroupUseCase(
            repository = repository
        )
        repository.saveGroups(DUMMY_DATA)

        val result = useCase.loadGroups().first()
        val actual = DUMMY_DATA

        Truth.assertThat(actual).isEqualTo(result)
    }

    @Test
    fun choice1GroupsTest() = runTest {
        val repository = GroupDataRepositoryForTest()
        val useCase = spyk(
            GroupUseCase(
                repository = repository
            ),
            recordPrivateCalls = true,
        ) {
            // 冪等性を担保するためにシャッフルせずにそのまま帰すようにする
            every {
                shuffledMember(TARGET_MEMBERS)
            } returns TARGET_MEMBERS
        }

        useCase.choiceGroup(TARGET_MEMBERS, 1)
        val result = useCase.loadGroups().first()
        val actual = mapOf(
            "グループ1" to TARGET_MEMBERS
        )

        Truth.assertThat(actual).isEqualTo(result)
    }

    @Test
    fun choice2GroupsTest() = runTest {
        val repository = GroupDataRepositoryForTest()
        val useCase = spyk(
            GroupUseCase(
                repository = repository
            ),
            recordPrivateCalls = true,
        ) {
            // 冪等性を担保するためにシャッフルせずにそのまま帰すようにする
            every {
                shuffledMember(TARGET_MEMBERS)
            } returns TARGET_MEMBERS
        }

        useCase.choiceGroup(TARGET_MEMBERS, 2)
        val result = useCase.loadGroups().first()
        val actual = mapOf(
            "グループ1" to TARGET_MEMBERS.filter {
                it.id in listOf(1, 3, 5)
            },
            "グループ2" to TARGET_MEMBERS.filter {
                it.id in listOf(2, 4, 6)
            },
        )

        Truth.assertThat(actual).isEqualTo(result)
    }

    @Test
    fun choice4GroupsTest() = runTest {
        val repository = GroupDataRepositoryForTest()
        val useCase = spyk(
            GroupUseCase(
                repository = repository
            ),
            recordPrivateCalls = true,
        ) {
            // 冪等性を担保するためにシャッフルせずにそのまま帰すようにする
            every {
                shuffledMember(TARGET_MEMBERS)
            } returns TARGET_MEMBERS
        }

        useCase.choiceGroup(TARGET_MEMBERS, 4)
        val result = useCase.loadGroups().first()
        val actual = mapOf(
            "グループ1" to TARGET_MEMBERS.filter {
                it.id in listOf(1, 5)
            },
            "グループ2" to TARGET_MEMBERS.filter {
                it.id in listOf(2, 6)
            },
            "グループ3" to TARGET_MEMBERS.filter {
                it.id in listOf(3)
            },
            "グループ4" to TARGET_MEMBERS.filter {
                it.id in listOf(4)
            },
        )

        Truth.assertThat(actual).isEqualTo(result)
    }

    @Test
    fun choice0GroupsTest() = runTest {
        val repository = GroupDataRepositoryForTest()
        val useCase = spyk(
            GroupUseCase(
                repository = repository
            ),
            recordPrivateCalls = true,
        ) {
            // 冪等性を担保するためにシャッフルせずにそのまま帰すようにする
            every {
                shuffledMember(TARGET_MEMBERS)
            } returns TARGET_MEMBERS
        }

        try {
            useCase.choiceGroup(TARGET_MEMBERS, 0)
        } catch (e: IllegalArgumentException) {
            Truth.assertThat(IllegalArgumentException()).isInstanceOf(e::class.java)
        }
    }

    @Test
    fun choiceOverMemberSizeGroupTest() = runTest {
        val repository = GroupDataRepositoryForTest()
        val useCase = spyk(
            GroupUseCase(
                repository = repository
            ),
            recordPrivateCalls = true,
        ) {
            // 冪等性を担保するためにシャッフルせずにそのまま帰すようにする
            every {
                shuffledMember(TARGET_MEMBERS)
            } returns TARGET_MEMBERS
        }

        try {
            useCase.choiceGroup(TARGET_MEMBERS, TARGET_MEMBERS.size + 1)
        } catch (e: IllegalArgumentException) {
            Truth.assertThat(IllegalArgumentException()).isInstanceOf(e::class.java)
        }
    }

    private class GroupDataRepositoryForTest : GroupDataRepository {
        private val groupsFlow = MutableStateFlow(emptyList<GroupAndMember>())

        override fun loadGroups(): Flow<List<GroupAndMember>> {
            return groupsFlow
        }

        override fun saveGroups(groups: Map<String, List<Member>>) {
            groupsFlow.value = groups.toGroupAndMember()
        }

        private fun Map<String, List<Member>>.toGroupAndMember(): List<GroupAndMember> {
            return flatMap { group ->
                group.value.map {
                    group.key to it
                }
            }.mapIndexed { index, (groupName, member) ->
                GroupAndMember().apply {
                    group = Group(
                        id = index,
                        name = groupName,
                        memberId = member.id,
                    )
                    this.member = member
                }
            }
        }
    }

    companion object {
        private val DUMMY_DATA = mapOf(
            "グループ1" to listOf(
                Member(
                    id = 1,
                    name = "HOGE_HOGE",
                ),
                Member(
                    id = 2,
                    name = "FUGA_FUGA",
                ),
            ),
            "グループ2" to listOf(
                Member(
                    id = 3,
                    name = "TEST_TEST",
                ),
            )
        )

        private val TARGET_MEMBERS = listOf(
            Member(
                id = 1,
                name = "member_1",
            ),
            Member(
                id = 2,
                name = "member_2",
            ),
            Member(
                id = 3,
                name = "member_3",
            ),
            Member(
                id = 4,
                name = "member_4",
            ),
            Member(
                id = 5,
                name = "member_5",
            ),
            Member(
                id = 6,
                name = "member_6",
            ),
        )
    }
}