package flightservice.flightservice.service


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import flightservice.flightservice.db.FlightEntity
import javax.persistence.TypedQuery

/*Most of the content inside here is not created by me but legally barrowed from my lecturer
* Andrea Arcuri  with some changes for my exam.
* link :    https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/advanced*/



@Service
@Transactional
class FlightService2(

        val em: EntityManager
) {


    /*
        Here I am not using a CRUD repository, but
        rather handling all manually via an JPA Entity Manager.
     */



    /*here we query manually in order to specify what to get more specific instead of using crud*/
    fun getFlightList(limit: Int, fromLocation: String?, toLocation: String?): List<FlightEntity> {

        val query: TypedQuery<FlightEntity>

        if (fromLocation.isNullOrBlank() && toLocation.isNullOrBlank()) {
            query = em.createQuery("select n from FlightEntity n", FlightEntity::class.java)

        }else if(fromLocation.isNullOrBlank()){
            query = em.createQuery("select n from FlightEntity n where n.toLocation=?1", FlightEntity::class.java)
            query.setParameter(1, toLocation)

        } else {
            query = em.createQuery("select n from FlightEntity n where n.fromLocation=?1", FlightEntity::class.java)
            query.setParameter(1, fromLocation)
        }

        query.maxResults = limit

        val result = query.resultList

        return result
    }

    


}

