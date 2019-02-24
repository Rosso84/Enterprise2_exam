package flightservice.flightservice.service

import flightservice.FlightRestApplication
import flightservice.flightservice.db.FlightEntity
import flightservice.flightservice.db.FlightRepository
import io.restassured.RestAssured
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@ActiveProfiles("test")
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(FlightRestApplication::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FlightService2Test {


    @LocalServerPort
    protected var port = 0

    @Autowired
    protected lateinit var repository: FlightRepository

    @Autowired
    private lateinit var service: FlightService2


    @Before
    @After
    fun reset() {

        // RestAssured configs shared by all the tests
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
        RestAssured.basePath = "/flights"
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        repository.run {
            deleteAll()

            save(FlightEntity("Norwegian", "AB1209", "Gardermoen", "HongKong", "24.01.18", 5000, 700))
            save(FlightEntity("Norwegian", "AB2209", "Gardermoen", "Bangkok", "24.01.18", 7000, 700))
            save(FlightEntity("Norwegian", "AB3209", "Gardermoen", "London", "24.01.18", 1000, 700))
            save(FlightEntity("Norwegian", "AB42010", "Gardermoen", "Bangkok", "24.01.18", 8000, 700))
            save(FlightEntity("Scan_Airlines", "AB2304", "Praha", "Denmark", "24.01.18", 1500, 300))
            save(FlightEntity("Alaska_airlines", "AC209", "Alaska", "North_pole", "24.01.18", 100, 150))
            save(FlightEntity("LuftHansa", "AG2409", "Gøteborg", "Saigon", "24.01.18", 8000, 800))
            save(FlightEntity("British_airlines", "AB4420", "London", "Oslo", "24.01.18", 1000, 300))
        }

    }


    /**@Get gets all the flights*/
    @Test
    fun testGetAllByFromLocation() {
        val fromLocation = "Gardermoen"

        val limit = 50

        val flights: List<FlightEntity>

        flights = service.getFlightList(limit, fromLocation, null)

        Assert.assertTrue(flights.size == 4)

    }

    /**@Get gets all the flights*/
    @Test
    fun testGetAllByToLocation() {
        val toLocation = "Bangkok"

        val limit = 50

        val flights: List<FlightEntity>

        flights = service.getFlightList(limit, null, toLocation)

        Assert.assertTrue(flights.size == 2)

    }

}