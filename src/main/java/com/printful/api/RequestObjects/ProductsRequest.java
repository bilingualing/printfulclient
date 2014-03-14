package com.printful.api.RequestObjects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.printful.api.ProductCatalog.ProductInfo;
import com.printful.api.ProductCatalog.VariantInfo;
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
public class ProductsRequest extends Request
{

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private CloseableHttpResponse response;
    private HttpEntity entity;
    private Gson gson = new Gson();
    //private variables
    private String jsonResponse;
    private ObjectMapper mapper = new ObjectMapper();

    public ProductsRequest(String apiKey)
    {
        super(apiKey);
    }

    public List<Product> getAllProductList(CloseableHttpResponse response)
    {
        //the list of products that will be returned
        List<Product> returnedProducts = null;

        try
        {
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
        }

        return returnedProducts;
    }

    public VariantInfo getVariantInfo(CloseableHttpResponse response)
    {
        VariantInfo info = null;

        try
        {
            if(response.getStatusLine().getStatusCode() == 200)
            {
                entity = response.getEntity();
                jsonResponse = EntityUtils.toString(entity);
                JsonNode root = mapper.readTree(jsonResponse);
                ArrayNode variant = (ArrayNode) root.path("result");
                info = gson.fromJson(variant.toString(),VariantInfo.class);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return info;
    }

    public ProductInfo getProductsVariantList(CloseableHttpResponse response)
    {
        ProductInfo info = null;

        try
        {
            if(response.getStatusLine().getStatusCode() == 200)
            {
                entity = response.getEntity();
                jsonResponse = EntityUtils.toString(entity);
                JsonNode root = mapper.readTree(jsonResponse);
                ArrayNode aNode = (ArrayNode) root.path("result");
                info = gson.fromJson(aNode.toString(), ProductInfo.class);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return info;
    }
}
