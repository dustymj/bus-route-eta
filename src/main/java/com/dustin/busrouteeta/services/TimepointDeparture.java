package com.dustin.busrouteeta.services;

import com.dustin.busrouteeta.data.BusRouteEtaConstants;
import com.dustin.busrouteeta.data.NexTripDepartureVO;
import com.dustin.busrouteeta.data.TimepointDepartureVO;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimepointDeparture {

    public static String getTimepointDeparture(TimepointDepartureVO timepointDepartureVO) {
        String routeID = timepointDepartureVO.getRouteID();
        String stopID = timepointDepartureVO.getStopID();
        String direction = timepointDepartureVO.getDirection();

        String responseETA = "";

        List<NexTripDepartureVO> nexTripDepartureVOS = RestConnection.getNexTripDepartures(routeID, stopID, direction);

        if (nexTripDepartureVOS.isEmpty()) {
            responseETA = "Unfortunately, no more pickups are scheduled for this stop for the rest of the day";
        } else {
            NexTripDepartureVO nextDeparture = nexTripDepartureVOS.get(0);

            if (nextDeparture.isActual()) {
                responseETA = determineActualETA(nextDeparture);
            } else {
                responseETA = determineApproximateETA(nextDeparture);
            }
        }

        return responseETA;
    }

    private static String determineActualETA(NexTripDepartureVO nextDeparture) {
        return "Transit will arrive in " + nextDeparture.getDepartureText() + "utes.";
    }

    private static String determineApproximateETA(NexTripDepartureVO nextDeparture) {
        String nextDepartureTime = nextDeparture.getDepartureTime();

        Calendar currentTime = Calendar.getInstance();
        Calendar busArrivalTime = getBusRouteEtaCalendar(nextDepartureTime);

        long currentTimeMillis = currentTime.getTimeInMillis();
        long busArrivalTimeMillis = busArrivalTime.getTimeInMillis();

        long timeDifferenceMillis = busArrivalTimeMillis - currentTimeMillis;

        long timeDifferenceMinutes = timeDifferenceMillis / BusRouteEtaConstants.MILLIS_TO_MINUTES;

        return "Transit is scheduled to arrive in " + timeDifferenceMinutes + " minutes";
    }

    private static Calendar getBusRouteEtaCalendar(String departureTime) {
        long epoch = 0;
        String epochString = "";
        String routeTimezone = "";
        String timezoneID = "GMT";

        Calendar busArrivalTime = Calendar.getInstance();

        Pattern pattern = Pattern.compile(BusRouteEtaConstants.TIME_TIMEZONE_CAPTURE_REGEX);
        Matcher matcher = pattern.matcher(departureTime);

        while (matcher.find()) {
            epochString = matcher.group(1);
            routeTimezone = matcher.group(2);
        }

        epoch = Long.valueOf(epochString);

        timezoneID = timezoneID + routeTimezone;
        TimeZone routeTimeZone = TimeZone.getTimeZone(timezoneID);

        busArrivalTime.setTimeInMillis(epoch);
        busArrivalTime.setTimeZone(routeTimeZone);

        return busArrivalTime;
    }
}
