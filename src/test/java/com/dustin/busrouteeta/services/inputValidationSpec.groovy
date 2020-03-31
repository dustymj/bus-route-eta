package com.dustin.busrouteeta.services

import com.dustin.busrouteeta.data.BusRouteEtaConstants
import com.dustin.busrouteeta.data.TimepointDepartureVO
import spock.lang.Specification

class inputValidationSpec extends Specification {

    InputValidation inputValidation = new InputValidation()

    def "test validateInputs - valid - All inputs are valid"() {
        given:
        String inputDirection = BusRouteEtaConstants.DIRECTION_NORTH
        String inputRoute = "901"
        String inputStop = "MAAM"
        String validatedDirection = BusRouteEtaConstants.DIRECTION_NORTH_ID
        String validatedRoute = "901"
        String validatedStop = "MAAM"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)
        inputs.put(BusRouteEtaConstants.INPUT_BUS_STOP, inputStop)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO()

        when:
        boolean valid = inputValidation.validateInputs(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        valid
        timepointDepartureVO
        timepointDepartureVO.direction == validatedDirection
        timepointDepartureVO.routeID == validatedRoute
        timepointDepartureVO.stopID == validatedStop
    }

    def "test validateInputs - invalid - route is invalid"() {
        String inputDirection = BusRouteEtaConstants.DIRECTION_NORTH
        String inputRoute = "invalidRoute"
        String inputStop = "MAAM"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)
        inputs.put(BusRouteEtaConstants.INPUT_BUS_STOP, inputStop)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO()

        when:
        boolean valid = inputValidation.validateInputs(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        !valid
        timepointDepartureVO
        !timepointDepartureVO.direction
        !timepointDepartureVO.routeID
        !timepointDepartureVO.stopID
    }

    def "test validateInputs - invalid - direction is invalid"() {
        given:
        String inputDirection = "invalid Direction"
        String inputRoute = "901"
        String inputStop = "MAAM"
        String validatedRoute = "901"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)
        inputs.put(BusRouteEtaConstants.INPUT_BUS_STOP, inputStop)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO()

        when:
        boolean valid = inputValidation.validateInputs(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        !valid
        timepointDepartureVO
        !timepointDepartureVO.direction
        timepointDepartureVO.routeID == validatedRoute
        !timepointDepartureVO.stopID
    }

    def "test validateInputs - valid - stop is invalid"() {
        given:
        String inputDirection = BusRouteEtaConstants.DIRECTION_NORTH
        String inputRoute = "901"
        String inputStop = "invalid Stop"
        String validatedDirection = BusRouteEtaConstants.DIRECTION_NORTH_ID
        String validatedRoute = "901"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)
        inputs.put(BusRouteEtaConstants.INPUT_BUS_STOP, inputStop)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO()

        when:
        boolean valid = inputValidation.validateInputs(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        !valid
        timepointDepartureVO
        timepointDepartureVO.direction == validatedDirection
        timepointDepartureVO.routeID == validatedRoute
        !timepointDepartureVO.stopID
    }
}
