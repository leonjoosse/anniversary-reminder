package nl.leonjoosse.anniversaryreminder.reminder

import nl.leonjoosse.anniversaryreminder.anniversary.Anniversary

internal data class ReminderResponse(var response: List<Anniversary> = emptyList(),
                                     val error: String = "")