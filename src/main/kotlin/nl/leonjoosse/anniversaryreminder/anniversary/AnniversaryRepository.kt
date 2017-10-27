package nl.leonjoosse.anniversaryreminder.anniversary

import org.springframework.data.mongodb.repository.MongoRepository

internal interface AnniversaryRepository : MongoRepository<Anniversary, String>