package com.dustin.busrouteeta;

import com.dustin.busrouteeta.data.TimepointDepartureVO;
import com.dustin.busrouteeta.services.InputHandler;
import com.dustin.busrouteeta.services.InputValidation;

import java.util.HashMap;

public class BusRouteEtaApplication {

	public static void main(String[] args) {
        HashMap<String, String> responseMap;

        responseMap = InputHandler.handleInputs(args);

        if (responseMap.size() == 3) {
            TimepointDepartureVO timepointDepartureVO = new TimepointDepartureVO();
            boolean valid = InputValidation.validateInputs(responseMap, timepointDepartureVO);

            if (!valid) {
                System.out.println("Please check your Route, Direction, and Stop to make sure they are correct.");
                return;
            }

            System.out.println("Success.");

            // Send call to API

            // Receive services

            // Handle permutations on response
            //etc. Statuses other than the 200 variety

            // write response to user.
        }
	}
}
