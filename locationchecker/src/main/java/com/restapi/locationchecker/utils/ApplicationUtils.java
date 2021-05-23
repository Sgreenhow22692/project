package com.restapi.locationchecker.utils;

import javax.validation.constraints.NotNull;

public class ApplicationUtils {

    /**
     * Returns correct format of city name
     *
     * @param cityName String provided by user as part of URL
     * @return returns city converted into correct formatting for URI
     */
    public static String cityNameFormatting( String cityName) {
        cityName = cityName.toLowerCase();
        String cityNameFormatted = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        return cityNameFormatted;
    }

    /**
     * Returns correct format of city name
     *
     * @param url String provided from constants for endpoint
     * @param cityName String provided by user
     * @return returns city converted into correct formatting for URI
     */
    public static String insertCityIntoURL(@NotNull String url, @NotNull String cityName) {
        String formattedURL = url.replace("{city}", cityName);
        return formattedURL;
    }

    /**
     * Returns correct format of userId
     *
     * @param url String provided from constants for endpoint
     * @param userId Integer provided from api call
     * @return returns userid converted into correct formatting for URI
     */
    public static String insertUserIdIntoURL(@NotNull String url, @NotNull Integer userId) {
        String formattedURL = url.replace("{userId}", userId.toString());
        return formattedURL;
    }

    /**
     * Returns distance of two points
     *
     * @param lat1 Double of London lat
     * @param lon1 Double of London lon
     * @param lat2 Double of user lat
     * @param lon2 Double of user lon
     * @return returns distance in miles
     */
    public static double haversineFormula(double lat1, double lon1, double lat2, double lon2)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));

        // Convert KM to miles on return
        return (rad * c) * 0.621371;
    }
}
