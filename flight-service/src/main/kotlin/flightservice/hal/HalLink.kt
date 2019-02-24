package flightservice.flightservice.hal

import io.swagger.annotations.ApiModelProperty

/*Most of the content inside here is not created by me but legally barrowed from my lecturer
* Andrea Arcuri  with some changes.
* link :    https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/advanced*/


/*
    Note: a HAL link can have more data, but here I am only
    interested in the "href"
 */
open class HalLink(

        @ApiModelProperty("URL of the link")
        var href: String = ""
)