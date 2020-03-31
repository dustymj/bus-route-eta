package com.dustin.busrouteeta.services

import com.dustin.busrouteeta.data.BusRouteEtaConstants
import spock.lang.Specification

class inputHandlerSpec extends Specification {

    InputHandler inputHandler = new InputHandler()

    def "test handleInputs - valid - all inputs supplied"() {
        given:
        String direction = "direction"
        String route = "route"
        String stop = "stop"
        String[] args = [route, stop, direction]

        when:
        HashMap<String, String> inputs = inputHandler.handleInputs(args)

        then:
        0 * _

        and:
        inputs
        inputs[BusRouteEtaConstants.INPUT_ROUTE] == route
        inputs[BusRouteEtaConstants.INPUT_BUS_STOP] == stop
        inputs[BusRouteEtaConstants.INPUT_DIRECTION] == direction
    }
}
