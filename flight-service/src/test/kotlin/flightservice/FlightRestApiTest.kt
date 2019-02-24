package flightservice

import org.junit.Assert.*
import flightservice.flightservice.db.FlightEntity
import flightservice.flightservice.db.FlightRepository
import flightservice.flightservice.dto.FlightDto
import flightservice.flightservice.service.FlightService2
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner


/*Most of the content inside here is not created by me but legally barrowed from my lecturer
* Andrea Arcuri  with some changes.
* link :    https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/advanced*/





@ActiveProfiles("test")
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(FlightRestApplication::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FlightRestApiTest {

    @LocalServerPort
    protected var port = 0

    @Autowired
    protected lateinit var repository: FlightRepository



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



    /**@Get gets all flights available*/
    @Test
    fun testGetAll() {

        given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("data.list.size()", equalTo(8))
    }


    /**@Get gets all flights by location travelling from */
    @Test
    fun testGetAllByFromLocation() {

        val fromLocation = "Gardermoen"

        RestAssured.given().accept(ContentType.JSON)
                .queryParam("fromLocation", fromLocation)
                .get().then()
                .statusCode(200)
                .body("data.list.size()", CoreMatchers.equalTo(4))
    }


    /**@Get gets all flights by location travelling to */
    @Test
    fun testGetAllByToLocation() {
        val toLocation = "Bangkok"

        RestAssured.given().accept(ContentType.JSON)
                .queryParam("toLocation", toLocation)
                .get().then()
                .statusCode(200)
                .body("data.list.size()", CoreMatchers.equalTo(2))
    }



    /**return a payload  with message and error code if not exists*/
    @Test
    fun testNoFlightsFound() {

        given().accept(ContentType.JSON)
                .get("/-3")
                .then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("message", not(equalTo(null)))
    }



    /**@Get gets all flights and verifies contents*/
    @Test
    fun testRetrieveEachSingleFlights() {

        val flights = given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("data.list.size()", equalTo(8))
                .extract().body().jsonPath().getList("data.list", FlightDto::class.java)


        for (flight in flights) {

            given().accept(ContentType.JSON)
                    .get("/${flight.id}")
                    .then()
                    .statusCode(200)
                    .body("data.airline", equalTo(flight.airline))
                    .body("data.flightNumber", equalTo(flight.flightNumber))
                    .body("data.fromLocation", equalTo(flight.fromLocation))
                    .body("data.toLocation", equalTo(flight.toLocation))
                    .body("data.date", equalTo(flight.date))
                    .body("data.price", equalTo(flight.price))
                    .body("data.seats", equalTo(flight.seats))
        }

    }


    /**@Creates a new flight and verifies if exists.*/
    @Test
    fun testCreateNewFlight() {

        val currentSize = given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .extract().body().path<Int>("data.list.size()")

        val airline = "Luft_Hansa"

        val location = given().contentType(ContentType.JSON)
                .body(FlightDto(airline, "Ab3333", "Oslo", "Stockholm", "24.01.18", 1000, 500))
                .post()
                .then()
                .statusCode(201)
                .extract().header("location")

        given().accept(ContentType.JSON)
                .basePath("")
                .get(location)
                .then()
                .statusCode(200)
                .body("data.airline", equalTo(airline))

        given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("data.list.size()", equalTo(currentSize + 1))

    }


    /** @Delete all flights*/
    @Test
    fun testDeleteAllFlights() {

        val flights = given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("data.list.size()", equalTo(8))
                .extract().body().jsonPath().getList("data.list", FlightDto::class.java)

        for (flight in flights) {

            given().accept(ContentType.JSON)
                    .delete("/${flight.id}")
                    .then()
                    .statusCode(204)
        }

        given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("data.list.size()", equalTo(0))

    }


    /**@Complete Updates Entity with put */
    @Test
    fun testUpdateFlightWithPut() {

        val airline = "British_Airlines"

        val location = given().contentType(ContentType.JSON)
                .body(FlightDto(airline, "AB43434", "London", "Zimbabwe", "24.01.18", 4500, 500))
                .post()
                .then()
                .statusCode(201)
                .extract().header("location")

        given().accept(ContentType.JSON)
                .basePath("")
                .get(location)
                .then()
                .statusCode(200)
                .body("data.airline", equalTo(airline))

        val id = location.substring(location.lastIndexOf('/') + 1)

        val modifiedAirline = "Norwegian"

        given().contentType(ContentType.JSON)
                .body(FlightDto(modifiedAirline, "AB43434", "London", "Zimbabwe", "24.01.18", 4500, 500, id))
                .put("/$id")
                .then()
                .statusCode(204)

        given().accept(ContentType.JSON)
                .basePath("")
                .get(location)
                .then()
                .statusCode(200)
                .body("data.airline", equalTo(modifiedAirline))
    }


    fun patchWithMergeJSon(id: Long, jsonBody: String, statusCode: Int) {

        given().contentType("application/merge-patch+json")
                .body(jsonBody)
                .patch("$id")
                .then()
                .statusCode(statusCode)
    }


    /**@PArtial updates Entities field with Integer value using Json mergepatch*/
    @Test
    fun testPartialUpdateWithIntegerValueWithMergePatch() {

        val toLocation = "Zimbabwe"

        val flightNumber = "AB43434"

        val fromLocation = "London"

        val airline = "Alaska_airlines"

        val location = given().contentType(ContentType.JSON)
                .body(FlightDto(airline, flightNumber, fromLocation, toLocation, "24.01.18", 4500, 500))
                .post()
                .then()
                .statusCode(201)
                .extract().header("location")

        val modifiedPrice = 3500

        val id = location.substring(location.lastIndexOf('/') + 1).toLong()

        patchWithMergeJSon(id, "{\"price\":$modifiedPrice}", 204)

        given().accept(ContentType.JSON)
                .basePath("")
                .get(location)
                .then()
                .statusCode(200)
                .body("data.price", equalTo(modifiedPrice))


    }

    /**@Partial updates Entities field with String value using Json mergepatch*/
    @Test
    fun testPartialUpdateWithStringeWithMergePatch() {

        val toLocation = "Zimbabwe"

        val flightNumber = "AB43434"

        val fromLocation = "London"

        val airline = "Alaska_airlines"

        val location = given().contentType(ContentType.JSON)
                .body(FlightDto(airline, flightNumber, fromLocation, toLocation, "24.01.18", 4500, 500))
                .post()
                .then()
                .statusCode(201)
                .extract().header("location")

        given().accept(ContentType.JSON)
                .basePath("")
                .get(location)
                .then()
                .statusCode(200)
                .body("data.airline", equalTo(airline))



        val modifiedAirline = "NewAirline"

        val id = location.substring(location.lastIndexOf('/') + 1).toLong()

        patchWithMergeJSon(id, "{\"airline\": \"$modifiedAirline\"}", 204)

        given().accept(ContentType.JSON)
                .basePath("")
                .get(location)
                .then()
                .statusCode(200)
                .body("data.airline", equalTo(modifiedAirline))

    }


}