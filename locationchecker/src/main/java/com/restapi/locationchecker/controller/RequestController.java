package com.restapi.locationchecker.controller;

import com.restapi.locationchecker.constants.EndPoints;
import com.restapi.locationchecker.constants.LondonConstants;
import com.restapi.locationchecker.handler.CallExternalAPI;
import com.restapi.locationchecker.model.UserDataMessage;
import com.restapi.locationchecker.utils.ApplicationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RequestController {

    @GetMapping("/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    List<UserDataMessage> sendRequest(@NotBlank @PathVariable String city) throws ValidationException {
        // Check if path is allowed city
        if (city.equals(LondonConstants.cityName)) {
            // Call api for people living in London. First the city name is changed to the correct format then input into the url and the api is called.
            //Once called the response is parsed and returned to a list.
            List<UserDataMessage> outputList = CallExternalAPI.sendRequest(ApplicationUtils.insertCityIntoURL(EndPoints.endPointOne, city));

            // Call api with all users and parse through these.
            for (UserDataMessage p : CallExternalAPI.sendRequest(EndPoints.endPointTwo)) {
                // Parse through users and use haversine formula to find users located within 50 miles. London coords are hard coded but could use Google geo api for future
                // amendments to use any City.
                if(ApplicationUtils.haversineFormula(LondonConstants.latitude, LondonConstants.longitude, p.getLatitude(), p.getLongitude()) <= LondonConstants.distance) {
                    outputList.add(p);
                }
            }
            // Remove duplicates in the list
            List<UserDataMessage> listWithoutDuplicates = outputList.stream().distinct().collect(Collectors.toList());
            return listWithoutDuplicates;
        }
        else throw new ValidationException("Cannot use current city");
    }
}
