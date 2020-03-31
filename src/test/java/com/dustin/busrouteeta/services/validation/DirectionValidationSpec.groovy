package com.dustin.busrouteeta.services.validation

import com.dustin.busrouteeta.data.BusRouteEtaConstants
import com.dustin.busrouteeta.data.TimepointDepartureVO
import spock.lang.Specification
import spock.lang.Unroll

class DirectionValidationSpec extends Specification {

    DirectionValidation directionValidation = new DirectionValidation()

    def "test validateDirection - valid - direction is valid and is a match"() {
        given:
        String inputDirection = BusRouteEtaConstants.DIRECTION_NORTH
        String inputRoute = "901"
        String validatedRoute = "901"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO(
                routeID: validatedRoute
        )

        when:
        boolean valid = directionValidation.validateDirection(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        valid
        timepointDepartureVO
        timepointDepartureVO.direction == BusRouteEtaConstants.DIRECTION_NORTH_ID
    }

    def "test validateDirection - invalid - direction is not valid"() {
        given:
        String inputDirection = "direction"
        String inputRoute = "901"
        String validatedRoute = "901"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO(
                routeID: validatedRoute
        )

        when:
        boolean valid = directionValidation.validateDirection(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        !valid
        timepointDepartureVO
        !timepointDepartureVO.direction
    }

    def "test validateDirection - valid - direction is valid but not a match"() {
        given:
        String inputDirection = BusRouteEtaConstants.DIRECTION_EAST
        String inputRoute = "901"
        String validatedRoute = "901"

        HashMap<String, String> inputs = new HashMap<>()
        inputs.put(BusRouteEtaConstants.INPUT_DIRECTION, inputDirection)
        inputs.put(BusRouteEtaConstants.INPUT_ROUTE, inputRoute)

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO(
                routeID: validatedRoute
        )

        when:
        boolean valid = directionValidation.validateDirection(inputs, timepointDepartureVO)

        then:
        0 * _

        and:
        !valid
        timepointDepartureVO
        !timepointDepartureVO.direction
    }

    @Unroll
    def "test isDirectionValid - valid - direction #scenario is valid"() {
        when:
        boolean valid = directionValidation.isDirectionValid(inputDirection)

        then:
        0 * _

        and:
        valid

        where:
        scenario | inputDirection
        "North"  | BusRouteEtaConstants.DIRECTION_NORTH
        "East"   | BusRouteEtaConstants.DIRECTION_EAST
        "South"  | BusRouteEtaConstants.DIRECTION_SOUTH
        "West"   | BusRouteEtaConstants.DIRECTION_WEST
    }

    def "test isDirectionValid - invalid - direction is not valid"() {
        given:
        String inputDirection = "inputDirection"

        when:
        boolean valid = directionValidation.isDirectionValid(inputDirection)

        then:
        0 * _

        and:
        !valid
    }

    def "test isDirectionMatch - valid - happy path"() {
        given:
        String directionVODirection = "NORTHBOUND"
        String inputDirection = "North"

        when:
        boolean match = directionValidation.isDirectionMatch(inputDirection, directionVODirection)

        then:
        0 * _

        and:
        match
    }
}
