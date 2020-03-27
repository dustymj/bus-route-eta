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

    def "test addTwoNumbers - valid - happy path"() {
        given:
        int numberOne = 1
        int numberTwo = 2

        when:
        int sum = busRouteEtaApplication.addTwoNumbers(numberOne, numberTwo)

        then:
        0 * _

        and:
        sum
        sum == 4
    }
}
