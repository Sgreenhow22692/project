package com.restapi.locationchecker.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.locationchecker.model.UserDataMessage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

	/**
	 * Method responsible for parsing JSON object retrieved from API and mapping it
	 * to object Person
	 * 
	 * @param response CloseableHttpResponse retrieved from API call
	 * @return returns List<UserDataMessage> with retrieved and mapped data from API
	 */
	public static List<UserDataMessage> parseJson(CloseableHttpResponse response) throws ValidationException {
		HttpEntity entity = response.getEntity();
		ObjectMapper mapper = new ObjectMapper();
		List<UserDataMessage> listResults = new ArrayList<UserDataMessage>();
		// Enables handling single value as array
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		if (entity != null) {
			InputStream inputStream;
			try {
				inputStream = entity.getContent();

				// Maps JSON values to List<UserDataMessage>
				listResults = mapper.readValue(inputStream, new TypeReference<List<UserDataMessage>>() {
				});
			} catch (UnsupportedOperationException | IOException e) {
				throw new ValidationException(e);
			} catch (Exception e) {
				throw new ValidationException(e);
			}
		}
		return listResults;
	}
}
