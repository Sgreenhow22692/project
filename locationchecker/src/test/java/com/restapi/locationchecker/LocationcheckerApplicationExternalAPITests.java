package com.restapi.locationchecker;

import com.restapi.locationchecker.constants.EndPoints;
import com.restapi.locationchecker.handler.CallExternalAPI;
import com.restapi.locationchecker.model.UserDataMessage;
import com.restapi.locationchecker.utils.ApplicationUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;

public class LocationcheckerApplicationExternalAPITests {

    @Test
    public void endpointOneCallTest() throws ValidationException {

        List<UserDataMessage> response = CallExternalAPI.sendRequest(ApplicationUtils.insertCityIntoURL(EndPoints.endPointOne, "London"));
        Assert.assertTrue(!response.toString().isEmpty());
    }

    @Test
    public void endpointTwoCallTest() throws ValidationException {

        List<UserDataMessage> response = CallExternalAPI.sendRequest(EndPoints.endPointTwo);
        Assert.assertTrue(!response.toString().isEmpty());
    }
}
