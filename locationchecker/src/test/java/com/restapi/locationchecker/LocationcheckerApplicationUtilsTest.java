package com.restapi.locationchecker;

import com.restapi.locationchecker.utils.ApplicationUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.xml.bind.ValidationException;

public class LocationcheckerApplicationUtilsTest {

    @Test
    public void cityNameFormattingTest(){
        String cityNameExpected = "London";
        String cityNameFormatted = ApplicationUtils.cityNameFormatting("london");
        Assert.assertEquals(cityNameExpected, cityNameFormatted);
    }

    @Test
    public void insertCityIntoURLTest(){
        String city = "London";
        String urlExpected = "https://dwp-techtest.herokuapp.com/city/London/users";
        String url = ApplicationUtils.insertCityIntoURL("https://dwp-techtest.herokuapp.com/city/{city}/users", city);

        Assert.assertEquals(url, urlExpected);
    }

    @Test
    public void insertUserIdIntoURLTest(){
        Integer userId = 1;
        String urlExpected = "https://dwp-techtest.herokuapp.com/user/1";
        String url = ApplicationUtils.insertUserIdIntoURL("https://dwp-techtest.herokuapp.com/user/{userId}", 1);

        Assert.assertEquals(url, urlExpected);
    }

    @Test
    public void haversineFormulaTest(){
        double expectedResult = 3464.0441895124436;
        double lat1 = 51.5007;
        double lon1 = 0.1246;
        double lat2 = 40.6892;
        double lon2 = 74.0445;
        double distance = ApplicationUtils.haversineFormula(lat1, lon1, lat2, lon2);

        double epsilon = 0.000001d;
        Assert.assertEquals(expectedResult, distance, epsilon);

    }
}

