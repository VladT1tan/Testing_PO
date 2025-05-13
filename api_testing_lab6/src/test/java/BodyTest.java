package methods;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class BodyTest {

    private static final String BASE_ENDPOINT = "https://api.github.com";
    private CloseableHttpClient httpClient;

    @BeforeClass
    public void setUp() {
        httpClient = HttpClients.createDefault();
    }

    @AfterClass
    public void tearDown() throws Exception {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    @Test
    public void getBodyAsStringTest() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/users/octocat");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String jsonBody = ResponseUtils.getBodyAsString(response);
            Assert.assertNotNull(jsonBody, "JSON body should not be null");
            Assert.assertTrue(jsonBody.contains(User.LOGIN), "JSON body should contain 'login' field");
        }
    }

    @Test
    public void getBodyAsJSONObjectTest() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/users/octocat");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            JSONObject jsonObject = ResponseUtils.getBodyAsJSONObject(response);
            Assert.assertNotNull(jsonObject, "JSON object should not be null");
            Assert.assertTrue(jsonObject.has(User.LOGIN), "JSON object should have 'login' key");
        }
    }

    @Test
    public void returnsCorrectLogin() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/users/octocat");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            JSONObject jsonObject = ResponseUtils.getBodyAsJSONObject(response);
            Object loginValue = ResponseUtils.getValueFor(jsonObject, User.LOGIN);
            System.out.println("Actual login: " + loginValue);
            Assert.assertEquals(String.valueOf(loginValue), "octocat", "Login should be 'octocat'");
        }
    }

    @Test
    public void returnsCorrectId() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/users/octocat");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            JSONObject jsonObject = ResponseUtils.getBodyAsJSONObject(response);
            Object idValue = ResponseUtils.getValueFor(jsonObject, User.ID);
            System.out.println("Actual ID: " + idValue);
            Assert.assertEquals(String.valueOf(idValue), "583231", "ID should be '583231'");
        }
    }

    @Test
    public void getBodyAsMapTest() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/users/octocat");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            Map<String, Object> responseMap = ResponseUtils.getBodyAsMap(response);
            Assert.assertNotNull(responseMap, "Response map should not be null");
            Assert.assertTrue(responseMap.containsKey(User.LOGIN), "Response map should contain 'login' key");
        }
    }

    @Test
    public void returnsCorrectLoginFromMap() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/users/octocat");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            Map<String, Object> responseMap = ResponseUtils.getBodyAsMap(response);
            String loginValue = ResponseUtils.getValueFromMap(responseMap, User.LOGIN);
            System.out.println("Actual login from map: " + loginValue);
            Assert.assertEquals(loginValue, "octocat", "Login should be 'octocat'");
        }
    }

    @Test
    public void returnsCorrectIdFromMapAsString() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/users/octocat");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            Map<String, Object> responseMap = ResponseUtils.getBodyAsMap(response);
            String idValue = ResponseUtils.getValueFromMap(responseMap, User.ID);
            System.out.println("Actual ID from map: " + idValue);
            Assert.assertEquals(idValue, "583231", "ID should be '583231' as a string");
        }
    }

    @Test
    public void returnsCorrectHireableFromMap() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/users/octocat");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            Map<String, Object> responseMap = ResponseUtils.getBodyAsMap(response);
            String hireableValue = ResponseUtils.getValueFromMap(responseMap, User.HIREABLE);
            System.out.println("Actual hireable: " + hireableValue);
            Assert.assertTrue(hireableValue == null || hireableValue.equals("true") || hireableValue.equals("false"),
                    "Hireable should be null, 'true', or 'false'");
        }
    }
}