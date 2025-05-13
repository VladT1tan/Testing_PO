package methods;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;

public class ResponseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method using Java 8 Streams
    public static String getHeaderJava8Way(CloseableHttpResponse response, String headerName) {
        Header[] httpHeaders = response.getAllHeaders();
        return Arrays.stream(httpHeaders)
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Didn't find the header: " + headerName))
                .getValue();
    }

    // Method to check if a header is present
    public static boolean headerIsPresent(CloseableHttpResponse response, String headerName) {
        Header[] httpHeaders = response.getAllHeaders();
        return Arrays.asList(httpHeaders).stream()
                .anyMatch(header -> headerName.equalsIgnoreCase(header.getName()));
    }

    // Method to get response body as string
    public static String getBodyAsString(CloseableHttpResponse response) throws Exception {
        int statusCode = response.getStatusLine().getStatusCode();
        String body = EntityUtils.toString(response.getEntity());
        System.out.println("Response status: " + statusCode + ", body: " + body);
        if (statusCode != 200) {
            throw new RuntimeException("Unexpected status code: " + statusCode + ", response: " + body);
        }
        return body;
    }

    // Method for creating a JSON object
    public static JSONObject getBodyAsJSONObject(CloseableHttpResponse response) throws Exception {
        String jsonBody = getBodyAsString(response);
        return new JSONObject(jsonBody);
    }

    // Method to get value by key
    public static Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

    // Method to deserialize JSON into Map<String, Object>
    public static Map<String, Object> getBodyAsMap(CloseableHttpResponse response) throws Exception {
        String jsonBody = getBodyAsString(response);
        return objectMapper.readValue(jsonBody, Map.class);
    }

    // Method to extract value from Map by casting to string
    public static String getValueFromMap(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            throw new RuntimeException("Key '" + key + "' not found in response body");
        }
        Object value = map.get(key);
        return value != null ? value.toString() : null;
    }
}