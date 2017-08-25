package nl.leonjoosse.anniversaryreminder

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration

@SpringBootApplication(exclude = arrayOf(RepositoryRestMvcAutoConfiguration::class))
class AnniversaryReminderApplication

fun main(args: Array<String>) {
    SpringApplication.run(AnniversaryReminderApplication::class.java, *args)
}
