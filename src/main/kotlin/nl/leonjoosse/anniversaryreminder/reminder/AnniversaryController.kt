package nl.leonjoosse.anniversaryreminder.reminder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping(value = "/anniversary", produces = arrayOf("application/json"), consumes = arrayOf("application/json"))
class AnniversaryController(@Autowired val service: AnniversaryService) {

    @GetMapping("/{id}")
    fun getById(@PathParam("id") id: String): AnniversaryResponse {
        return AnniversaryResponse(service.findById(id), "")
    }

    @GetMapping
    fun list(): AnniversaryResponse {
        return AnniversaryResponse(service.listAll(), "")
    }

    @PostMapping
    fun create(@RequestBody anniversary: Anniversary): AnniversaryResponse {
        return service.create(anniversary)
    }

    @PutMapping("/{id}")
    fun update(@PathParam("id") id: String, @RequestBody anniversary: Anniversary): AnniversaryResponse {

        if (id != anniversary.id) {
            return AnniversaryResponse(Anniversary.Empty(), "error_path_id_does_not_match_object_id")
        }

        return service.update(anniversary)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathParam("id") id: String): AnniversaryResponse {
        return service.delete(id)
    }
}