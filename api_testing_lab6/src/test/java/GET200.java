import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GET200 {

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
    public void baseUrlReturns200() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            Assert.assertEquals(statusCode, 200, "Status code should be 200");
        }
    }

    @Test
    public void rateLimitReturns200() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            Assert.assertEquals(statusCode, 200, "Status code should be 200");
        }
    }
}