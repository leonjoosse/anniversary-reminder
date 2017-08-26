package nl.leonjoosse.anniversaryreminder.anniversary

import java.time.LocalDate
import java.time.MonthDay
import java.util.*

internal data class Anniversary(val id: String?,
                                val name: String?,
                                val date: Date?,
                                val remind: Boolean?,
                                val remindDaysInAdvance: Int,
                                val createdBy: String?,
                                val created: Date?,
                                val type: String?) {

    constructor() : this(null, "", null, false, 0, "", null, "")

    internal companion object {

        @JvmField
        val Empty = Anniversary("", "", null, true, 1, "", null, "")
    }

    fun getRemindMonthDay(): MonthDay {

        val remindDate : LocalDate

        if (date == null) {
            remindDate = LocalDate.now()
        } else {
            remindDate = LocalDate.of(date.year, date.month + 1, date.date + 1)
        }

        return MonthDay.from(remindDate.minusDays(remindDaysInAdvance.toLong()))
    }

}