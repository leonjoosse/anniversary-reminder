package nl.leonjoosse.anniversaryreminder.reminder

import nl.leonjoosse.anniversaryreminder.anniversary.Anniversary
import nl.leonjoosse.anniversaryreminder.anniversary.AnniversaryResponse
import nl.leonjoosse.anniversaryreminder.anniversary.AnniversaryService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.time.LocalDate
import java.time.MonthDay
import java.util.*

class ReminderServiceTest {

    @Mock
    private lateinit var anniversaryService: AnniversaryService
    private lateinit var reminderService: ReminderService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        reminderService = ReminderService(anniversaryService)
    }

    @Test
    fun calculateRemindersForDateShouldCallAnniversaryServiceListAll() {
        `when`(anniversaryService.listAll()).thenReturn(AnniversaryResponse(listOf<Anniversary>()))
        reminderService.calculateRemindersForDate(MonthDay.now())
        verify(anniversaryService).listAll()
    }

    @Test
    fun successCalculateRemindersForDateShouldReturnAnniversaries() {
        val anniversaries = createAnniversariesThatShouldRemindToday(true)
        `when`(anniversaryService.listAll()).thenReturn(AnniversaryResponse(anniversaries, ""))
        val reminderResponse = reminderService.calculateRemindersForDate(MonthDay.now())
        Assert.assertEquals(1, reminderResponse.response.size)
    }

    @Test
    fun calculateRemindersForDateShouldNotReturnAnniversariesWhereRemindIsFalse() {
        val anniversaries = createAnniversariesThatShouldRemindToday(false)
        `when`(anniversaryService.listAll()).thenReturn(AnniversaryResponse(anniversaries, ""))
        val reminderResponse = reminderService.calculateRemindersForDate(MonthDay.now())
        Assert.assertEquals(0, reminderResponse.response.size)
    }

    @Test
    fun errorInAnniversaryRepositoryShouldReturnError() {
        `when`(anniversaryService.listAll()).thenReturn(AnniversaryResponse(emptyList<Anniversary>(), "anything"))
        val response = reminderService.calculateRemindersForDate(MonthDay.now())
        Assert.assertEquals("error_could_not_retrieve_anniversaries", response.error)
    }

    private fun createAnniversariesThatShouldRemindToday(remind: Boolean): List<Anniversary> {
        val date = Date()

        // Return an Anniversary that the user should be reminded of today and yesterday
        return listOf(
                Anniversary("id", "name", date, remind, 0, "createdBy", date, "type"),
                Anniversary("id", "name", date, remind, 1, "createdBy", date, "type")
        )
    }
}