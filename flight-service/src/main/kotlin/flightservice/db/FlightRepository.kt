package flightservice.flightservice.db


/*Most of the content inside here is not created by me but legally barrowed from my lecturer
* Andrea Arcuri  with some changes.
* link :    https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/advanced*/


import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface FlightRepository : CrudRepository<FlightEntity, Long>
