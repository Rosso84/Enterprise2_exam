package flightservice.flightservice.db

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
class FlightEntity(

        @get:NotBlank @get:Size(max = 256)
        var airline: String,

        @get:NotBlank @get:Size(max = 256)
        var flightNumber: String,

        /*could use location as shown in arcuris news -country examples*/
        @get:NotBlank @get:Size(max = 256)
        var fromLocation: String,

        /*could use location as shown in arcuris news -country examples*/
        @get:NotBlank @get:Size(max = 256)
        var toLocation: String,


        /*probably much better to use a Date type, but i dont want to
         try to confuse my self too much as i have never tried this.*/
        @get:NotBlank @get:Size(max = 64)
        var date: String,

        @get:Max(20000) @get:NotNull
        var price: Int,

        @get:Max(1000) @get:NotNull
        var seats: Int,

        @get:Id
        @get:GeneratedValue
        var id: Long? = null



)