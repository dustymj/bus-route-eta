package com.dustin.busrouteeta

import spock.lang.Specification

class BusRouteEtaApplicationSpec extends Specification {

    BusRouteEtaApplication busRouteEtaApplication = new BusRouteEtaApplication()

    def "test main - valid - happy path"() {
        given:
        String[] args = []

        when:
        busRouteEtaApplication.main(args)

        then:
        0 * _

        and:
        noExceptionThrown()
    }
}
