package com.dustin.busrouteeta.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NexTripDepartureVO {
    private boolean actual;
    private String blockNumber;
    private String departureText;
    private String departureTime;
    private String description;
    private String gate;
    private String route;
    private String routeDirection;
    private String terminal;
    private String vehicleHeading;
    private String vehicleLatitude;
    private String vehicleLongitude;

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getDepartureText() {
        return departureText;
    }

    public void setDepartureText(String departureText) {
        this.departureText = departureText;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRouteDirection() {
        return routeDirection;
    }

    public void setRouteDirection(String routeDirection) {
        this.routeDirection = routeDirection;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getVehicleHeading() {
        return vehicleHeading;
    }

    public void setVehicleHeading(String vehicleHeading) {
        this.vehicleHeading = vehicleHeading;
    }

    public String getVehicleLatitude() {
        return vehicleLatitude;
    }

    public void setVehicleLatitude(String vehicleLatitude) {
        this.vehicleLatitude = vehicleLatitude;
    }

    public String getVehicleLongitude() {
        return vehicleLongitude;
    }

    public void setVehicleLongitude(String vehicleLongitude) {
        this.vehicleLongitude = vehicleLongitude;
    }
}
