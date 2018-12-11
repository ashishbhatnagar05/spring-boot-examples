package com.spring.ssl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class HttpsClientTest {


    @Test(expected = SSLHandshakeException.class)
    public void whenHttpsUrlIsConsumed_thenException()
            throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String urlOverHttps
                = "https://localhost:8443/hello";
        HttpGet getMethod = new HttpGet(urlOverHttps);

        HttpResponse response = httpClient.execute(getMethod);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
    }


    //TODO not running
    @Test
    public void whenHttpsUrlIsConsumed()
            throws Exception {

        HttpClient httpClient = HttpsClientFactory2.getHttpsClient();
        String urlOverHttps
                = "https://localhost:8443/hello";
        HttpGet getMethod = new HttpGet(urlOverHttps);

        HttpResponse response = httpClient.execute(getMethod);
        HttpsClientFactory2.releaseInstance();
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));

    }


}
