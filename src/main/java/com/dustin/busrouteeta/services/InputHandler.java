package com.dustin.busrouteeta.services;

import com.dustin.busrouteeta.data.BusRouteEtaConstants;

import java.util.HashMap;
import java.util.Scanner;

public class InputHandler {

    private static String defaultReponse = "Please give the route name, stop name, and direction of the transit route "
            + "to find the ETA of the next transit; with each item within quotations (\"). Else, give nothing, and "
            + "answer the prompts as they come.";

    public static HashMap<String, String> handleInputs(String[] args) {
        HashMap<String, String> inputMap = new HashMap<>();

        if (args.length == 0) {
            return handleNoInputs();
        } else if (args.length == 3) {
            return handleThreeInputs(args);
        } else {
            handleOtherInputs();
        }

        return inputMap;
    }

    private static HashMap<String, String> handleNoInputs() {
        System.out.println("No information supplied.\n");

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter the route name: ");
        String route = keyboard.nextLine();

        System.out.print("Enter the name of the bus stop: ");
        String stopName = keyboard.nextLine();

        System.out.print("Enter the name of the direction: ");
        String direction = keyboard.nextLine();

        System.out.println();
        keyboard.close();

        HashMap<String, String> inputMap = new HashMap<>();
        inputMap.put(BusRouteEtaConstants.INPUT_ROUTE, route);
        inputMap.put(BusRouteEtaConstants.INPUT_BUS_STOP, stopName);
        inputMap.put(BusRouteEtaConstants.INPUT_DIRECTION, direction);

        return inputMap;
    }

    private static HashMap<String, String> handleThreeInputs(String[] inputs) {
        HashMap<String, String> inputMap = new HashMap<>();
        String route = inputs[0];
        String stopName = inputs[1];
        String direction = inputs[2];

        inputMap.put(BusRouteEtaConstants.INPUT_ROUTE, route);
        inputMap.put(BusRouteEtaConstants.INPUT_BUS_STOP, stopName);
        inputMap.put(BusRouteEtaConstants.INPUT_DIRECTION, direction);

        return inputMap;
    }

    private static HashMap<String, String> handleOtherInputs() {
        System.out.println(defaultReponse);
        return new HashMap<>();
    }
}
