import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import methods.ResponseUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResponseHeaders {

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
    public void shouldReturnFullContentTypeWithCharset() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            Header contentTypeHeader = response.getFirstHeader("Content-Type");
            Assert.assertNotNull(contentTypeHeader, "Content-Type header should not be null");

            String contentTypeValue = contentTypeHeader.getValue();
            Assert.assertEquals(contentTypeValue, "application/json; charset=utf-8",
                    "Content-Type should be 'application/json; charset=utf-8'");
        }
    }

    @Test
    public void shouldReturnCorrectMimeType() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            ContentType contentType = ContentType.getOrDefault(response.getEntity());
            String mimeType = contentType.getMimeType();
            Assert.assertEquals(mimeType, "application/json",
                    "MIME type should be 'application/json'");
        }
    }

    @Test
    public void shouldReturnServerHeaderAsGitHub() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String serverValue = ResponseUtils.getHeaderJava8Way(response, "Server");
            Assert.assertEquals(serverValue, "github.com",
                    "Server header should be 'github.com'");
        }
    }

    @Test
    public void xRateLimitIsSixtyForUnauthenticated() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String limitVal = ResponseUtils.getHeaderJava8Way(response, "X-RateLimit-Limit");
            Assert.assertEquals(limitVal, "60",
                    "X-RateLimit-Limit header should be '60' for unauthenticated requests");
        }
    }

    @Test
    public void shouldCheckETagPresence() throws Exception {
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            boolean headerIsPresent = ResponseUtils.headerIsPresent(response, "ETag");
            Assert.assertTrue(headerIsPresent, "ETag header should be present");
        }
    }
}