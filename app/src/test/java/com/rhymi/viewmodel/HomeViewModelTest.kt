import com.rhymi.service.repository.DanceClassRepository
import com.rhymi.service.room.ClassEntity
import com.rhymi.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class HomeViewModelTest {

    private lateinit var repository: DanceClassRepository
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        // Create the mock repository
        repository = mock(DanceClassRepository::class.java)
    }

    @Test
    fun `classes emits repository values`() = runTest {
        val testData = listOf(
            ClassEntity(1, 1L, "Hip Hop", "Chris", "Song A", "", ""),
            ClassEntity(2, 1L, "Ballet", "Chelsey", "Song B", "", "")
        )

        // Stub repository to return a valid Flow BEFORE creating ViewModel
        whenever(repository.getAllClasses()).thenReturn(MutableStateFlow(testData))

        // Create ViewModel after stubbing
        viewModel = HomeViewModel(repository)

        // Collect first emission from the Flow
        val result = viewModel.classes.first()

        assertEquals(testData, result)
    }
}
