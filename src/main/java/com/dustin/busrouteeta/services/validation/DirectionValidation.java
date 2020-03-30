package com.dustin.busrouteeta.services.validation;

import com.dustin.busrouteeta.data.BusRouteEtaConstants;
import com.dustin.busrouteeta.data.DirectionVO;
import com.dustin.busrouteeta.data.TimepointDepartureVO;
import com.dustin.busrouteeta.services.RestConnection;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

public class DirectionValidation {

    public static boolean validateDirection(HashMap<String, String> inputs, TimepointDepartureVO timepointDepartureVO) {
        String validatedRouteID = timepointDepartureVO.getRouteID();
        String inputDirection = inputs.get(BusRouteEtaConstants.INPUT_DIRECTION);
        String inputRoute = inputs.get(BusRouteEtaConstants.INPUT_ROUTE);

        if (!isDirectionValid(inputDirection)) {
            System.out.println("Invalid direction given: " + inputDirection);
            System.out.println("Direction must be North, South, East, or West. Northbound, Southbound, Eastbound, and Westbound are acceptable as well.");
            return false;
        }

        List<DirectionVO> directionVOS = RestConnection.getDirections(validatedRouteID);

        for (DirectionVO directionVO : directionVOS) {
            if (isDirectionMatch(inputDirection, directionVO.getText())) {
                timepointDepartureVO.setDirection(directionVO.getValue());
                return true;
            }
        }

        System.out.println("Direction (" + inputDirection + ") is not recognized as a valid direction for route: " + inputRoute);
        return false;
    }

    private static boolean isDirectionValid(String inputDirection) {
        return StringUtils.containsIgnoreCase(inputDirection, BusRouteEtaConstants.DIRECTION_NORTH) ||
               StringUtils.containsIgnoreCase(inputDirection, BusRouteEtaConstants.DIRECTION_EAST) ||
               StringUtils.containsIgnoreCase(inputDirection, BusRouteEtaConstants.DIRECTION_SOUTH) ||
               StringUtils.containsIgnoreCase(inputDirection, BusRouteEtaConstants.DIRECTION_WEST);
    }

    private static boolean isDirectionMatch(String inputDirection, String directionVODirection) {
        return StringUtils.containsIgnoreCase(directionVODirection, inputDirection);
    }
}
