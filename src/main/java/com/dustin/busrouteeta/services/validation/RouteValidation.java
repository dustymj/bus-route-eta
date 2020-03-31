package com.dustin.busrouteeta.services.validation;

import com.dustin.busrouteeta.data.BusRouteEtaConstants;
import com.dustin.busrouteeta.data.NexTripRouteVO;
import com.dustin.busrouteeta.data.TimepointDepartureVO;
import com.dustin.busrouteeta.services.RestConnection;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

public class RouteValidation {

    public static boolean validateRoute(HashMap<String, String> inputs, TimepointDepartureVO timepointDepartureVO) {
        String inputRoute = inputs.get(BusRouteEtaConstants.INPUT_ROUTE);

        List<NexTripRouteVO> nexTripRouteVOS = RestConnection.getRoutes();

        for (NexTripRouteVO nexTripRouteVO : nexTripRouteVOS) {
            String nexTripRouteVODescription = nexTripRouteVO.getDescription();
            String nexTripRouteVORouteID = nexTripRouteVO.getRoute();

            if (isRouteDescriptionMatch(inputRoute, nexTripRouteVODescription) || isRouteIDMatch(inputRoute, nexTripRouteVORouteID)) {
                timepointDepartureVO.setRouteID(nexTripRouteVORouteID);
                return true;
            }
        }

        System.out.println("Route (" + inputRoute + ") is not recognized as a valid route.");
        return false;
    }

    private static boolean isRouteDescriptionMatch(String inputRoute, String description) {
        return StringUtils.equalsIgnoreCase(inputRoute, description);
    }

    private static boolean isRouteIDMatch(String inputRoute, String routeID) {
        return StringUtils.equalsIgnoreCase(inputRoute, routeID);
    }
}
