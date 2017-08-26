package nl.leonjoosse.anniversaryreminder.anniversary

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.MonthDay
import java.util.*

class AnniversaryTest {

    @Test
    fun getRemindMonthDayWithZeroDaysInAdvanceShouldReturnCorrectMonthDay() {
        val expected = MonthDay.from(LocalDate.now())
        val anniversary = Anniversary("", "", Date(), true, 0, "", Date(), "")
        Assert.assertEquals(expected, anniversary.getRemindMonthDay())
    }

    @Test
    fun getRemindMonthDayWithOneDaysInAdvanceShouldReturnCorrectMonthDay() {
        val expected = MonthDay.from(LocalDate.now().minusDays(1))
        val anniversary = Anniversary("", "", Date(), true, 1, "", Date(), "")
        Assert.assertEquals(expected, anniversary.getRemindMonthDay())
    }
}