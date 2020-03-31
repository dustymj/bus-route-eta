package com.dustin.busrouteeta.services.validation

import com.dustin.busrouteeta.data.BusRouteEtaConstants
import com.dustin.busrouteeta.data.TimepointDepartureVO
import spock.lang.Specification

class RouteValidationSpec extends Specification {
    RouteValidation routeValidation = new RouteValidation()

    def "test validateRoute - valid - route ID is valid"() {
        given:
        String inputRoute = "901"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO()

        when:
        boolean valid = routeValidation.validateRoute(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        valid
        timepointDepartureVO
        timepointDepartureVO.routeID == inputRoute
    }

    def "test validateRoute - valid - route description is valid"() {
        given:
        String inputRoute = "metro blue line"
        String routeID = "901"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO()

        when:
        boolean valid = routeValidation.validateRoute(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        valid
        timepointDepartureVO
        timepointDepartureVO.routeID == routeID
    }

    def "test validateRoute - invalid - route entered is not valid"() {
        given:
        String inputRoute = "Dustin route"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO()

        when:
        boolean valid = routeValidation.validateRoute(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        !valid
        timepointDepartureVO
        !timepointDepartureVO.routeID
    }

    def "test isRouteDescriptionMatch - valid - happy path"() {
        given:
        String inputRoute = "metro blue line"
        String description = "METRO Blue Line"

        when:
        boolean match = routeValidation.isRouteDescriptionMatch(inputRoute, description)

        then:
        0 * _

        and:
        match
    }

    def "test isRouteIDMatch - valid - happy path"() {
        given:
        String inputRoute = "901"
        String routeID = "901"

        when:
        boolean match = routeValidation.isRouteIDMatch(inputRoute, routeID)

        then:
        0 * _

        and:
        match
    }
}
