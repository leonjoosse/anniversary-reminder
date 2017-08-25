package nl.leonjoosse.anniversaryreminder.reminder

import org.springframework.stereotype.Service

@Service class AnniversaryService(val repository: AnniversaryRepository) {

    fun findById(id: String) = repository.findOne(id)!!

//    fun listAll(): List<Anniversary> = repository.findAll() ?: emptyList<Anniversary>()

    fun listAll(): List<Anniversary> {
        val anniversaries = repository.findAll()
        return anniversaries
    }

    fun create(anniversary: Anniversary): AnniversaryResponse {

        val result = repository.save(anniversary)

        if (result == null) {
            return AnniversaryResponse(Anniversary.Empty(), "error_inserting")
        }

        return AnniversaryResponse(result, "")
    }

    fun update(anniversary: Anniversary): AnniversaryResponse {

        if (!repository.exists(anniversary.id)) {
            return AnniversaryResponse(Anniversary.Empty(), "error_does_not_exist")
        }

        val result = repository.save(anniversary)

        if (result == null) {
            return AnniversaryResponse(Anniversary.Empty(), "error_updating")
        }

        return AnniversaryResponse(result, "")
    }

    fun delete(id: String): AnniversaryResponse {

        if (!repository.exists(id)) {
            return AnniversaryResponse(Anniversary.Empty(), "error_does_not_exist")
        }

        repository.delete(id)

        return AnniversaryResponse(Anniversary.Empty(), "")
    }
}

