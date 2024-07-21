package io.github.emusute1212.makasetechoice.data.repository

import androidx.annotation.WorkerThread
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import io.github.emusute1212.makasetechoice.data.db.MakaseteChoiceDatabase
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.ext.toMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GroupDataRepositoryTest {
    @Test
    fun saveGroupsDataTest() = runTest {
        val database = createDatabase()
        kotlin.AutoCloseable {
            database.close()
        }.use {
            withContext(Dispatchers.IO) {
                database.setDummyData()
            }
            val repository = GroupDataRepositoryImpl(
                database = database
            )

            val groups = mapOf(
                "グループ1" to listOf(
                    DUMMY_MEMBERS[0],
                    DUMMY_MEMBERS[1],
                ),
                "グループ2" to listOf(
                    DUMMY_MEMBERS[2],
                ),
            )
            withContext(Dispatchers.IO) {
                repository.saveGroups(groups)
            }
            val result = withContext(Dispatchers.IO) {
                repository.loadGroups().first().toMap()
            }

            // GroupAndMemberで比較すると Deep Comparison が面倒なのでgroupsで比較する
            Truth.assertThat(groups).isEqualTo(result)
        }
    }

    @Test
    fun saveGroups2TimesDataTest() = runTest {
        val database = createDatabase()
        kotlin.AutoCloseable {
            database.close()
        }.use {
            withContext(Dispatchers.IO) {
                database.setDummyData()
            }
            val repository = GroupDataRepositoryImpl(
                database = database
            )

            val firstTimeGroups = mapOf(
                "グループ1" to listOf(
                    DUMMY_MEMBERS[0],
                    DUMMY_MEMBERS[1],
                ),
                "グループ2" to listOf(
                    DUMMY_MEMBERS[2],
                ),
            )
            withContext(Dispatchers.IO) {
                repository.saveGroups(firstTimeGroups)
            }
            val firstTimeResult = withContext(Dispatchers.IO) {
                repository.loadGroups().first().toMap()
            }

            // まずは一回目の登録ができていることを確認
            Truth.assertThat(firstTimeGroups).isEqualTo(firstTimeResult)

            val secondTimeGroups = mapOf(
                "グループ1" to listOf(
                    DUMMY_MEMBERS[2],
                    DUMMY_MEMBERS[0],
                ),
                "グループ2" to listOf(
                    DUMMY_MEMBERS[1],
                ),
            )
            withContext(Dispatchers.IO) {
                repository.saveGroups(secondTimeGroups)
            }
            val secondTimeResult = withContext(Dispatchers.IO) {
                repository.loadGroups().first().toMap()
            }

            // 二回目のアップデートができていることを確認
            Truth.assertThat(secondTimeGroups).isEqualTo(secondTimeResult)
        }
    }

    private fun createDatabase(): MakaseteChoiceDatabase {
        return Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MakaseteChoiceDatabase::class.java,
        ).build()
    }

    @WorkerThread
    private fun MakaseteChoiceDatabase.setDummyData() {
        DUMMY_MEMBERS.forEach { member ->
            memberDao().insertMember(member)
        }
    }

    companion object {
        val DUMMY_MEMBERS = listOf(
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
        )
    }
}