package flightservice.flightservice.dto

import flightservice.flightservice.db.FlightEntity
import flightservice.flightservice.hal.PageDto
import kotlin.streams.toList


/*Most of the content inside here is not created by me but legally barrowed from my lecturer
* Andrea Arcuri  with some changes.
* link :    https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/advanced*/


object DtoTransformer {



    fun transform(flights: FlightEntity): FlightDto {
        val dto = FlightDto(
                flights.airline,
                flights.flightNumber,
                flights.fromLocation,
                flights.toLocation,
                flights.date,
                flights.price,
                flights.seats,
                flights.id?.toString()
                )
        return dto
    }


    /**
     * This creates a HAL dto, but with the links (self, next, previous)
     * that still have to be set
     */
    fun transform(flightList: List<FlightEntity>,
                  offset: Int,
                  limit: Int) : PageDto<FlightDto> {

        val dtoList: MutableList<FlightDto> = flightList.stream()
                .skip(offset.toLong()) // this is a good example of how streams simplify coding
                .limit(limit.toLong())
                .map { transform(it) }
                .toList().toMutableList()


        return PageDto(
                list = dtoList,
                rangeMin = offset,
                rangeMax = offset + dtoList.size - 1,
                totalSize = flightList.size
        )
    }
}