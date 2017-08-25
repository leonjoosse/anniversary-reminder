package nl.leonjoosse.anniversaryreminder.reminder

import org.springframework.data.mongodb.repository.MongoRepository

internal interface AnniversaryRepository : MongoRepository<Anniversary, String>