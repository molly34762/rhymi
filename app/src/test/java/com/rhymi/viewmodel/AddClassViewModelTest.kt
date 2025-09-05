import com.rhymi.service.repository.DanceClassRepository
import com.rhymi.service.room.ClassEntity
import com.rhymi.viewmodel.AddClassViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class AddClassViewModelTest {

    private lateinit var repository: DanceClassRepository
    private lateinit var viewModel: AddClassViewModel

    @Before
    fun setup() {
        repository = mock(DanceClassRepository::class.java)
        viewModel = AddClassViewModel(repository)
    }

    @Test
    fun `saveDanceClass calls repository`() = runTest {
        val entity = ClassEntity(
            id = 1,
            style = "Hip Hop",
            teacherName = "Chris",
            songName = "Song A",
            date = 1L,
            videoUrl = "",
            notes = "Fun class"
        )

        viewModel.saveDanceClass(entity)

        // Wait for coroutine to complete
        this.testScheduler.advanceUntilIdle()

        verify(repository).insertClass(entity)
    }
}
