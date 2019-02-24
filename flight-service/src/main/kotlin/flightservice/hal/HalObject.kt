package flightservice.flightservice.hal

import io.swagger.annotations.ApiModelProperty

/*Most of the content inside here is not created by me but legally barrowed from my lecturer
* Andrea Arcuri  with some changes.
* link :    https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/advanced*/



/*
    HAL (Hypertext Application Language) is a proposal (not a standard, at least not yet...)
    to how to define links in JSON objects
    for HATEOAS ( Hypermedia As The Engine Of Application State).

    Here, I am just using a subset of it.

    More info at:
    https://en.wikipedia.org/wiki/Hypertext_Application_Language
    https://en.wikipedia.org/wiki/HATEOAS
 */
open class HalObject(

        @ApiModelProperty("HAL links")
        var _links: MutableMap<String, HalLink> = mutableMapOf()
)