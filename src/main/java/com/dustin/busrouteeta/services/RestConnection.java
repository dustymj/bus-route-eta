package com.dustin.busrouteeta.services;

import com.dustin.busrouteeta.data.*;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestConnection {

    public static List<NexTripRouteVO> getRoutes() {
        List<NexTripRouteVO> nexTripRouteVOS = new ArrayList<>();

        try {
            String jsonResponseString = getJsonResponseForUrl(BusRouteEtaConstants.URL_ROUTES);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            nexTripRouteVOS = Arrays.asList(mapper.readValue(jsonResponseString, NexTripRouteVO[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nexTripRouteVOS;
    }

    public static List<DirectionVO> getDirections(String routeID) {
        List<DirectionVO> directionVOS = new ArrayList<>();
        String url = BusRouteEtaConstants.URL_DIRECTIONS + "/" + routeID;

        try {
            String jsonResponseString = getJsonResponseForUrl(url);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            directionVOS = Arrays.asList(mapper.readValue(jsonResponseString, DirectionVO[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return directionVOS;
    }

    public static List<StopVO> getStops(String routeID, String direction) {
        List<StopVO> stopVOS = new ArrayList<>();
        String url = BusRouteEtaConstants.URL_STOPS + "/" + routeID + "/" + direction;

        try {
            String jsonResponseString = getJsonResponseForUrl(url);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            stopVOS = Arrays.asList(mapper.readValue(jsonResponseString, StopVO[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stopVOS;
    }

    public static List<NexTripDepartureVO> getNexTripDepartures(String routeID, String stopID, String direction) {
        List<NexTripDepartureVO> nexTripDepartureVOS = new ArrayList<>();
        String url = BusRouteEtaConstants.URL_NEXT_ARRIVAL + "/" + routeID + "/" + direction + "/" + stopID;

        try {
            String jsonResponseString = getJsonResponseForUrl(url);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            nexTripDepartureVOS = Arrays.asList(mapper.readValue(jsonResponseString, NexTripDepartureVO[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nexTripDepartureVOS;
    }

    private static String getJsonResponseForUrl(String urlString) {
        String jsonResponseString = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setRequestProperty(BusRouteEtaConstants.CONTENT_TYPE,
                                                 BusRouteEtaConstants.APPLICATION_JSON);
            httpURLConnection.setRequestProperty(BusRouteEtaConstants.ACCEPT_FORMAT,
                                                 BusRouteEtaConstants.APPLICATION_JSON);

            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String responseLine;
            StringBuilder responseBuilder = new StringBuilder();

            while ((responseLine = bufferedReader.readLine()) != null) {
                responseBuilder.append(responseLine);
            }

            bufferedReader.close();
            httpURLConnection.disconnect();

            jsonResponseString = responseBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponseString;
    }
}
