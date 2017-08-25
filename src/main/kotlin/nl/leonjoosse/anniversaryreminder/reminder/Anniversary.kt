package nl.leonjoosse.anniversaryreminder.reminder

import java.util.*

internal data class Anniversary(val id: String?,
                                val name: String?,
                                val date: Date?,
                                val remind: Boolean?,
                                val createdBy: String?,
                                val created: Date?,
                                val type: String?) {

    constructor() : this(null, "", null, false, "", null, "")

    internal companion object {

        @JvmField
        val Empty = Anniversary("", "", null, true, "", null, "")
    }
}