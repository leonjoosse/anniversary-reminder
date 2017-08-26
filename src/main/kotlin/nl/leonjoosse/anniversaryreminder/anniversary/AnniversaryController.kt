package nl.leonjoosse.anniversaryreminder.anniversary

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping(value = "/anniversary", produces = arrayOf("application/json"), consumes = arrayOf("application/json"))
internal class AnniversaryController(@Autowired private val service: AnniversaryService) {

    @GetMapping("/{id}")
    fun getById(@PathParam("id") id: String): AnniversaryResponse {
        return service.findById(id)
    }

    @GetMapping
    fun list(): AnniversaryResponse {
        return service.listAll()
    }

    @PostMapping
    fun create(@RequestBody anniversary: Anniversary): AnniversaryResponse {
        return service.create(anniversary)
    }

    @PutMapping
    fun update(@RequestBody anniversary: Anniversary): AnniversaryResponse {
        return service.update(anniversary)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathParam("id") id: String): AnniversaryResponse {
        return service.delete(id)
    }
}