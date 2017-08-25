package nl.leonjoosse.anniversaryreminder.reminder

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class AnniversaryControllerTest {

    @Mock
    private lateinit var service: AnniversaryService
    private lateinit var controller: AnniversaryController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        controller = AnniversaryController(service)
    }

    @Test
    fun callToFindItemByIdShouldDeliverCallToService() {
        controller.getById("id")
        verify(service).findById("id")
    }

    @Test
    fun callToListAllAnniversariesShouldDeliverCallToService() {
        controller.list()
        verify(service).listAll()
    }

    @Test
    fun callToCreateAnniversaryShouldDeliverCallToService() {
        val anniversary = Anniversary.Empty
        controller.create(anniversary)
        verify(service).create(anniversary)
    }

    @Test
    fun callToUpdateAnniversaryShouldDeliverCallToService() {
        val anniversary = Anniversary.Empty
        controller.update(anniversary)
        verify(service).update(anniversary)
    }

    @Test
    fun callToDeleteAnniversaryShouldDeliverCallToService() {
        controller.delete("id")
        verify(service).delete("id")
    }
}