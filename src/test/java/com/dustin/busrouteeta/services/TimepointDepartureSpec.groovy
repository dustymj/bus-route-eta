package com.dustin.busrouteeta.services

import com.dustin.busrouteeta.data.NexTripDepartureVO
import spock.lang.Specification

class TimepointDepartureSpec extends Specification {

    TimepointDeparture timepointDeparture = new TimepointDeparture()

    def "test determineActualETA - valid - happy path"() {
        given:
        NexTripDepartureVO nexTripDepartureVO = new NexTripDepartureVO(
                departureText: "11 min"
        )

        when:
        String actualETA = timepointDeparture.determineActualETA(nexTripDepartureVO)

        then:
        0 * _

        and:
        actualETA
        actualETA == "Transit will arrive in 11 minutes."
    }

    def "test getBusRouteEtaCalendar - valid - happy path"() {
        given:
        String departureTime = "/Date(1585612860000-0500)/"

        when:
        Calendar busRouteEtaCalendar = timepointDeparture.getBusRouteEtaCalendar(departureTime)

        then:
        0 * _

        and:
        busRouteEtaCalendar
        busRouteEtaCalendar.timeInMillis == 1585612860000
        busRouteEtaCalendar.timeZone == TimeZone.getTimeZone("GMT-0500")
    }
}
