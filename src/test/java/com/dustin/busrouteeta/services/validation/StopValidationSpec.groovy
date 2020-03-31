package com.dustin.busrouteeta.services.validation

import com.dustin.busrouteeta.data.BusRouteEtaConstants
import com.dustin.busrouteeta.data.TimepointDepartureVO
import spock.lang.Specification

class StopValidationSpec extends Specification {

    StopValidation stopValidation = new StopValidation()

    def "test validateStop - valid - stop ID match"() {
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

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO(
                routeID: validatedRoute,
                direction: validatedDirection
        )

        when:
        boolean valid = stopValidation.validateStop(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        valid
        timepointDepartureVO
        timepointDepartureVO.stopID == validatedStop
    }
    def "test validateStop - valid - stop description match"() {
        given:
        String inputDirection = BusRouteEtaConstants.DIRECTION_NORTH
        String inputRoute = "901"
        String inputStop = "Mall of America Station"
        String validatedDirection = BusRouteEtaConstants.DIRECTION_NORTH_ID
        String validatedRoute = "901"
        String validatedStop = "MAAM"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)
        inputs.put(BusRouteEtaConstants.INPUT_BUS_STOP, inputStop)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO(
                routeID: validatedRoute,
                direction: validatedDirection
        )

        when:
        boolean valid = stopValidation.validateStop(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        valid
        timepointDepartureVO
        timepointDepartureVO.stopID == validatedStop
    }

    def "test validateStop - invalid - stop is not a match"() {
        given:
        String inputDirection = BusRouteEtaConstants.DIRECTION_NORTH
        String inputRoute = "901"
        String inputStop = "Invalid station entry"
        String validatedDirection = BusRouteEtaConstants.DIRECTION_NORTH_ID
        String validatedRoute = "901"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)
        inputs.put(BusRouteEtaConstants.INPUT_BUS_STOP, inputStop)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO(
                routeID: validatedRoute,
                direction: validatedDirection
        )

        when:
        boolean valid = stopValidation.validateStop(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        !valid
        timepointDepartureVO
        !timepointDepartureVO.stopID
    }

    def "test isStopDescriptionMatch - valid - happy path"() {
        given:
        String inputStop = "mall of america station"
        String stopVODescription = "MALL of AMERICA sTaTiOn"

        when:
        boolean match = stopValidation.isStopDescriptionMatch(inputStop, stopVODescription)

        then:
        0 * _

        and:
        match
    }

    def "test isStopIDMatch - valid - happy path"() {
        given:
        String inputStop = "maam"
        String stopVOID = "MAAM"

        when:
        boolean match = stopValidation.isStopIDMatch(inputStop, stopVOID)

        then:
        0 * _

        and:
        match
    }
}
