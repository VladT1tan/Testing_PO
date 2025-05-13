import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BaseClass {

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

    @DataProvider(name = "endpoints")
    public Object[][] endpoints() {
        return new Object[][]{
                {"/user"},
                {"/user/followers"},
                {"/user/notifications"}
        };
    }

    @Test(dataProvider = "endpoints")
    public void endpointsRequiringAuthReturn401(String endpoint) throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + endpoint);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            Assert.assertEquals(statusCode, 401, "Status code should be 401 for endpoint: " + endpoint);
        }
    }
}