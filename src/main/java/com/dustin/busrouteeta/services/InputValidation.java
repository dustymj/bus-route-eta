package com.dustin.busrouteeta.services;


import com.dustin.busrouteeta.data.TimepointDepartureVO;
import com.dustin.busrouteeta.services.validation.DirectionValidation;
import com.dustin.busrouteeta.services.validation.RouteValidation;
import com.dustin.busrouteeta.services.validation.StopValidation;

import java.util.HashMap;

public class InputValidation {

    public static boolean validateInputs(HashMap<String, String> inputs, TimepointDepartureVO timepointDepartureVO) {
        return RouteValidation.validateRoute(inputs, timepointDepartureVO) &&
                DirectionValidation.validateDirection(inputs, timepointDepartureVO) &&
                StopValidation.validateStop(inputs, timepointDepartureVO);
    }

}
