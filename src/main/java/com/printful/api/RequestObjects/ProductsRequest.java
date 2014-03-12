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

import java.io.*;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonNode;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    public List<Product> getAllProducts(String requestType, String path)
    {
        List<Product> products = null;
        URL url;
        HttpURLConnection connection = null;
        String basicString = "Basic "+this.encodedApiKey.substring(0,encodedApiKey.length()-2);

        try
        {
            url = new URL(path);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(requestType);

            //set the request properties here
            connection.setRequestProperty("Authorization",basicString);

            connection.setUseCaches(false);

            /*
            * A URL connection can be used for input and/or output. Setting the doInput flag to true indicates
            * that the application intends to read data from the URL connection.
            * The default value of this field is true.
            * */
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //send the request
            DataOutputStream request = new DataOutputStream(connection.getOutputStream());
            request.flush();
            request.close();

            //get the response
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while((line = reader.readLine()) != null)
            {
                response.append(line);
            }

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(connection != null)
            {
                connection.disconnect();
            }
        }

        return products;
    }

    public List<Product> getAllProductList(String path)
    {
        //the list of products that will be returned
        List<Product> returnedProducts = null;

        //set the endpoint that will be used to get the list of products
        httpGet = new HttpGet(path);
        String basicString = "Basic "+this.encodedApiKey;
        httpGet.addHeader("Authorization",basicString.substring(0,basicString.length()-2));

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
