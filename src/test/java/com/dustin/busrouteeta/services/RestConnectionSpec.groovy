package com.dustin.busrouteeta.services

import com.dustin.busrouteeta.data.BusRouteEtaConstants
import com.dustin.busrouteeta.data.DirectionVO
import com.dustin.busrouteeta.data.NexTripRouteVO
import com.dustin.busrouteeta.data.StopVO
import spock.lang.Specification

class RestConnectionSpec extends Specification {

    RestConnection restConnection = new RestConnection()

    def "test getRoutes - valid - happy path"() {
        when:
        List<NexTripRouteVO> nexTripRouteVOS = restConnection.getRoutes()

        then:
        0 * _

        and:
        nexTripRouteVOS
        !nexTripRouteVOS.isEmpty()
    }

    def "test getDirections - valid - directions for route 901 are as expected."() {
        given:
        String routeID = "901"

        when:
        List<DirectionVO> directionVOS = restConnection.getDirections(routeID)

        then:
        0 * _

        and:
        directionVOS
        directionVOS.size() == 2
        directionVOS[0].text == "NORTHBOUND"
        directionVOS[0].value == BusRouteEtaConstants.DIRECTION_NORTH_ID
        directionVOS[1].text == "SOUTHBOUND"
        directionVOS[1].value == BusRouteEtaConstants.DIRECTION_SOUTH_ID
    }

    def "test getStops - valid - stops exist for given route and direction"() {
        given:
        String routeID = "901"
        String direction = "4"

        when:
        List<StopVO> stopVOS = restConnection.getStops(routeID, direction)

        then:
        0 * _

        and:
        stopVOS
        !stopVOS.isEmpty()
    }

    def "test getJsonResponseForURL - valid - happy path"() {
        given:
        String routeID = "901"
        String url = BusRouteEtaConstants.URL_DIRECTIONS + "/" + routeID

        when:
        String response = restConnection.getJsonResponseForUrl(url)

        then:
        0 * _

        and:
        response
        response == "[{\"Text\":\"NORTHBOUND\",\"Value\":\"4\"},{\"Text\":\"SOUTHBOUND\",\"Value\":\"1\"}]"
    }
}
