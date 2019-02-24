package flightservice

import org.springframework.boot.SpringApplication

/*Most of the content inside here is not created by me but legally barrowed from my lecturer
* Andrea Arcuri  with some changes.
* link :    https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/advanced*/



class LocalApplicationRunner

/*
    If you run this directly, you can then check the Swagger documentation at:

    http://localhost:8080/swagger-ui.html

 */

fun main(args: Array<String>)
{
    SpringApplication.run(FlightRestApplication::class.java )
}

