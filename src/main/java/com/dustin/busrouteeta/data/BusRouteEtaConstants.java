package com.dustin.busrouteeta.data;

public class BusRouteEtaConstants {

    // Input BusRouteEtaConstants
    public static String INPUT_ROUTE = "route";
    public static String INPUT_BUS_STOP = "busStop";
    public static String INPUT_DIRECTION = "direction";

    // Direction BusRouteEtaConstants
    public static String DIRECTION_SOUTH_ID = "1";
    public static String DIRECTION_EAST_ID = "2";
    public static String DIRECTION_WEST_ID = "3";
    public static String DIRECTION_NORTH_ID = "4";
    public static String DIRECTION_SOUTH = "south";
    public static String DIRECTION_EAST = "east";
    public static String DIRECTION_WEST = "west";
    public static String DIRECTION_NORTH = "north";

    // URL BusRouteEtaConstants
    public static String URL_ROUTES = "https://svc.metrotransit.org/NexTrip/Routes";
    public static String URL_DIRECTIONS = "https://svc.metrotransit.org/NexTrip/Directions";
    public static String URL_STOPS = "https://svc.metrotransit.org/NexTrip/Stops";
    public static String URL_NEXT_ARRIVAL = "https://svc.metrotransit.org/NexTrip";

    // REST Constants
    public static String JSON_FORMAT = "?format=json";
    public static String CONTENT_TYPE = "Content-Type";
    public static String ACCEPT_FORMAT = "Accept";
    public static String APPLICATION_JSON = "application/json";
}
