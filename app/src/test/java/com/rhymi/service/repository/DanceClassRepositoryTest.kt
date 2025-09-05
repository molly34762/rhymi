import com.rhymi.service.repository.DanceClassRepository
import com.rhymi.service.room.ClassDao
import com.rhymi.service.room.ClassEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class DanceClassRepositoryTest {

    private lateinit var classDao: ClassDao
    private lateinit var repository: DanceClassRepository

    @Before
    fun setup() {
        classDao = mock()
        repository = DanceClassRepository(classDao)
    }

    @Test
    fun `getAllClasses returns Flow of class entities`() = runTest {
        val classes = listOf(
            ClassEntity(
                id = 1,
                date = System.currentTimeMillis(),
                style = "Hip Hop",
                teacherName = "Chris",
                songName = "Song A",
                videoUrl = "",
                notes = ""
            )
        )

        whenever(classDao.getAllClasses()).thenReturn(flowOf(classes))

        val result = repository.getAllClasses().first()
        assert(result == classes)
    }

    @Test
    fun `insertClass calls DAO insert`() = runTest {
        val exampleClass = ClassEntity(
            id = 1,
            date = System.currentTimeMillis(),
            style = "Hip Hop",
            teacherName = "Chris",
            songName = "Song A",
            videoUrl = "",
            notes = ""
        )

        repository.insertClass(exampleClass)

        verify(classDao).insertClass(exampleClass)
    }
}
