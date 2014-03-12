package com.printful.api.RequestObjects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import com.printful.api.ProductCatalog.Product;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonNode;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

/**
 *
 * HttpClient vs. HttpCore
 *
 * HttpClient is a Client-side HTTP transport library based on HttpCore.
 *
 * HttpCore is a set of components implementing the most fundamental aspects of the HTTP protocol
 that are nonetheless sufficient to develop full-featured client-side and server-side HTTP services with
 a minimal footprint.
 */
public class ProductsRequest
{
    //public variables
    private HttpPost httpPost;
    private HttpGet httpGet;
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private CloseableHttpResponse response;
    private HttpEntity entity;
    private Gson gson = new Gson();
    private Base64 codec = new Base64();

    //private variables
    private String apiKey;
    private String encodedApiKey;
    private String jsonResponse;
    private ObjectMapper mapper = new ObjectMapper();

    public ProductsRequest(String apiKey)
    {
        super();
        this.apiKey = apiKey;
        this.encodedApiKey = codec.encodeBase64String(apiKey.getBytes());
    }

    public List<Product> getAllProductList(String path)
    {
        //the list of products that will be returned
        List<Product> returnedProducts = null;

        //set the endpoint that will be used to get the list of products
        httpGet = new HttpGet(path);
        String basicString = "Basic "+this.encodedApiKey;
        //httpGet.addHeader("Authorization",basicString.substring(0,basicString.length()-2));

        try
        {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200)
            {
                entity = response.getEntity();
                jsonResponse = EntityUtils.toString(entity);
                Type listType = new TypeToken<List<Product>>(){}.getType();
                JsonNode root = mapper.readTree(jsonResponse);
                ArrayNode products = (ArrayNode) root.path("result");
                returnedProducts = (List<Product>) gson.fromJson(products.toString(),listType);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            String error = e.toString();
            int i;
        }

        return returnedProducts;
    }
}
