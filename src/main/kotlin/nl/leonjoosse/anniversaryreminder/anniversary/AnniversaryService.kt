package nl.leonjoosse.anniversaryreminder.anniversary

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class AnniversaryService(@Autowired private val repository: AnniversaryRepository) {

    fun findById(id: String): AnniversaryResponse {
        val result = repository.findOne(id)
        result?.let {
            return AnniversaryResponse(result, "")
        }
        return AnniversaryResponse(Anniversary.Empty, "error_item_not_found")
    }

    fun listAll(): AnniversaryResponse {
        val anniversaries = repository.findAll()
        anniversaries?.let {
            return AnniversaryResponse(anniversaries, "")
        }
        return AnniversaryResponse(emptyList<Anniversary>(), "error_loading_anniversaries")
    }

    fun create(anniversary: Anniversary): AnniversaryResponse {
        val result = repository.save(anniversary)
        result?.let {
            return AnniversaryResponse(result, "")
        }
        return AnniversaryResponse(Anniversary.Empty, "error_saving_anniversary")
    }

    fun update(anniversary: Anniversary): AnniversaryResponse {
        if (!repository.exists(anniversary.id)) {
            return AnniversaryResponse(Anniversary.Empty, "error_does_not_exist")
        }
        return proceedAnniversaryUpdate(anniversary)
    }

    private fun proceedAnniversaryUpdate(anniversary: Anniversary): AnniversaryResponse {
        val result = repository.save(anniversary)
        result?.let {
            return AnniversaryResponse(result, "")
        }
        return AnniversaryResponse(Anniversary.Empty, "error_updating_anniversary")
    }

    fun delete(id: String): AnniversaryResponse {
        if (!repository.exists(id)) {
            return AnniversaryResponse(Anniversary.Empty, "error_does_not_exist")
        }
        repository.delete(id)
        return AnniversaryResponse(Anniversary.Empty, "")
    }
}

