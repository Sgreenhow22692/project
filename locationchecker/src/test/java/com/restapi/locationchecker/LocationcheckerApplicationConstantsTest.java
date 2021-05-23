package com.restapi.locationchecker;

import com.restapi.locationchecker.constants.EndPoints;
import com.restapi.locationchecker.handler.CallExternalAPI;
import com.restapi.locationchecker.utils.ApplicationUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LocationcheckerApplicationConstantsTest {

    @Test
    public void endpointsNameTest(){

        String endPointOne = EndPoints.endPointOne;
        String endPointOneExpected = "https://dwp-techtest.herokuapp.com/city/{city}/users";
        Assert.assertEquals(endPointOneExpected, endPointOne);

        String endPointTwo = EndPoints.endPointTwo;
        String endPointTwoExpected = "https://dwp-techtest.herokuapp.com/users";
        Assert.assertEquals(endPointTwoExpected, endPointTwo);

        String endPointThree = EndPoints.endPointThree;
        String endPointThreeExpected = "https://dwp-techtest.herokuapp.com/user/{userId}";
        Assert.assertEquals(endPointThreeExpected, endPointThree);

    }
}

