package io.github.emusute1212.makasetechoice.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import io.github.emusute1212.makasetechoice.data.db.MakaseteChoiceDatabase
import io.github.emusute1212.makasetechoice.data.entity.Member
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MemberDataRepositoryTest {
    @Test
    fun addMemberDataTest() = runTest {
        val database = createDatabase()
        kotlin.AutoCloseable {
            database.close()
        }.use {
            val repository = MemberDataRepositoryImpl(
                database = database
            )

            val memberName = "member_1"
            withContext(Dispatchers.IO) {
                repository.addMember(memberName)
            }
            val result = withContext(Dispatchers.IO) {
                repository.loadMembers().first()
            }
            val actual = listOf(
                Member(
                    id = 1,
                    name = memberName,
                )
            )

            Truth.assertThat(actual).isEqualTo(result)
        }
    }

    @Test
    fun deleteMemberDataTest() = runTest {
        val database = createDatabase()
        kotlin.AutoCloseable {
            database.close()
        }.use {
            val repository = MemberDataRepositoryImpl(
                database = database
            )

            val memberNames = listOf(
                "member_1",
                "remove_member_2",
                "member_3",
            )
            withContext(Dispatchers.IO) {
                memberNames.forEach {
                    repository.addMember(it)
                }
                val deleteMember = repository.loadMembers().first().find {
                    it.name == "remove_member_2"
                }
                repository.deleteMember(checkNotNull(deleteMember))
            }
            val result = withContext(Dispatchers.IO) {
                repository.loadMembers().first()
            }
            val actual = listOf(
                Member(
                    id = 1,
                    name = memberNames[0],
                ),
                Member(
                    id = 3,
                    name = memberNames[2],
                ),
            )

            Truth.assertThat(actual).isEqualTo(result)
        }
    }

    private fun createDatabase(): MakaseteChoiceDatabase {
        return Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MakaseteChoiceDatabase::class.java,
        ).build()
    }
}