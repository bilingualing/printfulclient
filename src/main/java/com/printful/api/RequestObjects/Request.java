package com.printful.api.RequestObjects;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: jamaaltaylor
 * Date: 3/14/14
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Request
{
    private String apiKey;
    private HttpGet httpGet = new HttpGet();
    private HttpPost httpPost = new HttpPost();
    private String encodedApiKey;
    private Base64 codec = new Base64();
    private String baseUrl = "https://api.theprintful.com";
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private CloseableHttpResponse response;

    public Request(String apiKey)
    {
        super();
        this.apiKey = apiKey;
        this.encodedApiKey = codec.encodeBase64String(apiKey.getBytes());
    }

    public HttpGet get(String path)
    {
        httpGet = new HttpGet(baseUrl+path);
        String basicString = "Basic "+this.encodedApiKey;
        httpGet.addHeader("Authorization",basicString.substring(0,basicString.length()-2));
        return httpGet;
    }

    public CloseableHttpResponse getResponse(HttpGet get) throws ClientProtocolException
    {
        try
        {
            response = httpClient.execute(get);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return response;
    }

    public HttpPost post(String path)
    {
        httpPost = new HttpPost(baseUrl+path);
        String basicString = "Basic "+this.encodedApiKey;
        httpPost.addHeader("Authorization",basicString.substring(0,basicString.length()-2));
        return httpPost;
    }

    public CloseableHttpResponse postResponse(HttpPost post) throws ClientProtocolException
    {
        try
        {
            response = httpClient.execute(post);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return response;
    }
}
