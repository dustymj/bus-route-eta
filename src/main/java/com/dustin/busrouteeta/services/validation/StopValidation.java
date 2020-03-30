package com.dustin.busrouteeta.services.validation;

import com.dustin.busrouteeta.data.BusRouteEtaConstants;
import com.dustin.busrouteeta.data.StopVO;
import com.dustin.busrouteeta.data.TimepointDepartureVO;
import com.dustin.busrouteeta.services.RestConnection;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

public class StopValidation {

    public static boolean validateStop(HashMap<String, String> inputs, TimepointDepartureVO timepointDepartureVO) {
        String inputStop = inputs.get(BusRouteEtaConstants.INPUT_BUS_STOP);
        String validatedRouteID = timepointDepartureVO.getRouteID();
        String validatedDirection = timepointDepartureVO.getDirection();

        List<StopVO> stopVOS = RestConnection.getStops(validatedRouteID, validatedDirection);

        for (StopVO stopVO : stopVOS) {
            String stopVODescription = stopVO.getText();
            String stopVOID = stopVO.getValue();
            if (isStopDescriptionMatch(inputStop, stopVODescription) || isStopIDMatch(inputStop, stopVOID)) {
                timepointDepartureVO.setStopID(stopVOID);
                return true;
            }
        }

        String inputRoute = inputs.get(BusRouteEtaConstants.INPUT_ROUTE);
        String inputDirection = inputs.get(BusRouteEtaConstants.INPUT_DIRECTION);
        System.out.println("Stop (" + inputStop + ") is not recognized as a valid stop for route: " + inputRoute + " and direction: " + inputDirection);
        return false;
    }

    private static boolean isStopDescriptionMatch(String inputStop, String stopVODescription) {
        return StringUtils.equalsIgnoreCase(inputStop, stopVODescription);
    }

    private static boolean isStopIDMatch(String inputStop, String stopVOID) {
        return StringUtils.equalsIgnoreCase(inputStop, stopVOID);
    }
}
