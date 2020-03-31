package com.dustin.busrouteeta;

import com.dustin.busrouteeta.data.TimepointDepartureVO;
import com.dustin.busrouteeta.services.InputHandler;
import com.dustin.busrouteeta.services.InputValidation;
import com.dustin.busrouteeta.services.TimepointDeparture;

import java.util.HashMap;

public class BusRouteEtaApplication {

    public static void main(String[] args) {
        HashMap<String, String> responseMap;

        responseMap = InputHandler.handleInputs(args);

        if (responseMap.size() != 3) {
            return;
        }

        TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO();

        boolean valid = InputValidation.validateInputs(responseMap, timepointDepartureVO);

        if (!valid) {
            System.out.println("Please check your Route, Direction, and Stop to make sure they are correct.");
            return;
        }

        String routeETA = TimepointDeparture.getTimepointDeparture(timepointDepartureVO);
        System.out.println(routeETA);
    }
}
