package nl.leonjoosse.anniversaryreminder.reminder

import nl.leonjoosse.anniversaryreminder.anniversary.Anniversary
import nl.leonjoosse.anniversaryreminder.anniversary.AnniversaryService
import java.time.MonthDay

internal class ReminderService(val anniversaryService: AnniversaryService) {

    fun calculateRemindersForDate(monthDay: MonthDay): ReminderResponse {

        val anniversaryResponse = anniversaryService.listAll();

        if (anniversaryResponse.error.isNotEmpty()) {
            return ReminderResponse(error = "error_could_not_retrieve_anniversaries")
        }

        val anniversaries = (anniversaryResponse.response as List<Anniversary>)
                .filter { it.remind == true }
                .filter { monthDay == it.getRemindMonthDay() }

        return ReminderResponse(response = anniversaries)
    }
}
