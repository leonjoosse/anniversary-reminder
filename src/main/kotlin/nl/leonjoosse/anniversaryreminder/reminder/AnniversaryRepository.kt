package nl.leonjoosse.anniversaryreminder.reminder

import org.springframework.data.mongodb.repository.MongoRepository

interface AnniversaryRepository : MongoRepository<Anniversary, String>