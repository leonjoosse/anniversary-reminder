package nl.leonjoosse.anniversaryreminder.anniversary

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.*

class AnniversaryServiceTest {

    @Mock
    private lateinit var repository: AnniversaryRepository
    private lateinit var service: AnniversaryService
    private val validAnniversary = createValidAnniversary()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = AnniversaryService(repository)
    }

    @Test
    fun callToFindItemByIdShouldDeliverCallToRepository() {
        service.findById("id")
        verify(repository).findOne("id")
    }

    @Test
    fun successSearchingItemByIdShouldReturnCorrectResult() {
        `when`(repository.findOne("id")).thenReturn(validAnniversary)
        val result = service.findById("id")
        assertEquals(validAnniversary, result.response)
        assertEquals("", result.error)
    }

    @Test
    fun failureSearchingItemByIdShouldReturnError() {
        `when`(repository.findOne("id")).thenReturn(null)
        val result = service.findById("id")
        assertEquals(Anniversary.Empty, result.response)
        assertEquals("error_item_not_found", result.error)
    }

    @Test
    fun callToLoadAllItemsShouldDeliverCallToRepository() {
        service.listAll()
        verify(repository).findAll()
    }

    @Test
    fun successFetchingAllItemsFromDatabaseShouldReturnCorrectResult() {
        val mockedData = listOf(createValidAnniversary())
        `when`(repository.findAll()).thenReturn(mockedData)
        val result = service.listAll()
        assertEquals(mockedData, result.response)
        assertEquals("", result.error)
    }

    @Test
    fun failureFetchingItemsFromDatabaseShouldReturnError() {
        `when`(repository.findAll()).thenReturn(null)
        val result = service.listAll()
        assertEquals(emptyList<Anniversary>(), result.response)
        assertEquals("error_loading_anniversaries", result.error)
    }

    @Test
    fun callToSaveAnniversaryShouldDeliverCallToRepository() {
        service.create(validAnniversary)
        verify(repository).save(validAnniversary)
    }

    @Test
    fun successSavingAnniversaryShouldReturnCorrectResult() {
        `when`(repository.save(validAnniversary)).thenReturn(validAnniversary)
        val result = service.create(validAnniversary)
        assertEquals(validAnniversary, result.response)
        assertEquals("", result.error)
    }

    @Test
    fun failureSavingAnniversaryShouldReturnError() {
        `when`(repository.save(validAnniversary)).thenReturn(null)
        val result = service.create(validAnniversary)
        assertEquals(Anniversary.Empty, result.response)
        assertEquals("error_saving_anniversary", result.error)
    }

    @Test
    fun callToUpdateAnniversaryShouldNeverDeliverCallToRepositoryIfItemWithSuchIdDoesNotExists() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(false)
        service.update(validAnniversary)
        verify(repository, never()).save(validAnniversary)
    }

    @Test
    fun failureUpdatingNonExistentItemShouldReturnError() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(false)
        val result = service.update(validAnniversary)
        assertEquals(Anniversary.Empty, result.response)
        assertEquals("error_does_not_exist", result.error)
    }

    @Test
    fun callToUpdateAnniversaryShouldDeliverCallToRepositoryIfItemWithSuchIdExists() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(true)
        service.update(validAnniversary)
        verify(repository).save(validAnniversary)
    }

    @Test
    fun successUpdatingAnniversaryShouldReturnCorrectResult() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(true)
        `when`(repository.save(validAnniversary)).thenReturn(validAnniversary)
        val result = service.update(validAnniversary)
        assertEquals(validAnniversary, result.response)
        assertEquals("", result.error)
    }

    @Test
    fun failureUpdatingAnniversaryShouldReturnError() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(true)
        `when`(repository.save(validAnniversary)).thenReturn(null)
        val result = service.update(validAnniversary)
        assertEquals(Anniversary.Empty, result.response)
        assertEquals("error_updating_anniversary", result.error)
    }

    @Test
    fun callToDeleteAnniversaryShouldNeverDeliverCallToRepositoryIfItemWithSuchIdDoesNotExists() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(false)
        service.delete(validAnniversary.id ?: "")
        verify(repository, never()).delete(validAnniversary.id)
    }

    @Test
    fun failureDeletingNonExistentItemShouldReturnError() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(false)
        val result = service.delete(validAnniversary.id ?: "")
        assertEquals(Anniversary.Empty, result.response)
        assertEquals("error_does_not_exist", result.error)
    }

    @Test
    fun callToDeleteAnniversaryShouldDeliverCallToRepositoryIfItemWithSuchIdExists() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(true)
        service.delete(validAnniversary.id ?: "")
        verify(repository).delete(validAnniversary.id)
    }

    @Test
    fun successfulDeletionShouldReturnCorrectResult() {
        `when`(repository.exists(validAnniversary.id)).thenReturn(true)
        val result = service.delete(validAnniversary.id ?: "")
        assertEquals(Anniversary.Empty, result.response)
        assertEquals("", result.error)
    }

    private fun createValidAnniversary(): Anniversary {
        val date = Date()
        return Anniversary("id", "name", date, true, 2, "createdBy", date, "type")
    }
}