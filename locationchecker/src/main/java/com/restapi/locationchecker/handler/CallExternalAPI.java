package com.restapi.locationchecker.handler;

import com.restapi.locationchecker.model.UserDataMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;

public class CallExternalAPI {

    static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * Https/Http connection to the provided URL (with headers)
     *
     * @param url  endpoint String
     * @return Response
     */
    public static List<UserDataMessage> sendRequest(String url) throws ValidationException {
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse result = null;
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            // Check if response is empty
            if (response != null && response.getStatusLine() != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                // Only parse the response if status is in the 2XX range. Allows for other calls such as creat (201)
                if (statusCode >= 200 || statusCode <= 299) {
                    List<UserDataMessage> responseParsedValue = JsonParser.parseJson(response);
                    closeHttpClient(response);
                    return responseParsedValue;
                }
            } else {
                closeHttpClient(response);
                return null;
            }

        } catch (IOException ioe) {
            throw new ValidationException(ioe);
        } catch (Exception e) {
            throw new ValidationException(e);
        }
        return null;
    }

    private static void closeHttpClient(CloseableHttpResponse httpClient){
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
