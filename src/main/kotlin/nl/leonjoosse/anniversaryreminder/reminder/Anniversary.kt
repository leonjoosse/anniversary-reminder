package nl.leonjoosse.anniversaryreminder.reminder

import org.springframework.data.annotation.Id
import java.util.*

open class Anniversary(
        @Id
        val id: String,
        val name: String,
        val date: Date,
        val remind: Boolean,
        val createdBy: String,
        val created: Date,
        val type: String
) {
    class Empty : Anniversary("", "", Date(), true, "", Date(), "")
}