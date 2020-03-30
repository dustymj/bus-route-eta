package com.dustin.busrouteeta.services;

import com.dustin.busrouteeta.data.BusRouteEtaConstants;
import com.dustin.busrouteeta.data.DirectionVO;
import com.dustin.busrouteeta.data.NexTripRouteVO;
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
